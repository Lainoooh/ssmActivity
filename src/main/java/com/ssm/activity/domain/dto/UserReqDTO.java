package com.ssm.activity.domain.dto;

import com.github.liaochong.myexcel.core.annotation.ExcelColumn;
import lombok.Data;

import java.util.Date;

/**
 * 用户，请求类
 */
@Data
public class UserReqDTO {

    private String id;

    //用户名
    private String userName;

    //密码 默认密码111111
    private String password;
    private String repassword;
    private String code;

    //学号
    private String stuNo;

    //手机号
    private String telNum;

    //邮箱
    private String email;

    //是否为管理员 0：不是 1：是
    private int isAdmin;

    private Date createTime;

    private Date updateTime;
}
