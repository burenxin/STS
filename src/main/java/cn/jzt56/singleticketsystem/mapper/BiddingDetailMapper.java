package cn.jzt56.singleticketsystem.mapper;

import cn.jzt56.singleticketsystem.entity.BiddingDetail;
import cn.jzt56.singleticketsystem.tools.AuctionTaskView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

//    /**
//     * @method
//     * @description：竞价截止任务单号
//     */
//    List<AuctionTaskView> findBiddingEnd(@Param("flage")Boolean flage);

    /**
     * @Date 12.17 17.01
     * @description :查询当前任务单id的最低价
     * @author :lzy
    */
    BiddingDetail  findMinDetailByTaskId(String bidTaskId);

    /**
     * @method
     * @description :findBiddingDetail 竞价明细
     * @author:lzy
     */
    List<BiddingDetail>findBiddingDetail(String bidTaskId);

    /**
     * @Date 12.17 20:30
     * @description :返回数量用于判断竞价是否存在
     * @author :lzy
     */
    int findBiddingDetailNum(String bisTaskId);


}
