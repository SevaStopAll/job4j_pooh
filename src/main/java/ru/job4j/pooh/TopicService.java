
package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TopicService implements Service {
    private final static String POST = "POST";
    private final static String GET = "GET";
    private final ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentLinkedQueue<String>>> topics = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        if (POST.equals(req.httpRequestType()) && topics.containsKey(req.getSourceName())) {
            topics.get(req.getSourceName()).values().forEach(a -> a.add(req.getParam()));
            return new Resp(null, null);
        }
/*        if (topics.putIfAbsent(req.getSourceName(), new ConcurrentHashMap<>()))*/
        topics.get(req.getSourceName()).putIfAbsent(req.getParam(), new ConcurrentLinkedQueue<>());
        return new Resp(topics.get(req.getSourceName()).get(req.getParam()).poll(),  "202");
    }
}
