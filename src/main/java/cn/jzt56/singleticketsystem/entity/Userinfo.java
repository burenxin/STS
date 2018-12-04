package cn.jzt56.singleticketsystem.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IDEA
 * 用户信息表实体类
 * 使用lombok插件中的注解@Date
 * @author: CHENG QI
 * @Date: 2018/12/4
 * Time: 13:30
 */

@Data
public class Userinfo implements Serializable {

    /** 用户ID. */
    private String userId;

    /** 用户名. */
    private String userName;

    /** 密码. */
    private String userPassword;

    /** 用户类型(0委托方、1承运商、2管理方). */
    private String type;

    /** 状态(0可用、1不可用). */
    private String status;

    /** 创建时间. */
    private Date createdTime;

    /** 修改时间. */
    private Date updatedTime;

    /** 联系电话. */
    private String phoneNum;

}
