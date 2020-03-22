package com.ssm.activity.dao;

import com.ssm.activity.domain.po.Integral;
import org.apache.ibatis.annotations.*;
import org.springframework.data.mybatis.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 积分活动
 */
@Mapper
@Component
public interface IntegralDao {

    /**
     * 查询该活动的学生数
     * @param activityId
     * @return
     */
    @Select("select IFNULL(COUNT(*),0) from t_integral where activity_id = #{activityId}")
    Integer getStuNumByActivityId(@Param("activityId") String activityId);

    /**
     * 查询活动积分列表
     * @param activityId
     * @return
     */
    List<Integral> findListByActivityId(@Param("activityId")String activityId,@Param("keyWord")String keyWord);

    /**
     * 查询活动积分列表
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("select * from t_integral where user_id = #{userId} and activity_time < #{endTime} and activity_time > #{startTime} order by create_time DESC")
    List<Integral> findListByUserId(@Param("userId")String userId, @Param("startTime")String startTime, @Param("endTime")String endTime);


    /**
     * 管理员移除学生
     * @param integralId
     * @return
     */
    @Delete("delete from t_integral where id = #{integralId}")
    void deleteIntegral(@Param("integralId")String integralId);

    /**
     * 学生参加活动
     * @param integral
     */
    void insertIntegral(@Param("integral")Integral integral);

    @Select("select * from t_integral where activity_id = #{activityId} and user_id = #{userId} limit 1")
    Integral getByActivityIdAndUserId(@Param("activityId")String activityId,@Param("userId") String userId);

    @Select("select * from t_integral where id = #{id}")
    Integral getById(@Param("id")String id);

    /**
     * 更新活动积分表 （只需要更新 获奖名次 和 积分）
     * @param id
     * @param rank
     * @param integral
     */
    @Update("update t_integral set rank = #{rank},integral = #{integral} where id = #{id}")
    void updateRankAndIntegral(@Param("id")String id, @Param("rank")Integer rank, @Param("integral")int integral);

    /**
     * 获取积分列表
     * @return
     */
    @Select("SELECT * FROM t_integral ORDER BY create_time DESC ")
    List<Integral> findAll();

    /**
     * 查询每个学生的参与活动总数
     * @param userId
     * @return
     */
    @Select("SELECT IFNULL(COUNT(*),0) FROM t_integral where user_id = #{userId} ")
    Integer findUsersActivityNum(@Param("userId")String userId);

    /**
     * 查询每个学生的参与活动积分总数
     * @param userId
     * @return
     */
    @Select("SELECT IFNULL(SUM(integral),0) FROM t_integral where user_id = #{userId} ")
    Integer findUsersIntegralNum(@Param("userId")String userId);


    @Delete("delete from t_integral where activity_id = #{activityId}")
    void deleteByActivityId(@Param("activityId")String activityId);

    @Delete("delete from t_integral where user_id = #{id}")
    void deleteByStudentId(@Param("id")String id);
}
