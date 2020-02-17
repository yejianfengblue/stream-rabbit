package com.yejianfengblue.spring.cloud.dataflow.usagecostloggerrabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @author yejianfengblue
 */
@EnableBinding(Sink.class)
public class UsageCostLogger {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @StreamListener(Sink.INPUT)
    public void process(UsageCostDetail usageCostDetail) {
        logger.info(usageCostDetail.toString());
    }
}
