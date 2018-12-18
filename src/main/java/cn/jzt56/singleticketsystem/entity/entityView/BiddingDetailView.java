package cn.jzt56.singleticketsystem.entity.entityView;

import cn.jzt56.singleticketsystem.entity.BiddingDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 15:16 2018/12/17
 * @ Description：返回竞价详情包括任务单信息
 */
@Data
public class BiddingDetailView {

    private List<BiddingDetail> list;

    private String userId;

    private String userName;

    private BigDecimal transactionPrice;//竞拍成功价
}
