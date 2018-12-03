package cn.jzt56.singleticketsystem.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Order implements Serializable{

    private String order_id;//订单ID
    private String client_id;//用户ID
    private String phoneNum_Consignor;//寄件人电话
    private String consignor;//寄件人姓名
    private String phoneNum_ee;//收件人电话
    private String consignee;//收件人名
    private String starArea;//起始地址
    private String starArea_mx;//起始地街道
    private String endArea;//目的地
    private String endArea_mx;//目的地街道
    private String goods_Type;//商品类型
    private String goods_Count;//总件数
    private String volume;//体积
    private String weight;//重量
    private String delivery_Time;//提货时间
    private String receiving_Time;//预估收货时间
    private BigDecimal ttansport_Prices;//运输费用
    private String status;//状态
    private String created_Time;//创建时间
    private String updated_Time;//更新时间

}
