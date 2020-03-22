package com.ssm.activity.service;

import com.ssm.activity.Utils.IDUtils;
import com.ssm.activity.dao.ApplyDao;
import com.ssm.activity.dao.UserDao;
import com.ssm.activity.domain.WebResult;
import com.ssm.activity.domain.po.Apply;
import com.ssm.activity.domain.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.WatchEvent;
import java.util.Date;
import java.util.List;

/**
 * 申报管理
 */
@Service
public class ApplyService {
    @Autowired
    ApplyDao applyDao;

    @Autowired
    UserDao userDao;

    /**
     *  学生申报
     * @param apply
     * @return
     */
    public WebResult<Boolean> add(Apply apply) {
        User userDaoById = userDao.getById(apply.getUserId());
        if (userDaoById != null) {
            String id = IDUtils.getId();
            apply.setId(id);
            apply.setCreateTime(new Date());
            applyDao.insertOrFlush(apply);
        }
        return WebResult.ok(true);
    }

    /**
     *  管理员处理
     * @param apply
     * @return
     */
    public WebResult<Boolean> update(Apply apply) {
        Apply byId = applyDao.getById(apply.getId());
        if (byId != null) {
            byId.setUpdateTime(new Date());
            byId.setIsDel(1);
            byId.setDelName(apply.getDelName());
            applyDao.insertOrFlush(byId);
        }
        return WebResult.ok(true);
    }

    /**
     * 查询申报列表
     * @param userId
     * @return
     */
    public List<Apply> findApplyList(String userId) {
        return applyDao.findApplyList(userId);
    }
}
