package com.yejianfengblue.spring.cloud.dataflow.usagecostprocessorrabbit;

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
