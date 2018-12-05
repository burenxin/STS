package cn.jzt56.singleticketsystem.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Order implements Serializable{

    private String orderId;//订单ID
    private String userId;//用户ID
    private String phoneNumConsignor;//寄件人电话
    private String consignor;//寄件人姓名
    private String phoneNumConsignee;//收件人电话
    private String consignee;//收件人名
    private String starArea;//起始地址
    private String starAreaMx;//起始地街道
    private String endArea;//目的地
    private String endAreaMx;//目的地街道
    private String goodsType;//商品类型
    private String goodsCount;//总件数
    private String volume;//体积
    private String weight;//重量
    private String deliveryTime;//提货时间
    private String receivingTime;//预估收货时间
    private BigDecimal ttansportPrices;//运输费用
    private String status;//状态
    private String createdTime;//创建时间
    private String updatedTime;//更新时间

}
