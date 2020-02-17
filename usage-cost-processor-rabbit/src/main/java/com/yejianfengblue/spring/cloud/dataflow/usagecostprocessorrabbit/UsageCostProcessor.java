package com.yejianfengblue.spring.cloud.dataflow.usagecostprocessorrabbit;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * @author yejianfengblue
 */
@EnableBinding(Processor.class)
public class UsageCostProcessor {

    private final double ratePerSecond = 0.1;

    private final double ratePerMB = 0.05;

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public UsageCostDetail processUsageCost(UsageDetail usageDetail) {

        UsageCostDetail usageCostDetail = new UsageCostDetail();
        usageCostDetail.setUserId(usageDetail.getUserId());
        usageCostDetail.setCallCost(usageDetail.getDuration() * this.ratePerSecond);
        usageCostDetail.setDataCost(usageDetail.getData() * this.ratePerMB);
        return usageCostDetail;
    }

}
