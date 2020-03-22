package com.ssm.activity.domain.po;

import lombok.Data;

import java.util.Date;

/**
 * 申报记录，对应的 t_apply
 */
@Data
public class Apply {

    private String id;

    private String userId;

    private String activityId;

    private String activityName;

    //用户名
    private String userName;

    //学号
    private String stuNo;

    //手机号
    private String telNum;

    //邮箱
    private String email;

    //申报描述
    private String dsc;

    //申报类型 1：学生信息 2：活动信息 3：重置密码
    private int applyType;

    //是否处理：0：未处理 1：处理
    private int isDel;

    //处理人
    private String delName;

    private Date createTime;

    private Date updateTime;
}
