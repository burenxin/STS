package cn.jzt56.singleticketsystem.mapper;

import cn.jzt56.singleticketsystem.entity.BiddingDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 16:18 2018/12/5
 * @ Description：报价类
 */
@Mapper
public interface BiddingDetailMapper {

    /**
     * @method
     * @description 报价不存在则插入
     * @author:lzy
     */
    int addBiddingDetail(BiddingDetail biddingDetail);
    /**
     * @method
     * @description:修改报价
     * @author:lzy
     */
    int updateQuotedPrice(BiddingDetail biddingDetail);

    /**
     * @method
     * @description:取消报价
     * @author:lzy
     */
    int cancelBidding(BiddingDetail biddingDetail);
}
