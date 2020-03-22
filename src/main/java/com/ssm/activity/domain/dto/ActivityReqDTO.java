package com.ssm.activity.domain.dto;

import com.github.liaochong.myexcel.core.annotation.ExcelColumn;
import lombok.Data;

import java.util.Date;

/**
 * 活动类 对应表 t_activity
 */
@Data
public class ActivityReqDTO {

    //唯一主键
    private String id;

    //活动名称
    @ExcelColumn(index = 0)
    private String activityName = "名称模板";

    //活动描述
    @ExcelColumn(index = 1)
    private String activityDsc = "描述模板";

    //活动地点
    @ExcelColumn(index = 2)
    private String activityPlace = "地点模板";

    //活动开始时间
    private String startTime;

    //活动结束时间
    private String endTime;

    @ExcelColumn(index = 3)
    //是否为竞赛类 0：否 1：是
    private String isRaceString = "否";

    //是否为竞赛类 0：否 1：是
    private int isRace;

    //活动状态 0：报名中 1：进行中 2：已结束
    private int state;

    //一等奖
    @ExcelColumn(index = 4)
    private Integer prize1 = 4;

    //二等奖
    @ExcelColumn(index = 5)
    private Integer prize2 = 3;

    //三等奖
    @ExcelColumn(index = 6)
    private Integer prize3 = 2;

    //参与奖 ，如果不为竞赛类活动，则都是参与奖
    @ExcelColumn(index = 7)
    private Integer prize4 = 1;


    private Date createTime;

    private Date updateTime;

}
