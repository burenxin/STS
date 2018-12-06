package cn.jzt56.singleticketsystem.mapper;

import cn.jzt56.singleticketsystem.entity.Userinfo;
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
public interface IUserinfoMapper {

    /**
     * 添加用户信息
     * @param  userinfo
     */
    void insertUserinfo(Userinfo userinfo);

    /**
     * 根据用户id修改用户信息
     * @param userinfo
     */
    void updateUserinfoByUserId(Userinfo userinfo);

    /**
     * 根据用户id删除用户信息
     * @param UserId
     */
    void removeUserinfoByUserId(String UserId);

    /**
     * 根据用户id查找用户信息
     * @param UserId
     * @return Userinfo
     */
    Userinfo getUserinfoByUserId(String UserId);

    /**
     * 获取所有的用户信息
     * @return List<Userinfo>
     */
    List<Userinfo> listGetAllUserinfo();

    /**
     * 根据用户名查询用户信息
     * @param UserName
     * @return Userinfo
     */
    Userinfo getUserinfoByUserName(String UserName);
}
