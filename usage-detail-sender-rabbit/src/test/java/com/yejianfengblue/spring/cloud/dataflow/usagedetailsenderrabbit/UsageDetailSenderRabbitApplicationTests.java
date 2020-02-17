package com.yejianfengblue.spring.cloud.dataflow.usagedetailsenderrabbit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsageDetailSenderRabbitApplicationTests {

    // spring-cloud-stream-test-support uses the Test binder to trace and test app's outbound and inbound messages.
    // MessageCollector stores messages in memory.
    @Autowired
    private MessageCollector messageCollector;

    @Autowired
    private Source source;

    @Test
    void testUsageDetailSender() throws Exception {
        Message message = this.messageCollector.forChannel(this.source.output()).poll(1, TimeUnit.SECONDS);
        String usageDetailJSON = message.getPayload().toString();
        assertTrue(usageDetailJSON.contains("userId"));
        assertTrue(usageDetailJSON.contains("duration"));
        assertTrue(usageDetailJSON.contains("data"));
    }
}
