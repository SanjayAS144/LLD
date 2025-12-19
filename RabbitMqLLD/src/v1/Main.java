package v1;

import java.text.MessageFormat;
import java.util.function.BiConsumer;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        BiConsumer<Message, Subscriber> biConsumer = (message, subscriber)->{
            System.out.println(MessageFormat.format("Message of type {0} and message {1} is processed at subscriber {2}",message.getMessageType(),message.getMessage(), subscriber.getName()));
        };

        publisher.subscribe(new SubscriberA("SubscriberA", x -> x.getMessageType().equals("INFO"),biConsumer));
        publisher.subscribe(new SubscriberB("SubscriberB", x-> x.getMessageType().equals("ERROR"),biConsumer));
        publisher.subscribe(new SubscriberA("SubscriberA1", x -> true,biConsumer));

        for(int i=0;i<5;i++){
            String messageType = i%2 == 0 ? "INFO" : "ERROR";
            Message message = new Message("message "+ messageType + " " + i , messageType);
            publisher.publish(message).thenAccept(msg -> System.out.println("Message Delivered" + msg.getMessage()));
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(publisher.getCount());
        publisher.shutdown();
    }
}