package com.ssm.activity.service;

import com.ssm.activity.Utils.DateUtils;
import com.ssm.activity.Utils.IDUtils;
import com.ssm.activity.dao.ActivityDao;
import com.ssm.activity.dao.IntegralDao;
import com.ssm.activity.domain.WebResult;
import com.ssm.activity.domain.dto.ActivityReqDTO;
import com.ssm.activity.domain.dto.ActivityRespDTO;
import com.ssm.activity.domain.po.Activity;
import com.ssm.activity.domain.po.Integral;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * 活动业务逻辑类
 */
@Service
public class ActivityService {

    @Autowired
    ActivityDao activityDao;
    @Autowired
    IntegralDao integralDao;

    /**
     * 定时任务job，每隔一分钟执行一次
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void updateState(){
        //查找状态为 报名中的活动
        List<ActivityRespDTO> activityList1 = activityDao.findActivityListByState(0);
        Date now = new Date();
        for (ActivityRespDTO activityRespDTO : activityList1) {
            //判断当前时间是否超过互动开始时间，是则改变活动状态
            if(now.after(activityRespDTO.getStartTime())){
                activityDao.updateState(1,activityRespDTO.getId());
            }
        }
        //查找状态为 进行中的活动
        List<ActivityRespDTO> activityList2 = activityDao.findActivityListByState(1);
        for (ActivityRespDTO activityRespDTO : activityList2) {
            //判断当前时间是否超过互动结束时间，是则改变活动状态
            if(now.after(activityRespDTO.getEndTime())){
                activityDao.updateState(2,activityRespDTO.getId());
            }
        }
    }
    

    /**
     * 查询活动列表
     * @return
     */
    public WebResult<List<ActivityRespDTO>> findActivityList(Integer limit,String sort) {
        //1.查询活动列表
        List<ActivityRespDTO> activityList = activityDao.findActivityList();
        if (!CollectionUtils.isEmpty(activityList)) {
            //2.查询参与活动的学生数
            for (ActivityRespDTO activityRespDTO : activityList) {
                //从积分活动表里查询数量
                int stuNum = integralDao.getStuNumByActivityId(activityRespDTO.getId());
                activityRespDTO.setStuNum(stuNum);
                //重组装活动时间
                activityRespDTO.setActivityTime(DateUtils.format(activityRespDTO.getStartTime()) + " 至 " + DateUtils.format(activityRespDTO.getEndTime()));
            }
            //3.说明要节选和排序
            if (!StringUtils.isEmpty(sort)) {
                //根据参与学生数排序
                if ("stuNum".equals(sort)) {
                    activityList.sort(new Comparator<ActivityRespDTO>() {
                        @Override
                        public int compare(ActivityRespDTO o1, ActivityRespDTO o2) {
                            return o2.getStuNum() - o1.getStuNum();
                        }
                    });
                }
            }
            //节选
            if (limit != null && limit < activityList.size()) {
                activityList = activityList.subList(0, limit);
            }
        }

        return WebResult.ok(activityList);
    }

    /**
     * 根据id删除活动
     * @param activityId
     * @return
     */
    public WebResult<Boolean> deleteActivity(String activityId) {
        //1.删除活动
        activityDao.deleteById(activityId);
        //2.删除活动积分
        integralDao.deleteByActivityId(activityId);
        return WebResult.ok(true);
    }

    /**
     * 根据id获取活动对象
     * @param activityId
     * @return
     */
    public ActivityRespDTO getById(String activityId) {
        ActivityRespDTO byId = activityDao.getById(activityId);
        if (byId != null) {
            //重组装活动时间
            byId.setActivityTime(DateUtils.format(byId.getStartTime()) + " 至 " + DateUtils.format(byId.getEndTime()));
        }
        return byId;
    }

    public WebResult<Boolean> addOrFlush(ActivityReqDTO reqDTO) {
        int isRace = reqDTO.getIsRace();
        if (isRace == 0) {
            //说明是非竞赛类
            reqDTO.setPrize1(null);
            reqDTO.setPrize2(null);
            reqDTO.setPrize3(null);
        }
        //1.没有传id，则说明是增加，传了是更新
        if (StringUtils.isEmpty(reqDTO.getId())) {
            reqDTO.setId(IDUtils.getId());
            reqDTO.setCreateTime(new Date());
            reqDTO.setState(0);
        }else{
            String id = reqDTO.getId();
            ActivityRespDTO byId = activityDao.getById(id);
            //判断是否更改活动类型
            if(byId.getIsRace() != isRace){
                //判断是否为竞赛类更改为非竞赛类
                if(isRace == 0){
                    List<Integral> listByActivityId = integralDao.findListByActivityId(id, "");
                    if (!CollectionUtils.isEmpty(listByActivityId)) {
                        for (Integral integral : listByActivityId) {
                            //判断更改之前是否有学生评为一二三等奖
                            if(integral.getRank() != null ){
                                if ( integral.getRank() == 1 || integral.getRank() == 2 || integral.getRank() == 3) {
                                    //如果有，则不允许更改类型
                                    return WebResult.fail("已有学生评奖，不允许更改活动类型！！！");
                                }
                            }

                        }
                    }
                }

            }

        }
        activityDao.insertOrFlush(reqDTO);
        return WebResult.ok(true);
    }

    /**
     * 导入活动Excel
     * @param importList
     * @return
     */
    public WebResult<Boolean> insertBatch(List<ActivityReqDTO> importList) {
        for (ActivityReqDTO activity : importList) {
            WebResult<ActivityReqDTO> checkParam = checkParam(activity);
            if(!checkParam.isSuccess()){
                String error = checkParam.getError();
                return WebResult.fail(error);
            }
            activity = checkParam.getResult();
            addOrFlush(activity);
        }
        return WebResult.ok(true);
    }

    private WebResult<ActivityReqDTO> checkParam(ActivityReqDTO activity) {
        Date now = DateUtils.addMinutes(new Date(), 30);
        activity.setStartTime(DateUtils.format(now));
        activity.setEndTime(DateUtils.format(DateUtils.tomorrow(now)));

        if ("是".equals(activity.getIsRaceString())) {
            activity.setIsRace(1);
        }else{
            activity.setIsRace(0);
        }
//        String startTime = activity.getStartTime();
//        String endTime = activity.getEndTime();

        if (StringUtils.isEmpty(activity.getActivityName())) {
            return WebResult.fail("活动名称不能为空");
        }
//        if (StringUtils.isEmpty(startTime)) {
//            return WebResult.fail("活动开始时间不能为空");
//        }
//        if (StringUtils.isEmpty(endTime)) {
//            return WebResult.fail("活动结束时间不能为空");
//        }
//        if (new Date().after(DateUtils.parse(startTime))) {
//            return WebResult.fail("活动开始时间不能早于当前时间");
//        }
//
//        if (DateUtils.parse(startTime).after(DateUtils.parse(endTime))) {
//            return WebResult.fail("活动结束时间不能早于开始时间");
//        }

        if (activity.getPrize4() == null) {
            activity.setPrize4(0);
        }
        if (activity.getIsRace() == 1) {
            if (activity.getPrize1() == null) {
                activity.setPrize1(0);
            }
            if (activity.getPrize2() == null) {
                activity.setPrize2(0);
            }
            if (activity.getPrize3() == null) {
                activity.setPrize3(0);
            }
        }
        return WebResult.ok(activity);
    }
}
