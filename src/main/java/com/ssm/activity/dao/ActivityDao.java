package com.ssm.activity.dao;

import com.ssm.activity.domain.dto.ActivityReqDTO;
import com.ssm.activity.domain.dto.ActivityRespDTO;
import com.ssm.activity.domain.po.Activity;
import com.ssm.activity.domain.po.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ActivityDao {

    /**
     * 查找活动列表
     * @return
     */
    @Select("select * from t_activity order by state,create_time DESC")
    List<ActivityRespDTO> findActivityList();

    /**
     * 根据状态查找活动
     * @return
     */
    @Select("select * from t_activity where state = #{state}")
    List<ActivityRespDTO> findActivityListByState(@Param("state")int state);

    /**
     * 根据id删除活动
     * @param activityId
     */
    @Delete("delete from t_activity where id = #{activityId}")
    void deleteById(@Param("activityId") String activityId);

    /**
     * 根据id获取活动对象
     * @param activityId
     * @return
     */
    @Select("select * from t_activity where id = #{activityId} limit 1")
    ActivityRespDTO getById(@Param("activityId")String activityId);

    @Insert("replace into t_activity (id,activity_name,activity_dsc,activity_place,start_time,end_time," +
            " is_race,state,prize1,prize2,prize3,prize4,create_time,update_time)" +
            " value(#{reqDTO.id},#{reqDTO.activityName},#{reqDTO.activityDsc}," +
            "#{reqDTO.activityPlace},#{reqDTO.startTime},#{reqDTO.endTime},#{reqDTO.isRace},#{reqDTO.state}," +
            "#{reqDTO.prize1},#{reqDTO.prize2},#{reqDTO.prize3},#{reqDTO.prize4},#{reqDTO.createTime},#{reqDTO.updateTime})")
    void insertOrFlush(@Param("reqDTO")ActivityReqDTO reqDTO);

    /**
     * 更改活动状态
     * @param state
     * @param id
     */
    @Update("update t_activity set state = #{state} where id = #{id}")
    void updateState(@Param("state")int state,@Param("id")String id);

    /*@Update("update set t_activity (id,activity_name,activity_dsc,activity_place,start_time,end_time," +
            " is_race,state,prize1,prize2,prize3,prize4,create_time)" +
            " value(#{reqDTO.id},#{reqDTO.activityName},#{reqDTO.activityDsc}," +
            "#{reqDTO.activityPlace},#{reqDTO.startTime},#{reqDTO.endTime},#{reqDTO.isRace},#{reqDTO.state}," +
            "#{reqDTO.prize1},#{reqDTO.prize2},#{reqDTO.prize3},#{reqDTO.prize4},#{reqDTO.createTime})")
    void update(ActivityReqDTO reqDTO);*/
}
