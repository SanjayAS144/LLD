package v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Publisher {
    private final AtomicInteger counter = new AtomicInteger(0);
    private volatile boolean running = true;
    private final List<Subscriber> subscribers = new ArrayList<Subscriber>();
    private final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private final BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>(100);
    private final Map<String,CompletableFuture<Message>> futures = new ConcurrentHashMap<>();

    public int getCount() {
        return counter.get();
    }

    public Publisher() {
        processMessages();
    }

    public void subscribe(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    public void unSubscribe(Subscriber subscriber){
        subscribers.remove(subscriber);
    }

    public CompletableFuture<Message> publish (Message message){
        CompletableFuture<Message> completableFuture = new CompletableFuture<>();
        futures.put(message.getId(), completableFuture);
        queue.add(message);
        return completableFuture;
    }

    public void shutdown(){
        running = false;
        executor.shutdown();
    }

    private void processMessages(){
        new Thread(()->{
            while (running || !queue.isEmpty()){
                try {
                    Message message = queue.poll(1, TimeUnit.SECONDS);
                    if(Objects.nonNull(message)){
                        for (Subscriber subscriber : subscribers){
                            executor.submit(()->{
                                if(subscriber.getPredicate().test(message)){
                                    subscriber.receiveMessage(message,()->{
                                        CompletableFuture<Message> completableFuture = futures.remove(message.getId());
                                        if(Objects.nonNull(completableFuture)){
                                            counter.incrementAndGet();
                                            completableFuture.complete(message);
                                        }
                                    });
                                }
                            });
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println("Exception occurred while processing the messages" + e.getMessage());
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }
}
