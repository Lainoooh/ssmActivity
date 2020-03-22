package com.ssm.activity.dao;

import com.ssm.activity.domain.po.Apply;
import com.ssm.activity.domain.po.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ApplyDao {

    /**
     * 增加或更新
     * @param apply
     */
    @Insert("replace into t_apply (id,user_id,activity_id,dsc,apply_type,is_del,del_name,create_time,update_time) " +
            "value(#{reqDTO.id},#{reqDTO.userId},#{reqDTO.activityId},#{reqDTO.dsc},#{reqDTO.applyType},#{reqDTO.isDel},#{reqDTO.delName},#{reqDTO.createTime},#{reqDTO.updateTime})")
    void insertOrFlush(@Param("reqDTO") Apply apply);

    /**
     * 通过id查钊申报
     * @param id
     * @return
     */
    @Select("select * from t_apply where id = #{id}")
    Apply getById(@Param("id")String id);

    /**
     * 查找申报列表
     * @param userId
     * @return
     */
    List<Apply> findApplyList(@Param("userId")String userId);
}
