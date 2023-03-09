
package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TopicService implements Service {
    private final static String POST = "POST";
    private final static String GET = "GET";
    private final ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentLinkedQueue<String>>> topics = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        Resp result = new Resp("", "404");
        if (POST.equals(req.httpRequestType())) {
            topics.getOrDefault(req.getSourceName(), new ConcurrentHashMap<>()).values().forEach(a -> a.add(req.getParam()));
        }
        if (GET.equals(req.httpRequestType())) {
            topics.putIfAbsent(req.getSourceName(), new ConcurrentHashMap<>());
            topics.getOrDefault(req.getSourceName(), new ConcurrentHashMap<>()).putIfAbsent(req.getParam(), new ConcurrentLinkedQueue<>());
            result = new Resp(topics.getOrDefault(req.getSourceName(), new ConcurrentHashMap<>())
                    .getOrDefault(req.getParam(), new ConcurrentLinkedQueue<>())
                    .peek() == null ? "" : topics.getOrDefault(req.getSourceName(), new ConcurrentHashMap<>())
                    .getOrDefault(req.getParam(), new ConcurrentLinkedQueue<>()).poll(), "202");
        }
        return result;
    }
}
