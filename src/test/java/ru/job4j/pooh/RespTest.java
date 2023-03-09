package ru.job4j.pooh;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RespTest {

    @Test
    void whenTopicStatus404() {
            TopicService topicService = new TopicService();
            String paramForPublisher = "temperature=18";
            Resp result1 = topicService.process(
                    new Req("POST", "topic", "weather", paramForPublisher)
            );
            assertThat(result1.status()).isEqualTo("404");
    }

    @Test
    void whenQueueStatus404() {
        QueueService queueService = new QueueService();
        String paramForPublisher = "temperature=18";
        Resp result1 = queueService.process(
                new Req("POST", "topic", "weather", paramForPublisher)
        );
        assertThat(result1.status()).isEqualTo("404");
    }

    @Test
    void whenQueueStatus202() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "time=18.00";
        queueService.process(
                new Req("POST", "queue", "time", paramForPostMethod)
        );
        Resp result = queueService.process(
                new Req("GET", "queue", "time", null)
        );
        assertThat(result.status()).isEqualTo("202");
    }

    @Test
    void whenTopicStatus202() {
        TopicService topicService = new TopicService();
        String paramForPublisher = "temperature=18";
        String paramForSubscriber = "client407";
        topicService.process(
                new Req("POST", "topic", "weather", paramForPublisher)
        );
        Resp result = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber)
        );
        assertThat(result.status()).isEqualTo("202");
    }

}