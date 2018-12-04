package cn.jzt56.singleticketsystem.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 16:22 2018/12/4
 * @ Description：竞价任务实体类
 * @Data实现toString与getter、setter
 */
@Data
public class TaskOfAuction implements Serializable {
    private String	BidTaskId		;//任务单ID
    private String	OrderId		    ;//订单ID
    private String	CarrierId		;//承运商ID
    private Integer	TotalQuantity	;//总件数
    private float	TotalVolume		;//总体积
    private float	TotalWeight		;//总重量
    private float	 ProposedPrice	;//合计金额
    private String	PickArea		;//提货地区
    private String	DeliverArea		;//送货地区
    private Date ServiceTime		;//送达时间
    private Date	ReleaseTime		;//竞拍发布时间
    private Date	SealedDiskTime	;//竞拍封盘时间
    private String	BidStatus		;//竞拍状态
    private String	TransactionPrice;//竞拍成功价
    private String	TaskType		;//任务类型


}
