package com.yejianfengblue.spring.cloud.dataflow.usagedetailsenderrabbit;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Random;

/**
 * @author yejianfengblue
 */
@EnableScheduling
// @EnableBinding binds app to messaging middleware
// Source defines an output channel named "output". In the case of RabbitMQ, message sent to this "output" channel
// are in turn sent to the RabbitMQ message broker by using a TopicExchange
@EnableBinding(Source.class)
@RequiredArgsConstructor
public class UsageDetailSender {

    private final Source source;

    private String[] users = {"user1", "user2", "user3", "user4", "user5"};

    @Scheduled(fixedDelay = 1000)
    public void sendEvents() {

        UsageDetail usageDetail = new UsageDetail();

        usageDetail.setUserId(this.users[new Random().nextInt(5)]);
        usageDetail.setDuration(new Random().nextInt(300));
        usageDetail.setData(new Random().nextInt(700));
        this.source.output().send(MessageBuilder.withPayload(usageDetail).build());
    }
}
