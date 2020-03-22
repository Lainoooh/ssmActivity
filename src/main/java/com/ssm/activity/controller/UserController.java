package com.ssm.activity.controller;

import com.ssm.activity.domain.WebResult;
import com.ssm.activity.domain.dto.ActivityRespDTO;
import com.ssm.activity.domain.dto.UserRespDTO;
import com.ssm.activity.domain.po.Apply;
import com.ssm.activity.domain.po.Integral;
import com.ssm.activity.domain.po.User;
import com.ssm.activity.dao.UserDao;
import com.ssm.activity.service.ActivityService;
import com.ssm.activity.service.ApplyService;
import com.ssm.activity.service.IntegralService;
import com.ssm.activity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 学生跳转页面
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ActivityService activityService;

    @Autowired
    IntegralService integralService;

    @Autowired
    ApplyService applyService;

    /**
     * 跳转至-学生主页
     *
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String index(Model model, HttpServletRequest request,Integer year,Integer semester) {
        //1.从session里取当前登录user对象
        User userLogin = getLoginUser(model, request);
        model.addAttribute("index", "index");
        //2.查询 学生信息
        String userId = userLogin.getId();
        UserRespDTO userInfo= userService.getById(userId);
        model.addAttribute("userInfo", userInfo);
        //3. 根据userId查询参加的活动积分列表
        List<Integral> integralList = integralService.findListByUserId(userId, year, semester);
        model.addAttribute("integralList", integralList);
        //4.计算当前查询结果后的积分和活动数
        int activityNum = integralList.size();
        int integralNum = 0;
        //初始化学年学期的下拉列表数据 以year（如2019）为key,Set集合可以去重
        Set<String> yearSet = new HashSet<>();
        yearSet.add(2019 + "");
        yearSet.add(2020 + "");
        for (Integral integral : integralList) {
            //添加查询年份
            yearSet.add(integral.getYear());
            if (integral.getIntegral() != null) {
                integralNum += integral.getIntegral();
            }
        }
        model.addAttribute("activityNum", activityNum);
        model.addAttribute("integralNum", integralNum);
        if (CollectionUtils.isEmpty(yearSet)) {
            yearSet.add(year+"");
        }
        model.addAttribute("yearSet", yearSet);
        model.addAttribute("year", year+"");
        model.addAttribute("semester", semester);

        return "student-index";
    }

    /**
     * 跳转至-学生-活动管理列表
     *
     * @param model
     * @return
     */
    @GetMapping("/activity/list")
    public String activityList(Model model, HttpServletRequest request) {
        //1.从session里取当前登录user对象
        getLoginUser(model, request);
        model.addAttribute("index", "activity");
        //2.查询活动列表数据
        WebResult<List<ActivityRespDTO>> activityList = activityService.findActivityList(null,"stuNum");
        model.addAttribute("activityList", activityList.getResult());
        return "student-activity";
    }

    /**
     * 跳转至-学生-活动详情
     *
     * @param model
     * @return
     */
    @GetMapping("/activity/detail")
    public String activityDetail(Model model, HttpServletRequest request,String activityId,String keyWord) {
        return toActivityDetail(model, request, activityId,keyWord);
    }
    /**
     * 跳转至 活动详情页面
     * @param model
     * @param request
     * @param activityId
     * @param keyWord
     * @return
     */
    private String toActivityDetail(Model model, HttpServletRequest request, String activityId,String keyWord) {
        model.addAttribute("keyWord", keyWord);
        //1.从session里取当前登录user对象
        User userLogin = getLoginUser(model, request);
        //2.页面标志
        model.addAttribute("index", "activity");
        //3.根据id获取活动对象
        ActivityRespDTO activity = activityService.getById(activityId);
        if (activity == null) {
            //如果没找到活动对象，则跳转至活动列表
            return activityList(model, request);
        }
        model.addAttribute("activity", activity);
        //4.获取全部的学生
        List<UserRespDTO> userList = userService.findUserList(keyWord, null, null);
        //5.从积分活动表中，获取参与了该活动的学生集合
        List<Integral> integralList = integralService.findListByActivityId(activity.getId(),keyWord);
        //6.去重，获取未参与该活动的学生
        //这个是没有参与活动的学生列表
        List<UserRespDTO> userNoActivityList;
        //先判断这两个集合是否为空
        //如果用户列表为空，那么直接跳过
        //如果参与活动的用户列表为空，那么说明该活动没有学生参与
        if (!CollectionUtils.isEmpty(userList) && !CollectionUtils.isEmpty(integralList)) {
            userNoActivityList = new ArrayList<>();
            //使用integralList，抽取成以 userId为key，Integral活动积分对象为value的map集合
            Map<String, Integral> userActivityMap = new HashMap<>();
            for (Integral integral : integralList) {
                userActivityMap.put(integral.getUserId(), integral);
            }
            for (UserRespDTO user : userList) {
                //判断，如果在userActivityMap（参与活动的学生map）中，找不到这个User学生，则向userNoActivityList中添加
                if (!userActivityMap.containsKey(user.getId())) {
                    userNoActivityList.add(user);
                }
            }

        }else{
            userNoActivityList = userList;
        }
        //放入到域对象中
        model.addAttribute("userNoActivityList", userNoActivityList);
        model.addAttribute("integralList", integralList);

        //判断当前学生是否参与该活动
        int isActive = 0;
        String integralId = "";
        Integral byActivityIdAndUserId = integralService.getByActivityIdAndUserId(activity.getId(), userLogin.getId());
        if(byActivityIdAndUserId!=null){
            //只有当活动积分列表的用户id列表里有当前登录的userId，才能是参与
            isActive = 1;
            integralId = byActivityIdAndUserId.getId();
        }
        model.addAttribute("isActive", isActive);
        model.addAttribute("integralId", integralId);
        return "student-activity-detail";
    }

    /**
     * 学生取消报名
     * @param model
     * @param request
     * @param activityId
     * @param integralId
     */
    @GetMapping("/integral/delete")
    public String deleteIntegral(Model model, HttpServletRequest request,String activityId,String integralId){
        integralService.deleteIntegral(integralId);
        return toActivityDetail(model, request, activityId,"");
    }

    /**
     * 跳转至-学生-申报列表
     *
     * @param model
     * @return
     */
    @GetMapping("/apply/list")
    public String applyList(Model model, HttpServletRequest request) {
        //1.从session里取当前登录user对象
        User loginUser = getLoginUser(model, request);
        model.addAttribute("index", "apply");
        //2.查询申报列表
        List<Apply> applyList = applyService.findApplyList(loginUser.getId());
        model.addAttribute("applyList", applyList);
        return "student-apply";
    }

    /**
     * 从session里取当前登录user对象
     * @param model
     * @param request
     */
    private User getLoginUser(Model model, HttpServletRequest request) {
        User userLogin = (User) request.getSession().getAttribute("userLogin");
        model.addAttribute("user", userLogin);
        return userLogin;
    }
}
