package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {
    private final static String POST = "POST";
    private final static String GET = "GET";
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        if (POST.equals(req.httpRequestType())) {
            queue.putIfAbsent(req.getSourceName(), new ConcurrentLinkedQueue<>());
            queue.getOrDefault(req.getSourceName(), new ConcurrentLinkedQueue<>()).add(req.getParam());
            return new Resp("", "404");
        }
        return new Resp(queue.getOrDefault(req.getSourceName(), new ConcurrentLinkedQueue<>()).poll(), "202");
    }
}
