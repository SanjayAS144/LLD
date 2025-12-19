package v1;

import java.text.MessageFormat;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class SubscriberB implements Subscriber {

    private final String name;
    private final Predicate<Message> predicate;
    BiConsumer<Message, Subscriber> biConsumer;
    public SubscriberB(String name, Predicate<Message> predicate, BiConsumer<Message, Subscriber> biConsumer) {
        this.name = name;
        this.predicate = predicate;
        this.biConsumer = biConsumer;
    }

    public String getName() {
        return name;
    }
    @Override
    public Predicate<Message> getPredicate() {
        return predicate;
    }

    @Override
    public void receiveMessage(Message message, Runnable runnable) {
        System.out.println(MessageFormat.format("Subscriber {0} received message {1}", name, message.getMessage()));
        runnable.run();
        this.biConsumer.accept(message,this);
    }
}
