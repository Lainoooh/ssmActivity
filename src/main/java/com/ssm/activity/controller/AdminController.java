package com.ssm.activity.controller;

import com.github.liaochong.myexcel.core.*;
import com.github.liaochong.myexcel.utils.AttachmentExportUtil;
import com.ssm.activity.Utils.DateUtils;
import com.ssm.activity.dao.IntegralDao;
import com.ssm.activity.domain.WebResult;
import com.ssm.activity.domain.dto.ActivityReqDTO;
import com.ssm.activity.domain.dto.ActivityRespDTO;
import com.ssm.activity.domain.dto.UserRespDTO;
import com.ssm.activity.domain.po.Activity;
import com.ssm.activity.domain.po.Apply;
import com.ssm.activity.domain.po.Integral;
import com.ssm.activity.domain.po.User;
import com.ssm.activity.dao.UserDao;
import com.ssm.activity.service.ActivityService;
import com.ssm.activity.service.ApplyService;
import com.ssm.activity.service.IntegralService;
import com.ssm.activity.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 管理员跳转页面
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    ActivityService activityService;

    @Autowired
    IntegralService integralService;

    @Autowired
    ApplyService applyService;
    /**
     * 跳转至-管理员主页
     *
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String index(Model model, HttpServletRequest request) {
        //1.从session里取当前登录user对象
        User loginUser = getLoginUser(model, request);
        if (loginUser == null) {
            return "login";
        }
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

    /**
     * 跳转至-管理员-学生管理列表
     * @param model
     * @return
     */
    @GetMapping("/student/list")
    public String studentList(Model model, HttpServletRequest request) {
        //1.从session里取当前登录user对象
        User loginUser = getLoginUser(model, request);
        if (loginUser == null) {
            return "login";
        }
        model.addAttribute("index", "student");
        //2.查询 学生列表
        List<UserRespDTO> userList = userService.findUserList("",null,null);
        model.addAttribute("userList", userList);
        return "admin-student";
    }




    /**
     * 管理员-学生Excel导入
     *
     * @param model
     * @return
     */
    @PostMapping("/student/excel/import")
    public String studentExcelImport(Model model, HttpServletRequest request, MultipartFile file) {
        //1.从session里取当前登录user对象
        User loginUser = getLoginUser(model, request);
        if (loginUser == null) {
            return "login";
        }

        // 方式一：全部读取后处理
        List<User> importList = null;
        try {
            //工具类处理Excel导入
            importList = SaxExcelReader.of(User.class)
                    .sheet(0) // 0代表第一个，如果为0，可省略该操作，也可sheet("名称")读取
                    .rowFilter(row -> row.getRowNum() > 0) // 如无需过滤，可省略该操作，0代表第一行
                    .read(file.getInputStream());// 可接收inputStream
            //插入数据库
            WebResult<Boolean> importResult = userService.insertBatch(importList);
            if(!importResult.isSuccess()){
                String error = importResult.getError();
                model.addAttribute("errorMsg", error);
            }
        } catch (Exception e) {
            model.addAttribute("errorMsg", "导入失败");
        }

        model.addAttribute("index", "student");
        //3.查询 学生列表
        List<UserRespDTO> userList = userService.findUserList("",null,null);
        model.addAttribute("userList", userList);
        return "admin-student";
    }

    /**
     * 学生下载Excel模板
     * @param response
     * @return
     * @throws Exception
     */
    @GetMapping("/student/export/template")
    public WebResult<Boolean> studentTemplate(HttpServletResponse response) throws Exception{
        List<User> temp = new ArrayList<>();
        User req = new User();
        req.setUserName("姓名模板");
        req.setStuNo("121212121212");
        req.setTelNum("12312341234");
        req.setEmail("xx@qq.com");
        temp.add(req);
        List<String> titiles = new ArrayList<>();
        titiles.add("用户名（必填）");
        titiles.add("学号（必填）");
        titiles.add("手机号（必填）");
        titiles.add("邮箱（必填）");

        try (ExcelBuilder excelBuilder = new FreemarkerExcelBuilder()) {
            Map<String,Object> rstMap=new HashMap<>();
            rstMap.put("list", temp);
            rstMap.put("sheetName", "学生信息导入");
            rstMap.put("titles", titiles);
            Workbook workbook = excelBuilder.template("/excel/student.ftl").workbookType(WorkbookType.XLSX).build(rstMap);
            AttachmentExportUtil.export(workbook, "freemarker_excel", response);
        }
        return null;
    }

    /**
     * 管理员-活动Excel导入
     *
     * @param model
     * @return
     */
    @PostMapping("/activity/excel/import")
    public String activityExcelImport(Model model, HttpServletRequest request, MultipartFile file) {
        //1.从session里取当前登录user对象
        User loginUser = getLoginUser(model, request);
        if (loginUser == null) {
            return "login";
        }

        // 方式一：全部读取后处理
        List<ActivityReqDTO> importList = null;
        try {
            //工具类处理Excel导入
            importList = SaxExcelReader.of(ActivityReqDTO.class)
                    .sheet(0) // 0代表第一个，如果为0，可省略该操作，也可sheet("名称")读取
                    .rowFilter(row -> row.getRowNum() > 0) // 如无需过滤，可省略该操作，0代表第一行
                    .read(file.getInputStream());// 可接收inputStream
            //插入数据库
            WebResult<Boolean> importResult = activityService.insertBatch(importList);
            if(!importResult.isSuccess()){
                String error = importResult.getError();
                model.addAttribute("errorMsg", error);
            }
        } catch (Exception e) {
            model.addAttribute("errorMsg", "导入失败");
        }

        model.addAttribute("index", "activity");
        //2.查询活动列表数据
        WebResult<List<ActivityRespDTO>> activityList = activityService.findActivityList(null,null);
        model.addAttribute("activityList", activityList.getResult());
        return "admin-activity";
    }


    /**
     * 活动下载Excel模板
     * @param response
     * @return
     * @throws Exception
     */
    @GetMapping("/activity/export/template")
    public WebResult<Boolean> template(HttpServletResponse response) throws Exception{
        List<ActivityReqDTO> temp = new ArrayList<>();
        ActivityReqDTO activityReqDTO = new ActivityReqDTO();
        temp.add(activityReqDTO);
        List<String> titiles = new ArrayList<>();
        titiles.add("活动名称（必填）");
        titiles.add("活动描述");
        titiles.add("活动地点");
//        titiles.add("活动开始时间（必填）");
//        titiles.add("活动结束时间（必填）");
        titiles.add("是否为竞赛类(是&否)（必填）");
        titiles.add("一等奖");
        titiles.add("二等奖");
        titiles.add("三等奖");
        titiles.add("参与奖（必填）");

        try (ExcelBuilder excelBuilder = new FreemarkerExcelBuilder()) {
            Map<String,Object> rstMap=new HashMap<>();
            rstMap.put("list", temp);
            rstMap.put("sheetName", "活动信息导入");
            rstMap.put("titles", titiles);
            Workbook workbook = excelBuilder.template("/excel/activity.ftl").workbookType(WorkbookType.XLSX).build(rstMap);
            AttachmentExportUtil.export(workbook, "freemarker_excel", response);
        }
        return null;
    }

    /**
     * GET请求跳转至-管理员-学生添加或修改
     *
     * @param model
     * @return
     */
    @GetMapping("/student/add")
    public String toStudentAdd(Model model, HttpServletRequest request,String id) {
        //1.从session里取当前登录user对象
        User loginUser = getLoginUser(model, request);
        if (loginUser == null) {
            return "login";
        }
        //2.页面标志
        model.addAttribute("index", "student");
        if(!StringUtils.isEmpty(id)){
            UserRespDTO student = userService.getById(id);
            //3.跳转添加修改页面，进行数据初始化
            model.addAttribute("student", student);
        }
        return "admin-student-add";
    }

    /**
     * 跳转至-管理员-学生管理详情页
     * @param model
     * @param request
     * @param userId
     * @param year 学年
     * @param semester 学期
     * @return
     */
    @GetMapping("/student/info")
    public String studentInfo(Model model, HttpServletRequest request,String userId,Integer year,Integer semester) {
        //1.从session里取当前登录user对象
        User loginUser = getLoginUser(model, request);
        if (loginUser == null) {
            return "login";
        }
        model.addAttribute("index", "student");
        //2.查询 学生信息
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
        return "admin-student-info";
    }

    /**
     * 跳转至-管理员-活动管理列表
     *
     * @param model
     * @return
     */
    @GetMapping("/activity/list")
    public String activityList(Model model, HttpServletRequest request) {
        //1.从session里取当前登录user对象
        User loginUser = getLoginUser(model, request);
        if (loginUser == null) {
            return "login";
        }
        model.addAttribute("index", "activity");
        //2.查询活动列表数据
        WebResult<List<ActivityRespDTO>> activityList = activityService.findActivityList(null,null);
        model.addAttribute("activityList", activityList.getResult());
        return "admin-activity";
    }

    /**
     * 跳转至-管理员-活动详情
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
        User loginUser = getLoginUser(model, request);
        if (loginUser == null) {
            return "login";
        }
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
        return "admin-activity-detail";
    }

    /**
     * GET请求跳转至-管理员-活动添加或修改
     *
     * @param model
     * @return
     */
    @GetMapping("/activity/add")
    public String toActivityAdd(Model model, HttpServletRequest request,String activityId,String keyWord) {
        //1.从session里取当前登录user对象
        User loginUser = getLoginUser(model, request);
        if (loginUser == null) {
            return "login";
        }
        //2.页面标志
        model.addAttribute("index", "activity");
        if(!StringUtils.isEmpty(activityId)){
            ActivityRespDTO activity = activityService.getById(activityId);
            //3.跳转添加修改页面，进行数据初始化
            model.addAttribute("activity", activity);
            String startDay = DateUtils.format(activity.getStartTime(), DateUtils.format_YYYY_MM_DD);
            String startTime = DateUtils.format(activity.getStartTime(), DateUtils.format_HH_MM_SS);
            String endDay = DateUtils.format(activity.getEndTime(), DateUtils.format_YYYY_MM_DD);
            String endTime = DateUtils.format(activity.getEndTime(), DateUtils.format_HH_MM_SS);
            model.addAttribute("startDay", startDay);
            model.addAttribute("startTime", startTime);
            model.addAttribute("endDay", endDay);
            model.addAttribute("endTime", endTime);
        }
        return "admin-activity-add";
    }

    /**
     * GET请求跳转至-管理员-活动添加或修改
     *
     * @param model
     * @return
     */
    @GetMapping("/activity/delete")
    public String toActivityDelete(Model model, HttpServletRequest request,String activityId,String keyWord) {
        //1.从session里取当前登录user对象
        User loginUser = getLoginUser(model, request);
        if (loginUser == null) {
            return "login";
        }
        //2.页面标志
        model.addAttribute("index", "activity");
        activityService.deleteActivity(activityId);
        return "admin-activity";
    }



    /**
     * 跳转至-管理员-积分管理列表
     *
     * @param model
     * @return
     */
    @GetMapping("/integral/list")
    public String integralList(Model model, HttpServletRequest request) {
        //1.从session里取当前登录user对象
        User loginUser = getLoginUser(model, request);
        if (loginUser == null) {
            return "login";
        }
        model.addAttribute("index", "integral");
        //2.获取积分列表
        List<Integral> integralList = integralService.findAll();
        model.addAttribute("integralList", integralList);
        return "admin-integral";
    }


    /**
     * 管理员移除活动中的学生
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
     * 跳转至-管理员-申报列表
     *
     * @param model
     * @return
     */
    @GetMapping("/apply/list")
    public String applyList(Model model, HttpServletRequest request) {
        //1.从session里取当前登录user对象
        User loginUser = getLoginUser(model, request);
        if (loginUser == null) {
            return "login";
        }
        model.addAttribute("index", "apply");
        //2.查询申报列表
        List<Apply> applyList = applyService.findApplyList(null);
        model.addAttribute("applyList", applyList);
        return "admin-apply";
    }

    /**
     * 从session里取当前登录user对象
     * @param model
     * @param request
     */
    private User getLoginUser(Model model, HttpServletRequest request) {
        User userLogin = (User) request.getSession().getAttribute("userLogin");
        if (userLogin.getIsAdmin() != 1) {
            return null;
        }
        model.addAttribute("user", userLogin);
        return userLogin;
    }
}
