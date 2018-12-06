package cn.jzt56.singleticketsystem.service.impl;

import cn.jzt56.singleticketsystem.entity.UserInfo;
import cn.jzt56.singleticketsystem.mapper.IUserInfoMapper;
import cn.jzt56.singleticketsystem.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IDEA
 * 用户信息实现类
 * @author: CHENG QI
 * @Date: 2018/12/4
 * Time: 15:10
 */
@Service
@Slf4j
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private IUserInfoMapper service;

    /**
     * 添加用户信息
     *
     * @param userinfo
     * @return Userinfo
     */
    @Override
    public void insertUserinfo(UserInfo userinfo) {
        log.info("添加用户信息");
        service.insertUserinfo(userinfo);
    }

    /**
     * 根据用户id修改用户信息
     *
     * @param userinfo
     * @return Userinfo
     */
    @Override
    public void updateUserinfoByUserId(UserInfo userinfo) {
        log.info("根据用户id修改用户信息");
        service.updateUserinfoByUserId(userinfo);
    }

    /**
     * 根据用户id删除用户信息
     *
     * @param UserId
     */
    @Override
    public void removeUserinfoByUserId(String UserId) {
        log.info("根据用户id删除用户信息");
        service.removeUserinfoByUserId(UserId);
    }

    /**
     * 根据用户id查找用户信息
     *
     * @param UserId
     * @return Userinfo
     */
    @Override
    public UserInfo getUserinfoByUserId(String UserId) {
        log.info("根据用户id查找用户信息");
        return service.getUserinfoByUserId(UserId);
    }

    /**
     * 获取所有的用户信息
     *
     * @return List<Userinfo>
     */
    @Override
    public List<UserInfo> listGetAllUserinfo() {
        log.info("获取所有的用户信息");
        return service.listGetAllUserinfo();
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param UserName
     * @return Userinfo
     */
    @Override
    public UserInfo getUserinfoByUserName(String UserName) {
        log.info("根据用户名查询用户信息");
        UserInfo userinfo = service.getUserinfoByUserName(UserName);
            return userinfo;
    }
}
