package cn.jzt56.singleticketsystem.controller;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.BiddingDetail;
import cn.jzt56.singleticketsystem.entity.Order;
import cn.jzt56.singleticketsystem.entity.entityView.BiddingDetailView;
import cn.jzt56.singleticketsystem.service.AuctionTaskService;
import cn.jzt56.singleticketsystem.service.BiddingDetailService;
import cn.jzt56.singleticketsystem.tools.AuctionTaskView;
import cn.jzt56.singleticketsystem.tools.PageBean;
import cn.jzt56.singleticketsystem.tools.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    public Boolean auctionPrice(BiddingDetail biddingDetail){
        biddingDetail.setDetailId(getUUID32());
        //log.info(biddingDetail.getBidTaskId()+biddingDetail.getQuotedPrice());
        return this.biddingDetailService.addBidding(biddingDetail);


    }
    /**
     * @method
     * @description:取消报价
     * @author:lzy
     */
    @RequestMapping(value = "/cancelBidding")
    public Boolean  cancelBidding( BiddingDetail biddingDetail){
        //BiddingDetail biddingDetail=new BiddingDetail();
        //biddingDetail.setUserId("ui005");
       // biddingDetail.setBidTaskId(bidTaskId);
        log.info(biddingDetail.getBidTaskId());
        return this.biddingDetailService.cancelBidding(biddingDetail);

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
                                 @RequestParam String userId,
                                 @RequestParam String transactionPrice){

        return this.auctionTaskService.assignCarrier(userId,bidTaskId,transactionPrice);

    }


    /**
     *
     * @param bidTaskId
     * @return List<Order>
     * @description ：竞价页面-根据任务单号查询任务单详情
     * @author: CHENG QI
     */
    @RequestMapping(value = "/getTaskDetails")
    public Object getTaskDetailsByBidTaskId(@RequestParam(value = "bidTaskId", required = false) String bidTaskId){
        List<Order> list = auctionTaskService.getTaskDetailsByBidTaskId(bidTaskId);
//        if (list.size() == 0){
//            return "订单详情不存在，请检查订单是否出错";
//        }
        return list;
    }

    @RequestMapping(value = "/getResultDetails")
    public Object getResultDetailsByBidTaskId(@RequestParam(value = "bidTaskId", required = false) String bidTaskId){
        List<Order> list = auctionTaskService.getResultDetailsByBidTaskId(bidTaskId);
//        if (list.size() == 0){
//            return "订单详情不存在，请检查订单是否出错";
//        }
        return list;
    }



    /**
     *
     * @param userId
     * @return List<AuctionTask>
     * @description ：根据运输商id查询竞拍成功的任务单
     * @author: CHENG QI
     */
    // http://localhost:8080/STS/auctionTask/findSuccessCurrentTaskByUserId?userId=1001
    @RequestMapping(value = "/findSuccessCurrentTaskByUserId")
    public List<AuctionTask> findAllSuccessCurrentTaskByUserId(@RequestParam(value = "userId", required = false) String userId){
        return auctionTaskService.findAllSuccessCurrentTaskByUserId(userId);
    }

    /**
     * 分页实现任务单模糊查询
     * @param auctionTask
     * @param pageCode
     * @param pageSize
     * @return
     */
    @RequestMapping("/findSuccessByPage")
    public PageBean findSuccessByPage(AuctionTask auctionTask,
                                      @RequestParam(value = "pageCode", required = false,defaultValue = "1") int pageCode,
                                      @RequestParam(value = "pageSize", required = false,defaultValue = "10") int pageSize) {
        return auctionTaskService.findSuccessByPage(auctionTask, pageCode, pageSize);
    }

    /**
     * @param userId
     * @return List<AuctionTask>
     * @description : 根据运输商id查询所属任务单
     * @author: CHENG QI
     */
    // http://localhost:8080/STS/auctionTask/findAllCurrentTaskByUserId?userId=1001
    @RequestMapping(value = "findAllCurrentTaskByUserId")
    public List<AuctionTask> findAllCurrentTaskByUserId(@RequestParam(value = "userId", required = false) String userId){
        return auctionTaskService.findAllCurrentTaskByUserId(userId);
    }

    /**
     * @description 分页实现历史任务单模糊查询
     * @param auctionTask
     * @param pageCode
     * @param pageSize
     * @return
     * @author : CHENG QI
     */
    // http://localhost:8080/STS/auctionTask/findHistoryByPage?userId=1001
    @RequestMapping("/findHistoryByPage")
    public PageBean findHistoryByPage(AuctionTask auctionTask,
                                      @RequestParam(value = "pageCode", required = false,defaultValue = "1") int pageCode,
                                      @RequestParam(value = "pageSize", required = false,defaultValue = "10") int pageSize) {

        return auctionTaskService.findHistoryByPage(auctionTask, pageCode, pageSize);
    }

    /**
     * 竞价结果页：修改任务单状态
     * @param bidTaskId
     * @param bidStatus
     * @return
     */
    @RequestMapping("/updateTaskStatus")
    @Transactional
    public String updateTaskStatusByTaskId(@RequestParam(value = "bidTaskId", required = false) String bidTaskId,
                                           @RequestParam(value = "bidStatus", required = false) String bidStatus){
        int i = auctionTaskService.updateTaskStatusByTaskId(bidTaskId,bidStatus);
        if (i>0) {
            return "success";
        }else {
            return "false";
        }
    }

}
