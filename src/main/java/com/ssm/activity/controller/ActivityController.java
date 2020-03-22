package com.ssm.activity.controller;

import com.ssm.activity.domain.WebResult;
import com.ssm.activity.domain.dto.ActivityReqDTO;
import com.ssm.activity.domain.dto.ActivityRespDTO;
import com.ssm.activity.domain.po.Activity;
import com.ssm.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 活动管理
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    ActivityService activityService;

    /**
     * 查询活动列表
     * @return
     */
    @GetMapping("/findActivityList")
    public WebResult<List<ActivityRespDTO>> findActivityList(){
        return activityService.findActivityList(null,null);
    }

    /**
     * 根据id删除活动
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public WebResult<Boolean> deleteActivity(String id){
        return activityService.deleteActivity(id);
    }


    /**
     * POST请求管理员-活动添加或修改
     *
     * @return
     */
    @PostMapping("/add")
    public WebResult<Boolean> activityAdd(ActivityReqDTO reqDTO) {
        //1.添加活动
        return activityService.addOrFlush(reqDTO);
    }
}
