package com.ssm.activity.domain.po;

import com.github.liaochong.myexcel.core.annotation.ExcelColumn;
import lombok.Data;

import java.util.Date;
import java.util.Scanner;

/**
 * 用户，对应的 t_user
 */
@Data
public class User {

    private String id;

    //用户名
    @ExcelColumn(index = 0)
    private String userName;

    //密码 默认密码111111
    private String password = "111111";


    //学号
    @ExcelColumn(index = 1)
    private String stuNo;

    //手机号
    @ExcelColumn(index = 2)
    private String telNum;

    //邮箱
    @ExcelColumn(index = 3)
    private String email;

    //是否为管理员 0：不是 1：是
    private int isAdmin;

    private Date createTime;

    private Date updateTime;

}
