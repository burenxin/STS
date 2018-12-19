package cn.jzt56.singleticketsystem.service.impl;

import cn.jzt56.singleticketsystem.entity.BiddingDetail;
import cn.jzt56.singleticketsystem.entity.entityView.BiddingDetailView;
import cn.jzt56.singleticketsystem.mapper.AuctionTaskMapper;
import cn.jzt56.singleticketsystem.mapper.BiddingDetailMapper;
import cn.jzt56.singleticketsystem.service.BiddingDetailService;
import cn.jzt56.singleticketsystem.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private AuctionTaskMapper auctionTaskMapper;
    /**
     * @method
     * @description:承运商竞价与改价
     * @author:lzy
     */
   public Boolean addBidding(BiddingDetail biddingDetail){
        Boolean flage= this.biddingDetailMapper.addBiddingDetail(biddingDetail)>=1?true:false;//报价
        if (!flage)
            flage=this.biddingDetailMapper.updateQuotedPrice(biddingDetail)>=1?true:false;//报价修改
        return flage;
    }
    /**
     * @method
     * @description :取消报价
     * @author:lzy
     */
    @Override
    public Result cancelBidding(BiddingDetail biddingDetail) {
        Result result=new Result(false,"取消失败");
        Boolean flage= this.biddingDetailMapper.cancelBidding(biddingDetail)>=1?true:false;//取消报价
        if(flage){
            result.setMessage("取消成功");
            result.setSuccess(flage);
        }
        return result;
    }

    /**
     * @method
     * @description :findBiddingDetail 竞价明细
     * @author:lzy
     */
    @Override
    public BiddingDetailView findBiddingDetail(String bidTaskId) {

        BiddingDetailView biddingDetailView;
        if(this.auctionTaskMapper.findBiddingDetailViewNumber(bidTaskId)>0){
            biddingDetailView=this.auctionTaskMapper.findBiddingDetailView(bidTaskId);
        }else {
            biddingDetailView=new BiddingDetailView();
            biddingDetailView.setUserName("无");
            biddingDetailView.setTransactionPrice(BigDecimal.ZERO);
        }
        BiddingDetail biddingDetail =new BiddingDetail();
        biddingDetail.setDetailId("空");
        if(this.biddingDetailMapper.findBiddingDetail(bidTaskId).size()>0){
            biddingDetailView.setList(this.biddingDetailMapper.findBiddingDetail(bidTaskId));
        }else {
            List<BiddingDetail> list=new ArrayList<>();
            //list.add(biddingDetail);
            biddingDetailView.setList(list);
        }
        return biddingDetailView;
    }
}
