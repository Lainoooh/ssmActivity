package com.ssm.activity.controller;

import com.ssm.activity.domain.WebResult;
import com.ssm.activity.domain.dto.ActivityRespDTO;
import com.ssm.activity.service.ActivityService;
import com.ssm.activity.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 活动积分管理
 */
@RestController
@RequestMapping("/integral")
public class IntegralController {
    @Autowired
    IntegralService integralService;

    /**
     * 学生参加活动
     * 异步请求，返回的json，采用的注解是@ResponseBody，与AdminController不一样，那边返回的是model视图（freemarker模板引擎，可以下来学习下）
     * @param activityId
     * @param userId
     */
    @PostMapping("/add")
    @ResponseBody
    public WebResult<Boolean> addIntegral(String activityId,String userId){
        return integralService.addIntegral(activityId,userId);
    }

    /**
     * 更改活动获得奖项
     * 异步请求，返回的json，采用的注解是@ResponseBody，与AdminController不一样，那边返回的是model视图（freemarker模板引擎，可以下来学习下）
     * @param id
     * @param rank
     */
    @PostMapping("/update")
    @ResponseBody
    public WebResult<Boolean> updateIntegral(String id,Integer rank){
        return integralService.updateIntegral(id,rank);
    }

}
