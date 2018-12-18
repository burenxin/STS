package cn.jzt56.singleticketsystem.service;

import cn.jzt56.singleticketsystem.entity.UserInfo;

import java.util.List;

/**
 * Created with IDEA
 * 用户信息Service
 * @author: CHENG QI
 * @Date: 2018/12/4
 * Time: 15:07
 */
public interface IUserInfoService {

    /**
     * 添加用户信息
     * @param  userinfo
     */
    void insertUserinfo(UserInfo userinfo);

    /**
     * 根据用户id修改用户信息
     * @param userinfo
     */
    void updateUserinfoByUserId(UserInfo userinfo);

    /**
     * 根据用户id删除用户信息
     * @param UserId
     */
    int removeUserinfoByUserId(String UserId);

    /**
     * 根据用户id查找用户信息
     * @param UserId
     * @return UserInfo
     */
    UserInfo getUserinfoByUserId(String UserId);

    /**
     * 获取所有的用户信息
     * @return List<UserInfo>
     */
    List<UserInfo> listGetAllUserinfo();

    /**
     * 根据用户名查询用户信息
     * @param UserName
     * @return UserInfo
     */
    UserInfo getUserinfoByUserName(String UserName);
}
