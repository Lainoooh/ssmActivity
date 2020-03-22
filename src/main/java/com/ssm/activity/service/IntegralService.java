package com.ssm.activity.service;

import com.ssm.activity.Utils.DateUtils;
import com.ssm.activity.Utils.IDUtils;
import com.ssm.activity.dao.ActivityDao;
import com.ssm.activity.dao.IntegralDao;
import com.ssm.activity.dao.UserDao;
import com.ssm.activity.domain.WebResult;
import com.ssm.activity.domain.dto.ActivityRespDTO;
import com.ssm.activity.domain.po.Integral;
import com.ssm.activity.domain.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 活动积分业务逻辑类
 */
@Service
public class IntegralService {

    @Autowired
    IntegralDao integralDao;
    @Autowired
    ActivityDao activityDao;
    @Autowired
    UserDao userDao;

    /**
     * 查询活动积分列表
     * @param activityId
     * @return
     */
    public List<Integral> findListByActivityId(String activityId,String keyWord) {
        return integralDao.findListByActivityId(activityId,keyWord);
    }
    /**
     * 查询活动积分列表
     * @param userId
     * @return
     */
    public List<Integral> findListByUserId(String userId,Integer year,Integer semester) {
        //第一次进入页面的时候，这两个参数为空，
        String startTime = "1970-1-1 00:00:00";
        String endTime = "2099-12-31 23:59:59";
        if (year != null && semester != null) {
            if (semester == 1) {
                //第一学期
                startTime = year + "-9-1 00:00:00";
                //跨年了
                endTime = (year + 1) + "-1-31 23:59:59";
            } else if (semester == 2) {
                //第二学期
                startTime = (year + 1) + "-2-1 00:00:00";
                endTime = (year + 1) + "-8-31 23:59:59";
            } else {
                //查一整学年
                //2019-1-1 00:00:00
                startTime = year + "-9-1 00:00:00";
                endTime = (year + 1) + "-8-31 23:59:59";
            }
        }
        return integralDao.findListByUserId(userId,startTime,endTime);
    }

    /**
     * 管理员移除学生
     * @param integralId
     * @return
     */
    public void deleteIntegral(String integralId) {
        integralDao.deleteIntegral(integralId);
    }

    /**
     * 根据活动和学生id查找活动积分
     * @param activityId
     * @param userId
     * @return
     */
    public Integral getByActivityIdAndUserId(String activityId, String userId){
        return integralDao.getByActivityIdAndUserId(activityId, userId);
    }

    /**
     * 学生参加活动
     * @param activityId
     * @param userId
     */
    public WebResult<Boolean> addIntegral(String activityId, String userId) {
        //1.根据用户id和活动id进行查询积分，判断是否为空，空则说明该学生没有参加该活动
        Integral integralDaoByActivityIdAndUserId = integralDao.getByActivityIdAndUserId(activityId,userId);
        if (integralDaoByActivityIdAndUserId == null) {
            //2.查询活动信息和学生信息
            ActivityRespDTO activityById = activityDao.getById(activityId);
            User userById = userDao.getById(userId);
            //3.进行积分数据的组装
            if (activityById != null && userById != null) {
                Integral integral = new Integral();
                integral.setId(IDUtils.getId());
                integral.setUserId(userById.getId());
                integral.setUserName(userById.getUserName());
                integral.setStuNo(userById.getStuNo());
                integral.setActivityId(activityById.getId());
                integral.setActivityName(activityById.getActivityName());
                integral.setActivityTime(DateUtils.format(activityById.getStartTime()));
                integral.setYear(DateUtils.format(activityById.getStartTime(), DateUtils.format_YYYY));
                //4.插入数据库
                integralDao.insertIntegral(integral);
            }
        }
        return WebResult.ok(true);

    }

    /**
     * 更改活动获得奖项
     * 异步请求，返回的json，采用的注解是@ResponseBody，与AdminController不一样，那边返回的是model视图（freemarker模板引擎，可以下来学习下）
     * @param id
     * @param rank
     */
    public WebResult<Boolean> updateIntegral(String id, Integer rank) {
        //根据id获得活动积分对象
        Integral getById = integralDao.getById(id);
        if (getById != null) {
            //获取本次活动的id，查找出对象，获取活动的分值
            String activityId = getById.getActivityId();
            ActivityRespDTO activityById = activityDao.getById(activityId);
            if (activityById != null) {
                if (rank != null ) {
                    //根据获奖名次来寻找积分
                    int integral = 0;
                    switch (rank) {
                        case 0:
                            rank = null;
                            integral = 0;
                            break;
                        case 1:
                            integral = activityById.getPrize1();
                            break;
                        case 2:
                            integral = activityById.getPrize2();
                            break;
                        case 3:
                            integral = activityById.getPrize3();
                            break;
                        case 4:
                            integral = activityById.getPrize4();
                            break;
                    }
                    //更新活动积分表 （只需要更新 获奖名次 和 积分）
                    integralDao.updateRankAndIntegral(id,rank,integral);
                }
            }
        }
        return WebResult.ok(true);
    }

    /**
     * 获取积分列表
     * @return
     */
    public List<Integral> findAll() {
        return integralDao.findAll();
    }

}
