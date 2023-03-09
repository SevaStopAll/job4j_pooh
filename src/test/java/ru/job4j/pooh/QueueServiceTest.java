package ru.job4j.pooh;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class QueueServiceTest {

    @Test
    public void whenPostThenGetQueue() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "temperature=18";
        queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod)
        );
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        assertThat(result.text()).isEqualTo("temperature=18");
    }

    @Test
    public void whenPostThenGetQueueWithManyPosts() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "temperature=18";
        String paramForPostMethod2 = "temperature=19";
        String paramForPostMethod3 = "temperature=20";
        String paramForPostMethod4 = "temperature=22";
        String paramForPostMethod5 = "temperature=24";
        String paramForPostMethod6 = "temperature=-5";
        queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod)
        );
        queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod2)
        );
        queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod3)
        );
        queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod4)
        );
        queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod5)
        );
        queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod6)
        );
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        assertThat(result.text()).isEqualTo("temperature=18");
    }
}