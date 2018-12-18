package cn.jzt56.singleticketsystem.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 15:43 2018/12/4
 * @ Description：竞价实体类
 */
@Data
public class BiddingDetail implements Serializable {
    private String detailId	;           //详情ID
    private String bidTaskId;	        //竞价任务ID
    private String userId;	        //用户ID
    private BigDecimal quotedPrice	;       //报价
    private String participationTime;   //参与时间
    private String status;              //状态

}
