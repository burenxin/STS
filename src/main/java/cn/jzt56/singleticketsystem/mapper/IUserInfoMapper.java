package cn.jzt56.singleticketsystem.mapper;

import cn.jzt56.singleticketsystem.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IDEA
 * 接口类：用户信息Mapper
 * @author: CHENG QI
 * @Date: 2018/12/4
 * Time: 14:13
 */
@Mapper
public interface IUserInfoMapper {

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
     * @return Userinfo
     */
    UserInfo getUserinfoByUserId(String UserId);

    /**
     * 获取所有的用户信息
     * @return List<Userinfo>
     */
    List<UserInfo> listGetAllUserinfo();

    /**
     * 根据用户名查询用户信息
     * @param UserName
     * @return Userinfo
     */
    UserInfo getUserinfoByUserName(String UserName);
}
