package cn.jzt56.singleticketsystem.service.impl;

import cn.jzt56.singleticketsystem.entity.BiddingDetail;
import cn.jzt56.singleticketsystem.mapper.BiddingDetailMapper;
import cn.jzt56.singleticketsystem.service.BiddingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 16:13 2018/12/5
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
@Service
public class BiddingDetailServiceImpl implements BiddingDetailService {

    @Autowired
    private BiddingDetailMapper biddingDetailMapper;
    /**
     * @method
     * @description:承运商竞价
     * @author:lzy
     */
   public Boolean addBidding(BiddingDetail biddingDetail){
        Boolean flage= this.biddingDetailMapper.addBiddingDetail(biddingDetail)>=1?true:false;
        if (!flage)
            this.biddingDetailMapper.updateQuotedPrice(biddingDetail);
        return flage;
    }
    /**
     * @method
     * @description :取消报价
     * @author:lzy
     */
    @Override
    public Boolean cancelBidding(BiddingDetail biddingDetail) {
        return this.biddingDetailMapper.cancelBidding(biddingDetail)>=1?true:false;
    }
}
