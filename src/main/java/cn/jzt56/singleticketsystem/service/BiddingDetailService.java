package cn.jzt56.singleticketsystem.service;

import cn.jzt56.singleticketsystem.entity.BiddingDetail;
import cn.jzt56.singleticketsystem.entity.entityView.BiddingDetailView;
import cn.jzt56.singleticketsystem.tools.Result;

import java.util.List;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 16:14 2018/12/5
 * @ Description：报价记录接口
 */
public interface BiddingDetailService {

    /**
     * @method
     * @description :报价与修改价格
     * @author:lzy
     */
    Result addBidding(BiddingDetail biddingDetail);

    /**
     * @method
     * @description :取消报价
     * @author:lzy
     */
    Result cancelBidding(BiddingDetail biddingDetail);

    /**
     * @method
     * @description :findBiddingDetail 竞价明细
     * @author:lzy
     */
    BiddingDetailView findBiddingDetail(String bidTaskId);
}
