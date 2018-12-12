package cn.jzt56.singleticketsystem.controller;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.BiddingDetail;
import cn.jzt56.singleticketsystem.entity.Order;
import cn.jzt56.singleticketsystem.service.AuctionTaskService;
import cn.jzt56.singleticketsystem.service.BiddingDetailService;
import cn.jzt56.singleticketsystem.tools.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public List<AuctionTask> findAllCurrentTask(HttpServletRequest request){

        //String userId=(String)request.getSession().getAttribute("userId");
        AuctionTask auctionTask=new AuctionTask();
        auctionTask.setPickArea("湖南");
        auctionTask.setUserId("ui001");
        List<AuctionTask> list=this.auctionTaskService.findAllCurrentTask(auctionTask);
        log.info(list.toString());
        return list;
    }
    /**
     * @method
     * @description:竞价
     * @author:lzy
     */
    @RequestMapping(value = "/auctionPrice")
    public void  auctionPrice(@RequestParam String quotedPrice, @RequestParam String bidTaskId){
        BiddingDetail biddingDetail=new BiddingDetail();
        biddingDetail.setDetailId("di011");
        biddingDetail.setUserId("ui001");
        biddingDetail.setQuotedPrice(quotedPrice);
        biddingDetail.setBidTaskId(bidTaskId);
        log.info(quotedPrice+bidTaskId);
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
    public void  cancelBidding( @RequestParam String bidTaskId){
        BiddingDetail biddingDetail=new BiddingDetail();
        biddingDetail.setUserId("ui005");
        biddingDetail.setBidTaskId(bidTaskId);
        log.info(bidTaskId);
        Boolean flage= this.biddingDetailService.cancelBidding(biddingDetail);
    }
    /**
     * @method
     * @description :获取当前已竞价任务
     * @author:lzy
     */
    @RequestMapping(value = "/findBidded")
    public List<AuctionTask> finDBidded(){
        return this.auctionTaskService.findBidded("ui001");
    }



    /**
     *
     * @param bidTaskId
     * @return List<Order>
     * @description ：根据任务单号查询任务单详情
     * @author: CHENG QI
     */
    // http://localhost:8080/STS/auctionTask/getTaskDetail?bidTaskId=bt005
    @RequestMapping(value = "/getTaskDetail")
    public Object getTaskDetailsByBidTaskId(@RequestParam(value = "bidTaskId", required = false) String bidTaskId){
        List<Order> list = auctionTaskService.getTaskDetailsByBidTaskId(bidTaskId);
        if (list.size() == 0){
            return "订单详情不存在，请检查订单是否出错";
        }
        return list;
    }
    //竞价页面-根据任务单号查询任务单详情
    @RequestMapping(value = "/getTaskDetailjjy")
    public Object getTaskDetailsByBidTaskIdjjy(@RequestParam(value = "bidTaskId", required = false) String bidTaskId){
        List<Order> list = auctionTaskService.getTaskDetailsByBidTaskIdjjy(bidTaskId);
        if (list.size() == 0){
            return "订单详情不存在，请检查订单是否出错";
        }
        return list;
    }

    @RequestMapping(value = "/getTaskDetailjjjg")
    public Object getTaskDetailsByBidTaskIdjjjg(@RequestParam(value = "bidTaskId", required = false) String bidTaskId){
        List<Order> list = auctionTaskService.getTaskDetailsByBidTaskIdjjjg(bidTaskId);
        if (list.size() == 0){
            return "订单详情不存在，请检查订单是否出错";
        }
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
                                  @RequestParam(value = "pageSize", required = false,defaultValue = "1") int pageSize) {
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
                                      @RequestParam(value = "pageSize", required = false,defaultValue = "2") int pageSize) {
        auctionTask.setTaskType("1");
        return auctionTaskService.findHistoryByPage(auctionTask, pageCode, pageSize);
    }

    @RequestMapping("/updateTaskStatusByTaskId")
    public String updateTaskStatusByTaskId(@RequestParam(value = "bidTaskId", required = false) String bidTaskId){
        int i = auctionTaskService.updateTaskStatusByTaskId(bidTaskId);
        if (i>0) {
            return "success";
        }else {
            return "false";
        }
    }
}
