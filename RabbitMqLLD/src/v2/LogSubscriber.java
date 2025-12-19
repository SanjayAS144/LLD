package v2;

import java.text.MessageFormat;
import java.util.function.BiConsumer;

public class LogSubscriber implements Subscriber{
    private final String name;
    BiConsumer<Message, Subscriber> biConsumer;
    public LogSubscriber(String name, BiConsumer<Message, Subscriber> biConsumer) {
        this.name = name;
        this.biConsumer = biConsumer;
    }

    public String getName() {
        return name;
    }

    @Override
    public void receiveMessage(Message message, Runnable runnable) {
        System.out.println(MessageFormat.format("v2.Subscriber {0} received message {1}", name, message.getMessage()));
        runnable.run();
        this.biConsumer.accept(message,this);
    }


}
