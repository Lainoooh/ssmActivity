package com.ssm.activity.domain.po;

import com.github.liaochong.myexcel.core.annotation.ExcelColumn;
import lombok.Data;

import java.util.Date;

/**
 * 活动类 对应表 t_activity
 */
@Data
public class Activity {

    //唯一主键
    private String id;

    //活动名称
    private String activityName;

    //活动描述
    private String activityDsc;

    //活动地点
    private String activityPlace;

    //活动开始时间
    private Date startTime;

    //活动结束时间
    private Date endTime;

    //是否为竞赛类 0：否 1：是
    private int isRace;

    //活动状态 0：报名中 1：进行中 2：已结束
    private int state;

    //一等奖
    private int prize1;

    //二等奖
    private int prize2;

    //三等奖
    private int prize3;

    //参与奖 ，如果不为竞赛类活动，则都是参与奖
    private int prize4;

    private Date createTime;

    private Date updateTime;


}
