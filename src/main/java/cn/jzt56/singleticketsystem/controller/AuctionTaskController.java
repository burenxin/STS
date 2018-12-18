package cn.jzt56.singleticketsystem.controller;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.BiddingDetail;
import cn.jzt56.singleticketsystem.entity.Order;
import cn.jzt56.singleticketsystem.entity.entityView.BiddingDetailView;
import cn.jzt56.singleticketsystem.service.AuctionTaskService;
import cn.jzt56.singleticketsystem.service.BiddingDetailService;
import cn.jzt56.singleticketsystem.tools.AuctionTaskView;
import cn.jzt56.singleticketsystem.tools.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static cn.jzt56.singleticketsystem.tools.CreateUUID.getUUID32;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 11:31 2018/12/5
 * @ Description：竞价控制层
 */

@RestController
@RequestMapping(value = "/auctionTask")
@Slf4j
public class AuctionTaskController {

    @Autowired
    private AuctionTaskService auctionTaskService;

    @Autowired
    private BiddingDetailService biddingDetailService;
    /**
     * @method
     * @description:获取所有可以竞价而未竞价任务
     * @author:lzy
     */
    @RequestMapping(value = "/currentTask")
    public PageBean findAllCurrentTask(HttpServletRequest request,
                                       AuctionTask auctionTask,
                                       @RequestParam(value = "pageCode",required = false,defaultValue = "1") int pageCode,
                                       @RequestParam(value = "pageSize",required = false,defaultValue = "10") int pageSize){


        //String userId=(String)request.getSession().getAttribute("userId");
        //auctionTask.setPickArea("湖北");
        log.info("----------------------"+pageCode+""+pageSize+auctionTask.getUserId()+auctionTask.getBidTaskId());
        //auctionTask.setUserId("ui001");

        PageBean pageBean=this.auctionTaskService.findAllCurrentTask(auctionTask,pageCode,pageSize);
        log.info(pageBean.toString());
        return pageBean;
    }
    /**
     * @method
     * @description:竞价
     * @author:lzy
     */
    @RequestMapping(value = "/auctionPrice")
    public void  auctionPrice(BiddingDetail biddingDetail){
        biddingDetail.setDetailId(getUUID32());
        log.info(biddingDetail.getBidTaskId()+biddingDetail.getQuotedPrice());
        Boolean flage= this.biddingDetailService.addBidding(biddingDetail);
        if(flage)
            log.info(flage+"1");

    }
    /**
     * @method
     * @description:取消报价
     * @author:lzy
     */
    @RequestMapping(value = "/cancelBidding")
    public void  cancelBidding( BiddingDetail biddingDetail){
        //BiddingDetail biddingDetail=new BiddingDetail();
        //biddingDetail.setUserId("ui005");
       // biddingDetail.setBidTaskId(bidTaskId);
        log.info(biddingDetail.getBidTaskId());
        Boolean flage= this.biddingDetailService.cancelBidding(biddingDetail);
    }
    /**
     * @method
     * @description :获取当前已竞价任务
     * @author:lzy
     */
    @RequestMapping(value = "/findBidded")
    public PageBean findBidded(AuctionTaskView auctionTaskView,
                               @RequestParam(value = "pageCode",required = false,defaultValue = "1") int pageCode,
                               @RequestParam(value = "pageSize",required = false,defaultValue = "10") int pageSize){
        return this.auctionTaskService.findBidded(auctionTaskView,pageCode,pageSize);

    }



    /**
     *
     * @param bidTaskId
     * @return List<Order>
     * @description ：根据任务单号查询任务单详情
     * @author: CHENG QI
     */
    // http://localhost:8080/STS/auctionTask/getTaskDetail?bidTaskId='bt005'
    @RequestMapping(value = "/getTaskDetail")
    public Object getTaskDetailsByBidTaskId(String bidTaskId){
        List<Order> list = auctionTaskService.getTaskDetailsByBidTaskId(bidTaskId);
        if (list.size() == 0){
            return "订单详情不存在，请检查订单是否出错";
        }
        return list;
    }

    // http://localhost:8080/STS/auctionTask/findSuccessCurrentTaskByUserId?userId='1001'
    @RequestMapping(value = "/findSuccessCurrentTaskByUserId")
    public List<AuctionTask> findAllSuccessCurrentTaskByUserId(String userId){
        return auctionTaskService.findAllSuccessCurrentTaskByUserId(userId);
    }
    /**
     * @method
     * @description :biddeTask 所有已发布任务(与条件筛选)
     * @author:lzy
     */
    @RequestMapping(value = "/biddeTask")
    public PageBean biddeTask(AuctionTask auctionTask,
                              @RequestParam(value = "pageCode",required = false,defaultValue = "1") int pageCode,
                              @RequestParam(value = "pageSize",required = false,defaultValue = "10") int pageSize) {
        log.info(auctionTask.getTotalVolume()+auctionTask.getSealedDiskTime());
        return  this.auctionTaskService.biddeTask(auctionTask,pageCode,pageSize);
    }

    /**
     * @method
     * @description :findBiddingDetail 竞价明细
     * @author:lzy
     */
    @RequestMapping(value = "/findBiddingDetail")
    public BiddingDetailView findBiddingDetail(String bidTaskId) {
        return this.biddingDetailService.findBiddingDetail(bidTaskId);
    }

    /**
     * @method
     * @description 指派承运商
     * @author:lzy
     */
    @RequestMapping(value = "/assignCarrier")
    public Boolean assignCarrier(@RequestParam String bidTaskId,
                                 @RequestParam String userId){

        return this.auctionTaskService.assignCarrier(userId,bidTaskId);

    }


}
