package cn.jzt56.singleticketsystem.tools;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 14:19 2018/12/7
 * @ Description：已竞价
 */
@Data
//@EqualsAndHashCode(callSuper = true)
public class AuctionTaskView  extends AuctionTask{


    private BigDecimal quotedPrice;
}
