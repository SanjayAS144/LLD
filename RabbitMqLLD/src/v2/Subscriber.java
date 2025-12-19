package v2;

import java.util.function.Predicate;

public interface Subscriber {
    public void receiveMessage(Message message, Runnable runnable);
    public String getName();
}
