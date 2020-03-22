package com.ssm.activity.dao;

import com.ssm.activity.domain.dto.UserReqDTO;
import com.ssm.activity.domain.po.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDao {

    /**
     * 查询用户列表
     * @return
     */
    List<User> findUserList(@Param("keyWord")String keyWord);

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    @Select("select * from t_user where id = #{userId} limit 1")
    User getById(@Param("userId") String userId);

    @Insert("replace into t_user (id,user_name,password,stu_no,tel_num,email,is_admin,create_time,update_time) " +
            "value(#{reqDTO.id},#{reqDTO.userName},#{reqDTO.password},#{reqDTO.stuNo},#{reqDTO.telNum},#{reqDTO.email},#{reqDTO.isAdmin},#{reqDTO.createTime},#{reqDTO.updateTime})")
    void insertOrFlush(@Param("reqDTO")User reqDTO);


    @Delete("delete from t_user where id = #{userId}")
    void delete(@Param("userId")String id);

    @Select("select * from t_user where stu_no = #{stuNo} limit 1")
    User getByStuNo(@Param("stuNo")String stuNo);

    @Update("update  t_user set password = #{user.password} where id = #{user.id}")
    void resetPassword(@Param("user")UserReqDTO user);
}
