package com.ssm.activity.domain.dto;

import lombok.Data;

@Data
public class UserRespDTO {

    private String id;

    //用户名
    private String userName;

    //学号
    private String stuNo;

    //mima
    private String password;

    //手机号
    private String telNum;

    //邮箱
    private String email;

    //是否为管理员 0：不是 1：是
    private int isAdmin;

    //参与活动数
    private int activityNum;
    //积分总和
    private int integralNum;
}
