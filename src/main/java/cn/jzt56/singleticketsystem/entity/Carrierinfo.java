package cn.jzt56.singleticketsystem.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IDEA
 * 承运商信息表
 * 使用lombok插件中的注解@Date
 * @author: CHENG QI
 * @Date: 2018/12/4
 * Time: 13:51
 */
@Data
public class Carrierinfo implements Serializable {

    /** 承运商ID. */
    private String CarrierId;

    /** 姓名. */
    private String CarrierName;

    /** 用户id. */
    private String UserId;

    /** 联系方式(手机号). */
    private String Phone;

    /** 注册地址. */
    private String Address;

    /** 运输类型(0.冷藏品、1.普通). */
    private String TransportType;

    /** 起始地. */
    private String StartPlace;

    /** 目的地. */
    private String EndPlace;

    /** 单位重量价格. */
    private Double WeightPrice;

    /** 单位体积价格. */
    private Double VolumePrice;

    /** 每公里收费. */
    private Double CostPerKM;

    /** 最大运输体积. */
    private String MaxTransportVolume;

    /** 最大运输数量. */
    private String MaxTransportWeight;

    /** 时效(多长时间到达). */
    private String Duration;

    /** 状态(0可用、1不可用). */
    private String Status;

    /** 注册时间. */
    private String CreatedTime;

    /** 修改时间. */
    private String UpdateTime;

    /** 说明. */
    private String Description;

}
