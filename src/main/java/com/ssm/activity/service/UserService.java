package com.ssm.activity.service;

import com.ssm.activity.Utils.IDUtils;
import com.ssm.activity.dao.IntegralDao;
import com.ssm.activity.dao.UserDao;
import com.ssm.activity.domain.WebResult;
import com.ssm.activity.domain.dto.UserReqDTO;
import com.ssm.activity.domain.dto.UserRespDTO;
import com.ssm.activity.domain.po.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 用户业务逻辑类
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    IntegralDao integralDao;

    /**
     * 查询用户列表
     * @return
     */
    public List<UserRespDTO> findUserList(String keyWord,Integer limit,String sort) {
        //1.查询用户列表
        List<User> userList = userDao.findUserList(keyWord);
        //2.转换每一个学生（添加必要的信息）
        UserRespDTO userRespDTO;
        List<UserRespDTO> userRespDTOList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(userList)){
            for (User user : userList) {
                userRespDTO = new UserRespDTO();
                //这个方法是将user的所有属性赋值给userRespDTO
                BeanUtils.copyProperties(user,userRespDTO);
                //3.此处要去查询每个学生的参与活动总数和积分总数
                int activityNum = integralDao.findUsersActivityNum(user.getId());
                int integralNum = integralDao.findUsersIntegralNum(user.getId());
                userRespDTO.setActivityNum(activityNum);
                userRespDTO.setIntegralNum(integralNum);
                userRespDTOList.add(userRespDTO);
            }
            //3.说明要节选和排序
            if (limit != null || !StringUtils.isEmpty(sort)) {
                //根据参与积分总数排序
                if ("integralNum".equals(sort)) {
                    userRespDTOList.sort(new Comparator<UserRespDTO>() {
                        @Override
                        public int compare(UserRespDTO o1, UserRespDTO o2) {
                            return o2.getIntegralNum()-o1.getIntegralNum();
                        }
                    });
                }
                //节选
                if (limit != null && limit < userRespDTOList.size()) {
                    userRespDTOList = userRespDTOList.subList(0, limit);
                }
            }
        }
        return userRespDTOList;
    }

    /**
     * 查询 学生信息
     * @param userId
     * @return
     */
    public UserRespDTO getById(String userId) {
        User user = userDao.getById(userId);
        UserRespDTO userRespDTO = new UserRespDTO();
        if (user != null) {
            BeanUtils.copyProperties(user,userRespDTO);
            //3.此处要去查询每个学生的参与活动总数和积分总数
            int activityNum = integralDao.findUsersActivityNum(user.getId());
            int integralNum = integralDao.findUsersIntegralNum(user.getId());
            userRespDTO.setActivityNum(activityNum);
            userRespDTO.setIntegralNum(integralNum);
        }
        return userRespDTO;
    }

    /**
     * 添加或者更新
     * @param reqDTO
     * @return
     */
    public WebResult<Boolean> addOrFlush(User reqDTO) {
        User user = userDao.getByStuNo(reqDTO.getStuNo());
        //1.判断当前学号是否重复,排除更改用户和查找用户是同一个
        if (user != null && !user.getId().equals(reqDTO.getId())) {
            return WebResult.fail("学号重复！！！");
        }
        //2.没有传id，则说明是增加，传了是更新
        if (StringUtils.isEmpty(reqDTO.getId())) {
            reqDTO.setId(IDUtils.getId());
            reqDTO.setCreateTime(new Date());
        }else{
            reqDTO.setUpdateTime(new Date());
        }
        reqDTO.setIsAdmin(0);
        userDao.insertOrFlush(reqDTO);
        return WebResult.ok(true);
    }

    /**
     * 通过id删除
     * @param id
     * @return
     */
    public WebResult<Boolean> delete(String id) {
        userDao.delete(id);
        //删除活动积分
        integralDao.deleteByStudentId(id);
        return WebResult.ok(true);
    }

    /**
     * 通过学号获取
     * @param stuNo
     * @return
     */
    public User getByStuNo(String stuNo) {
        return userDao.getByStuNo(stuNo);
    }

    /**
     * 导入
     * @param importList
     * @return
     */
    public WebResult<Boolean> insertBatch(List<User> importList) {
        if (!CollectionUtils.isEmpty(importList)) {
            for (User insert : importList) {
                //1.参数校验
                WebResult<Boolean> checkParam = checkParam(insert);
                if(checkParam.isSuccess()){
                    //2.插入数据
                    return addOrFlush(insert);
                }else{
                    return checkParam;
                }
            }
        }
        return WebResult.ok(true);
    }


    /**
     * 添加用户的参数校验
     * @param user
     * @return
     */
    private WebResult<Boolean> checkParam(User user) {
        if (user == null) {
            return WebResult.fail("请输入正确信息！");
        }else if(StringUtils.isEmpty(user.getUserName())) {
            return WebResult.fail("请输入姓名！");
        }else if(StringUtils.isEmpty(user.getStuNo())) {
            return WebResult.fail("请输入学号或者工号！");
        }else if(StringUtils.isEmpty(user.getPassword())) {
            return WebResult.fail("请输入密码！");
        } else if (user.getStuNo().length() != 12) {
            return WebResult.fail("学号格式错误，请填写12位学号！");
        } else if (user.getPassword().length() != 6) {
            return WebResult.fail("密码格式错误，请填写6位数字！");
        }
        try {
            //校验是否为数字
            Integer reqPassword = Integer.valueOf(user.getPassword());
        } catch (Exception e) {
            return WebResult.fail("密码格式错误，请填写6位数字");
        }
        return WebResult.ok(true);
    }

    /**
     * 重置密码
     * @param user
     */
    public void resetPassword(UserReqDTO user) {
        userDao.resetPassword(user);
    }
}
