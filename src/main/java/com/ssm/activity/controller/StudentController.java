package com.ssm.activity.controller;

import com.ssm.activity.domain.WebResult;
import com.ssm.activity.domain.po.User;
import com.ssm.activity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 学生管理
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    UserService userService;

    /**
     * POST请求管理员-学生添加或修改
     *
     * @return
     */
    @PostMapping("/add")
    public WebResult<Boolean> addOrFlush(User reqDTO) {
        //1.添加学生
        return userService.addOrFlush(reqDTO);
    }

    /**
     * 根据id删除学生
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public WebResult<Boolean> deleteActivity(String id){
        return userService.delete(id);
    }

}
