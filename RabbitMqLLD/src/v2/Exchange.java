package v2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Exchange {
    private final String name;
    private final ExchangeType type;
    private final Map<String, List<String>> bindings = new ConcurrentHashMap<>();

    public Exchange(String name, ExchangeType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public ExchangeType getType() {
        return type;
    }

    public void bindQueue(String queueName,String routingKey){
        bindings.computeIfAbsent(routingKey, x -> new ArrayList<>()).add(queueName);
    }

    public List<String> route(String routingKey){
        List<String> matchedQueues = new ArrayList<>();
        switch (type){
            case FANOUT: bindings.values().forEach(matchedQueues::addAll);
            break;
            case DIRECT:matchedQueues.addAll(bindings.get(routingKey));
            break;
            case TOPIC: for (var entry : bindings.entrySet()) {
                if(matchTopic(routingKey,entry.getKey())){
                    matchedQueues.addAll(entry.getValue());
                }
            }
        }
        return matchedQueues;
    }

    private boolean matchTopic(String routingKey,String bindingKey){
        String[] routingKeyList = routingKey.split("\\.");
        String[] bindingKeyList = bindingKey.split("\\.");
        for(int i=0;i<bindingKeyList.length;i++){
            if(bindingKeyList[i].equals("*") || bindingKeyList[i].equals(routingKeyList[i])) continue;
            if(bindingKeyList[i].equals("#")) return true;
        }
        return routingKeyList.length == bindingKeyList.length;
    }
}
