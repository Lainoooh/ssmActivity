package com.ssm.activity.domain.po;

import lombok.Data;

import java.util.Date;

/**
 * 积分活动表，对应的 t_integral
 */
@Data
public class Integral {

    private String id;

    //用户id
    private String userId;

    //用户名
    private String userName;

    //用户学号
    private String stuNo;

    //活动id
    private String activityId;

    //活动名称
    private String activityName;

    //活动时间 记录的是活动开始时间
    private String activityTime;

    //活动名次
    private Integer rank;

    //获得积分
    private Integer integral;

    //记录年份，查询用
    private String year;

}
