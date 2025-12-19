package v2;

import java.text.MessageFormat;
import java.util.function.BiConsumer;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        BiConsumer<Message,Subscriber> biConsumer = (message,subscriber)->{
            System.out.println(MessageFormat.format("Message of type {0} and message {1} is processed at subscriber {2}",message.getRoutingKey(),message.getMessage(), subscriber.getName()));
        };

        MessageBroker messageBroker = new MessageBroker();

        messageBroker.createQueue("log-queue");
        messageBroker.createQueue("task-queue");
        messageBroker.createQueue("alerts-queue");

        messageBroker.createExchange("log-exchange",ExchangeType.FANOUT);
        messageBroker.createExchange("task-exchange",ExchangeType.DIRECT);
        messageBroker.createExchange("alerts-exchange",ExchangeType.TOPIC);

        messageBroker.bindQueueToExchange("log-queue","log-exchange","");
        messageBroker.bindQueueToExchange("task-queue","log-exchange","");
        messageBroker.bindQueueToExchange("alerts-queue","log-exchange","");
        messageBroker.bindQueueToExchange("task-queue","task-exchange","task.task1");
        messageBroker.bindQueueToExchange("alerts-queue","alerts-exchange","alerts.#");

//        Subscriber logSubscriber = new LogSubscriber("log-subscriber",biConsumer);
//        Subscriber taskSubscriber = new TaskSubscriber("task-subscriber",biConsumer);
//        Subscriber alertsSubscriber = new AlertSubscriber("alerts-subscriber",biConsumer);

        messageBroker.subscribe("log-queue",new LogSubscriber("log-subscriber",biConsumer));
        messageBroker.subscribe("task-queue",new TaskSubscriber("task-subscriber",biConsumer));
        messageBroker.subscribe("alerts-queue",new AlertSubscriber("alerts-subscriber",biConsumer));

        messageBroker.publish("log-exchange",new Message("Info Log ", ""));
//        messageBroker.publish("task-exchange",new Message("perform following task ", "task.task1"));
//        messageBroker.publish("alerts-exchange",new Message("Nodes alerts ", "alerts.node"));
//        messageBroker.publish("alerts-exchange",new Message("Pods alerts ", "alerts.pod"));

    }
}