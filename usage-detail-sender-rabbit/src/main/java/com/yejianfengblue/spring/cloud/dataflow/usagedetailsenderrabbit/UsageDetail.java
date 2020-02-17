package com.yejianfengblue.spring.cloud.dataflow.usagedetailsenderrabbit;

import lombok.Data;

/**
 * @author yejianfengblue
 */
@Data
public class UsageDetail {

    private String userId;

    private long duration;

    private long data;
}
