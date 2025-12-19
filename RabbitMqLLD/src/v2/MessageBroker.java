package v2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

public class MessageBroker {
    private volatile boolean running = true;
    private final Map<String, BlockingQueue<Message>> queues = new ConcurrentHashMap<>();
    private final Map<String, Exchange> exchanges = new ConcurrentHashMap<>();

    public MessageBroker() {

    }

    public void createQueue(String queueName) {
        queues.putIfAbsent(queueName, new LinkedBlockingQueue<>());
    }

    public void createExchange(String exchangeName,ExchangeType exchangeType) {
        exchanges.putIfAbsent(exchangeName,new Exchange(exchangeName,exchangeType));
    }

    public void bindQueueToExchange(String queueName,String exchangeName,String routingKey){
        Exchange exchange = exchanges.get(exchangeName);
        if(Objects.nonNull(exchange)){
            exchange.bindQueue(queueName,routingKey);
        }
    }

    public void publish(String exchangeName,Message message){
        Exchange exchange = exchanges.get(exchangeName);
        if(Objects.nonNull(exchange)){
            List<String> queueNames = exchange.route(message.getRoutingKey());
            for(String queueName : queueNames){
                queues.get(queueName).add(message);
            }
        }
    }

    public void subscribe(String queueName,Subscriber subscriber){
        new Thread(()->{
            while(running){
                try {
                    Message message = queues.get(queueName).poll(1, TimeUnit.SECONDS);
                    if(Objects.nonNull(message)){
                        subscriber.receiveMessage(message,()->{
                            //System.out.println("Message Delivered to " + queueName + ", message is " + message.getMessage());
                        });
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public void shutdown(){
        running = false;
    }

}
