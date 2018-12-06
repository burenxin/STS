package cn.jzt56.singleticketsystem.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IDEA
 * 承运商信息表
 * 使用lombok插件中的注解@Date
 * @author: CHENG QI
 * @Date: 2018/12/4
 * Time: 13:51
 */
@Data
public class CarrierInfo implements Serializable {

    /** 承运商ID. */
    private String carrierId;

    /** 姓名. */
    private String carrierName;

    /** 用户id. */
    private String userId;

    /** 联系方式(手机号). */
    private String phone;

    /** 注册地址. */
    private String address;

    /** 运输类型(0.冷藏品、1.普通). */
    private String transportType;

    /** 起始地. */
    private String startPlace;

    /** 目的地. */
    private String endPlace;

    /** 单位重量价格. */
    private Double weightPrice;

    /** 单位体积价格. */
    private Double volumePrice;

    /** 每公里收费. */
    private Double costPerKM;

    /** 最大运输体积. */
    private String maxTransportVolume;

    /** 最大运输数量. */
    private String maxTransportWeight;

    /** 时效(多长时间到达). */
    private String duration;

    /** 状态(0可用、1不可用). */
    private String status;

    /** 注册时间. */
    private Date createdTime;

    /** 修改时间. */
    private Date updateTime;

    /** 说明. */
    private String description;

}
