package ru.job4j.pooh;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TopicServiceTest {

    @Test
    public void whenTopic() {
        TopicService topicService = new TopicService();
        String paramForPublisher = "temperature=18";
        String paramForSubscriber1 = "client407";
        String paramForSubscriber2 = "client6565";
        topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber1)
        );
        topicService.process(
                new Req("POST", "topic", "weather", paramForPublisher)
        );
        Resp result1 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber1)
        );
        Resp result2 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber2)
        );
        assertThat(result1.text()).isEqualTo("temperature=18");
        assertThat(result2.text()).isEqualTo("");
    }

    @Test
    public void whenTopicWithManyMessages() {
        TopicService topicService = new TopicService();
        String paramForPublisher = "temperature=18";
        String paramForPublisher2 = "wind=30";
        String paramForPublisher3 = "humidity=36";
        String paramForPublisher4 = "pressure=normal";
        String paramForSubscriber1 = "client407";
        String paramForSubscriber2 = "client6565";
        String paramForSubscriber3 = "client6565";
        String paramForSubscriber4 = "client6565";
        topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber1)
        );
        topicService.process(
                new Req("POST", "topic", "weather", paramForPublisher)
        );
        Resp result1 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber1)
        );
        Resp result2 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber2)
        );
        topicService.process(
                new Req("POST", "topic", "weather", paramForPublisher2)
        );
        Resp result3 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber2)
        );
        Resp result4 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber1)
        );
        topicService.process(
                new Req("POST", "topic", "weather", paramForPublisher3)
        );
        Resp result5 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber3)
        );
        topicService.process(
                new Req("POST", "topic", "weather", paramForPublisher4)
        );
        Resp result6 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber4)
        );
        assertThat(result1.text()).isEqualTo("temperature=18");
        assertThat(result2.text()).isEqualTo("");
        assertThat(result3.text()).isEqualTo("wind=30");
        assertThat(result4.text()).isEqualTo("wind=30");
        assertThat(result5.text()).isEqualTo("humidity=36");
        assertThat(result6.text()).isEqualTo("pressure=normal");
    }
}