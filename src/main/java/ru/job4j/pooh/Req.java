package ru.job4j.pooh;

import java.util.Arrays;
import java.util.List;

public class Req {

    private final String httpRequestType;
    private final String poohMode;
    private final String sourceName;
    private final String param;

    public Req(String httpRequestType, String poohMode, String sourceName, String param) {
        this.httpRequestType = httpRequestType;
        this.poohMode = poohMode;
        this.sourceName = sourceName;
        this.param = param;
    }

    public static Req of(String content) {
        List<String> data = Arrays.stream(content.split(System.lineSeparator())).toList();
        String[] header = data.get(0).split("/");
        String httpRequestType = header[0].trim();
        String poohMode = header[1];
        String sourceName = header[2].split(" ")[0];
        String param = data.size() != 4 ? data.get(data.size() - 1) :
                header.length == 5 ? header[3].substring(0, header[3].length() - 5) : "";
        return new Req(httpRequestType, poohMode, sourceName, param);
    }

    public String httpRequestType() {
        return httpRequestType;
    }

    public String getPoohMode() {
        return poohMode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getParam() {
        return param;
    }
}
