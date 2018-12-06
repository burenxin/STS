package cn.jzt56.singleticketsystem.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 16:22 2018/12/4
 * @ Description：竞价任务实体类
 * @Data实现toString与getter、setter
 */
@Data
public class AuctionTask implements Serializable {
    private String	bidTaskId		;//任务单ID
    private String	orderId		    ;//订单ID
    private String	userId		;//用户ID
    private String	totalQuantity	;//总件数
    private String	totalVolume		;//总体积
    private String	totalWeight		;//总重量
    private BigDecimal proposedPrice	;//合计金额
    private String	pickArea		;//提货地区
    private String	deliverArea		;//送货地区
    private String serviceTime		;//送达时间
    private String	releaseTime		;//竞拍发布时间
    private String	sealedDiskTime	;//竞拍封盘时间
    private String	bidStatus		;//竞拍状态
    private BigDecimal	transactionPrice;//竞拍成功价
    private String	taskType		;//任务类型


}
