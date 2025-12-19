package v1;

import java.util.function.Predicate;

public interface Subscriber {
    public void receiveMessage(Message message, Runnable runnable);
    public Predicate<Message> getPredicate();
    public String getName();
}
