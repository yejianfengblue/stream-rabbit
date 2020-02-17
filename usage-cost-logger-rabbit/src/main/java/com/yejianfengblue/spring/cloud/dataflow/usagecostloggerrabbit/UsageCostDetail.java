package com.yejianfengblue.spring.cloud.dataflow.usagecostloggerrabbit;

import lombok.Data;

/**
 * @author yejianfengblue
 */
@Data
public class UsageCostDetail {

    private String userId;

    private double callCost;

    private double dataCost;
}
