package cn.jzt56.singleticketsystem.service;

import cn.jzt56.singleticketsystem.entity.BiddingDetail;

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
    Boolean addBidding(BiddingDetail biddingDetail);

    /**
     * @method
     * @description :取消报价
     * @author:lzy
     */
    Boolean cancelBidding(BiddingDetail biddingDetail);
}
