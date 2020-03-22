package com.ssm.activity.controller;

import com.ssm.activity.domain.WebResult;
import com.ssm.activity.domain.po.Apply;
import com.ssm.activity.domain.po.User;
import com.ssm.activity.service.ApplyService;
import com.ssm.activity.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 申报管理
 */
@RestController
@RequestMapping("/apply")
public class ApplyController {
    @Autowired
    ApplyService applyService;

    /**
     * 学生申报
     * 异步请求，返回的json，采用的注解是@ResponseBody，与AdminController不一样，那边返回的是model视图（freemarker模板引擎，可以下来学习下）
     * @param apply
     */
    @PostMapping("/add")
    @ResponseBody
    public WebResult<Boolean> add(Apply apply){
        return applyService.add(apply);
    }

    /**
     * 管理员处理
     * 异步请求，返回的json，采用的注解是@ResponseBody，与AdminController不一样，那边返回的是model视图（freemarker模板引擎，可以下来学习下）
     * @param apply
     */
    @PostMapping("/update")
    @ResponseBody
    public WebResult<Boolean> updateIntegral(Apply apply,HttpServletRequest request){
        //从session里取当前登录user对象
//        User userLogin = (User) request.getSession().getAttribute("userLogin");

//        apply.setDelName(userLogin.getUserName());
        apply.setDelName("admin");

        return applyService.update(apply);
    }

}
