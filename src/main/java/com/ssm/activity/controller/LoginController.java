package com.ssm.activity.controller;

import com.ssm.activity.Utils.IDUtils;
import com.ssm.activity.dao.UserDao;
import com.ssm.activity.domain.WebResult;
import com.ssm.activity.domain.dto.ActivityRespDTO;
import com.ssm.activity.domain.dto.UserReqDTO;
import com.ssm.activity.domain.dto.UserRespDTO;
import com.ssm.activity.domain.po.Apply;
import com.ssm.activity.domain.po.Integral;
import com.ssm.activity.domain.po.User;
import com.ssm.activity.service.ActivityService;
import com.ssm.activity.service.ApplyService;
import com.ssm.activity.service.IntegralService;
import com.ssm.activity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/activity")
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    ActivityService activityService;

    @Autowired
    IntegralService integralService;

    @Autowired
    ApplyService applyService;

    /**
     * 跳转至-登录界面
     *
     * @param model
     * @return
     */
    @GetMapping("/login")
    public String toLogin(Model model, HttpServletRequest request) {
        return "login";
    }

    @GetMapping("/logout")
    public String toLogout(Model model, HttpServletRequest request) {
        request.getSession().setAttribute("userLogin", null);
        return "login";
    }

    @GetMapping("/forget")
    public String toForget(Model model, HttpServletRequest request) {
        request.getSession().setAttribute("userLogin", null);
        request.getSession().setAttribute("code", null);
        model.addAttribute("errorMsg", null);
        model.addAttribute("code", null);
        return "login-forgetPwd";
    }

    /**
     * 获取验证码
     * @param model
     * @param request
     * @param user
     * @return
     */
    @GetMapping("/getCode")
    public String getCode(Model model, HttpServletRequest request, User user) {
        model.addAttribute("user", user);
        String errorMsg = "tel：";
        String stuNo = user.getStuNo();
        String userName = user.getUserName();
        User byStuNo = userService.getByStuNo(stuNo);

        if (byStuNo != null) {
            if (byStuNo.getUserName().equals(userName)) {
                //生成验证码
                String code = IDUtils.getCode(4);
                model.addAttribute("user", byStuNo);
                request.getSession().setAttribute("code", code);
                model.addAttribute("code", code);
                return "login-forgetPwd";
            }
        }
        //获取管理员电话
        errorMsg += userService.getById("1").getTelNum();
        model.addAttribute("errorMsg", errorMsg);
        return "login-forgetPwd";
    }

    /**
     * 重置密码
     * @param model
     * @param request
     * @param user
     * @return
     */
    @PostMapping("/password")
    @ResponseBody
    public WebResult<String> resetPassword(Model model, HttpServletRequest request, UserReqDTO user) {
        String code = user.getCode();
        String password = user.getPassword();
        String repassword = user.getRepassword();

        if (user.getPassword().length() != 6) {
            return WebResult.fail("密码格式错误，请填写6位数字！");
        }
        try {
            //校验是否为数字
            Integer reqPassword = Integer.valueOf(user.getPassword());
        } catch (Exception e) {
            return WebResult.fail("密码格式错误，请填写6位数字");
        }

        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(repassword)) {
            return WebResult.fail("请填写密码！");
        }
        if (!password.equals(repassword)) {
            return WebResult.fail("两次密码不一致！");
        }
        String  sessionCode = (String) request.getSession().getAttribute("code");
        if(StringUtils.isEmpty(sessionCode)||!sessionCode.equals(code)){
            return WebResult.fail("验证码错误！");
        }
        //修改密码
        userService.resetPassword(user);
        //记录申报
        Apply apply = new Apply();
        apply.setUserId(user.getId());
        apply.setApplyType(3);
        apply.setIsDel(1);
        apply.setDelName("自动处理");
        apply.setUpdateTime(new Date());
        applyService.add(apply);

        return WebResult.ok("修改密码成功！");
    }

    /**
     * 登录
     * @param model
     * @param request
     * @param user
     * @return
     */
    @PostMapping("/login")
    public String login(Model model, HttpServletRequest request,User user) {
        model.addAttribute("user", user);
        String stuNo = user.getStuNo().trim();
        String password = user.getPassword().trim();
        String userName = user.getUserName().trim();
        //校验登录数据 ,如果不通过，直接返回登录页面
        if (checkParam(model, user, stuNo, password, userName)) return "login";
        //根据学号或工号查询
        User userLogin = userService.getByStuNo(stuNo);
        if (userLogin != null) {

            if(!userLogin.getUserName().equals(userName)){
                model.addAttribute("errorMsg", "姓名错误");
                return "login";
            }
            if (!userLogin.getPassword().equals(password)) {
                model.addAttribute("errorMsg", "密码错误");
                return "login";
            }
            //1.此处说明登录成功,将User对象放进session
            request.getSession().setAttribute("userLogin", userLogin);
            //2.此处判断是否为管理员
            int isAdmin = userLogin.getIsAdmin();
            if (isAdmin == 1) {
                //3.跳转至管理员首页
                model.addAttribute("user", userLogin);
                return toAdminIndex(model);
            }else{
                //3.跳转至学生首页
                return toUserIndex(userLogin, model, request, null, null);
            }
        }
        model.addAttribute("errorMsg", "学生信息不存在，请联系管理员");
        return "login";
    }

    /**
     * 校验登录数据
     * @param model
     * @param user
     * @param stuNo
     * @param password
     * @param userName
     * @return
     */
    private boolean checkParam(Model model, User user, String stuNo, String password, String userName) {
        if (user == null) {
            model.addAttribute("errorMsg", "请输入正确信息！");
            return true;
        }else if(StringUtils.isEmpty(userName)) {
            model.addAttribute("errorMsg", "请输入姓名！");
            return true;
        }else if(StringUtils.isEmpty(stuNo)) {
            model.addAttribute("errorMsg", "请输入学号或者工号！");
            return true;
        }else if(StringUtils.isEmpty(password)) {
            model.addAttribute("errorMsg", "请输入密码！");
            return true;
        } else if (stuNo.length() != 12) {
            model.addAttribute("errorMsg", "学号格式错误，请填写12位学号！");
            return true;
        } else if (password.length() != 6) {
            model.addAttribute("errorMsg", "密码格式错误，请填写6位数字");
            return true;
        }
        try {
            //校验是否为数字
            Integer reqPassword = Integer.valueOf(password);
        } catch (Exception e) {
            model.addAttribute("errorMsg", "密码格式错误，请填写6位数字");
            return true;
        }
        return false;
    }

    /**
     * 跳转至学生首页
     * @param userLogin
     * @param model
     * @param request
     * @param year
     * @param semester
     * @return
     */
    public String toUserIndex(User userLogin,Model model, HttpServletRequest request,Integer year,Integer semester) {
        //1.从session里取当前登录user对象
        model.addAttribute("user", userLogin);
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
     * 跳转至管理员首页
     * @param model
     * @return
     */
    public String toAdminIndex(Model model) {
        //1.从session里取当前登录user对象
        model.addAttribute("index", "index");
        //2.查询活动列表数据
        WebResult<List<ActivityRespDTO>> activityList = activityService.findActivityList(3,"stuNum");
        model.addAttribute("activityList", activityList.getResult());
        //3.查询 学生列表
        List<UserRespDTO> userList = userService.findUserList("",3,"integralNum");
        model.addAttribute("userList", userList);
        //4.总活动数
        int activityNum = activityService.findActivityList(null, null).getResult().size();
        model.addAttribute("activityNum", activityNum);
        //4.总学生数
        int stuNum = userService.findUserList(null, null, null).size();
        model.addAttribute("stuNum", stuNum);
        //5.总申报数
        int applyNum = applyService.findApplyList(null).size();
        model.addAttribute("applyNum", applyNum);
        return "admin-index";
    }
}
