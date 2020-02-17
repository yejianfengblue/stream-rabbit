package com.yejianfengblue.spring.cloud.dataflow.usagecostloggerrabbit;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.support.MessageBuilder;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@SpringBootTest
class UsageCostLoggerRabbitApplicationTests {

    @Autowired
    protected Sink sink;

    @Autowired
    protected UsageCostLogger usageCostLogger;

    @Test
    void testUsageCostLogger() throws Exception {
        ArgumentCaptor<UsageCostDetail> captor = ArgumentCaptor.forClass(UsageCostDetail.class);
        this.sink.input().send(MessageBuilder.withPayload("{\"userId\":\"user3\",\"callCost\":10.100000000000001,\"dataCost\":25.1}").build());
        verify(this.usageCostLogger).process(captor.capture());
    }

    @EnableAutoConfiguration
    @EnableBinding(Sink.class)
    static class TestConfig {

        // Override `UsageCostLogger` bean for spying.
        @Bean
        @Primary
        public UsageCostLogger usageCostLogger() {
            return spy(new UsageCostLogger());
        }
    }
}
