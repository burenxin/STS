package cn.jzt56.singleticketsystem.service.impl;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.BiddingDetail;
import cn.jzt56.singleticketsystem.entity.CarrierInfo;
import cn.jzt56.singleticketsystem.entity.Order;
import cn.jzt56.singleticketsystem.mapper.AuctionTaskMapper;
import cn.jzt56.singleticketsystem.mapper.BiddingDetailMapper;
import cn.jzt56.singleticketsystem.mapper.CarrierInfoMapper;
import cn.jzt56.singleticketsystem.mapper.OrderMapper;
import cn.jzt56.singleticketsystem.service.AuctionTaskService;
import cn.jzt56.singleticketsystem.tools.AuctionTaskView;
import cn.jzt56.singleticketsystem.tools.PageBean;
import cn.jzt56.singleticketsystem.tools.Result;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 11:22 2018/12/5
 * @ Description：竞价业务实现类
 */
@Service
@Slf4j
public class AuctionTaskServiceImpl implements AuctionTaskService {

    @Autowired
    private AuctionTaskMapper auctionTaskMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CarrierInfoMapper carrierInfoMapper;

    @Autowired
    private BiddingDetailMapper biddingDetailMapper;





    /**
     * @param bidTaskId
     * @return
     * @description ：根据任务单号查询任务单详情
     * 此实现类调用的是OrderMapper中的Mapper接口。
     * @author: CHENG QI
     */
    @Override
    public List<Order> getTaskDetailsByBidTaskId(String bidTaskId) {
        return orderMapper.getTaskDetailsByBidTaskId(bidTaskId);
    }

    @Override
    public List<Order> getResultDetailsByBidTaskId(String bidTaskId) {
        return orderMapper.getResultDetailsByBidTaskId(bidTaskId);
    }

    /**
     * @param userId
     * @return List<AuctionTask>
     * @description : 根据运输商id查询竞拍成功的任务单
     * @author : CHENG QI
     * ,String bidStatus
     */
    @Override
    public List<AuctionTask> findAllSuccessCurrentTaskByUserId(String userId) {
        return this.auctionTaskMapper.findAllSuccessCurrentTaskByUserId(userId);
    }

    /**
     * @param auctionTask
     * @param pageCode
     * @param pageSize
     * @return
     * @description :分页实现任务单模糊查询
     * @author CHENG QI
     */
    @Override
    public PageBean findSuccessByPage(AuctionTask auctionTask, int pageCode, int pageSize) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode, pageSize);
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        Page<AuctionTask> page = auctionTaskMapper.findSuccessByPage(auctionTask);

        return new PageBean(page.getTotal(), page.getResult());
    }

    /**
     * @param userId
     * @return
     * @description : 根据运输商id查询所属任务单
     * @author : CHENG QI
     */
    @Override
    public List<AuctionTask> findAllCurrentTaskByUserId(String userId) {
        return this.auctionTaskMapper.findAllCurrentTaskByUserId(userId);
    }

    /**
     * @param auctionTask
     * @param pageCode
     * @param pageSize
     * @return
     * @description 分页实现历史任务单模糊查询
     * @author : CHENG QI
     */
    @Override
    public PageBean findHistoryByPage(AuctionTask auctionTask, int pageCode, int pageSize) {
        PageHelper.startPage(pageCode, pageSize);
        Page<AuctionTask> page =
                auctionTaskMapper.findHistoryByPage(auctionTask);

        return new PageBean(page.getTotal(), page.getResult());
    }

    /**
     * @param bidTaskId
     * @param bidStatus
     * @description 修改任务单状态，同时修改任务单包含的订单的订单状态
     * @author ：CHENG QI
     */
    @Override
    @Transactional
    public int updateTaskStatusByTaskId(String bidTaskId,String bidStatus) {
        int i = auctionTaskMapper.updateTaskStatusByTaskId(bidTaskId,bidStatus);
        return i;
    }



    /**
     * @method
     * @description :查询竞价任务
     * @author:lzy
     */
    @Override
    public PageBean findAllCurrentTask(AuctionTask auctionTask, int pageCode, int pageSize) {
        //分页
        PageHelper.startPage(pageCode,pageSize);

        Page<AuctionTask> page=auctionTaskMapper.findAllCurrentTask(auctionTask);

        //log.info(page.getResult().toString()+pageCode+pageSize);

        return new PageBean(page.getTotal(),page.getResult());
    }
    /**
     * @method
     * @description :查询当前用户的已竞价任务
     * @author:lzy
     */

    @Override
    public PageBean findBidded(AuctionTaskView auctionTaskView,int pageCode, int pageSize) {

        PageHelper.startPage(pageCode,pageSize);
        //auctionTaskView.setUserId("ui001");
        Page<AuctionTaskView> page= this.auctionTaskMapper.findBidded(auctionTaskView);

        return new PageBean(page.getTotal(),page.getResult());
    }


    /**
     * @method
     * @description :报价任务截单
     * @author:lzy
     */
    @Override
    public void auctionTaskClose(){
        log.info("输出AuctionTaskServiceImpl");
        List<AuctionTask> list=this.auctionTaskMapper.findAllBiddingTask();//查询所有未完成竞价任务
        BiddingDetail biddingDetail;
        for (AuctionTask auctionTask:list
                ) {
            if(auctionTask.getTaskType().equals("1")) {//判断是否普通类型，'任务类型  0.冷藏品、1.普通'
                auctionTask.setTaskType("0");//运输方式,0普通1冷藏
            }else {
                auctionTask.setTaskType("1");
            }
            CarrierInfo carrierInfo = this.carrierInfoMapper.findMinCarrierInfo(auctionTask);

            //log.info(carrierInfo.getWeightPrice().toString());
            if (this.biddingDetailMapper.findBiddingDetailNum(auctionTask.getBidTaskId()) > 0 ? true : false) {
                biddingDetail = this.biddingDetailMapper.findMinDetailByTaskId(auctionTask.getBidTaskId());

            } else {
                biddingDetail = new BiddingDetail();


            }
            biddingDetail.setStatus("2");
            if ((biddingDetail.getQuotedPrice() == null)||(carrierInfo.getWeightPrice().compareTo(biddingDetail.getQuotedPrice()) == -1 && (biddingDetail.getQuotedPrice() != null)) ) {//流拍时将价格与Id替换为系统配送的
                biddingDetail.setBidTaskId(auctionTask.getBidTaskId());
                biddingDetail.setQuotedPrice(BigDecimal.ZERO);//
                biddingDetail.setUserId("");
                biddingDetail.setStatus("0");
            }
            log.info(biddingDetail.toString());
            //截单
            this.auctionTaskMapper.biddingEnd(biddingDetail);
        }




//        }


//        List<AuctionTaskView> list=this.biddingDetailMapper.findBiddingEnd(true);
//        for (AuctionTaskView auctionTaskView:list
//        ) {
//            CarrierInfo carrierInfo=this.carrierInfoMapper.findMinCarrierInfo(auctionTaskView);//查询最低竞价
//
//            if(carrierInfo.getWeightPrice().compareTo(auctionTaskView.getQuotedPrice())==-1) {//流派
//                auctionTaskView.setQuotedPrice(carrierInfo.getWeightPrice());//
//                auctionTaskView.setUserId(carrierInfo.getUserId());
//
//            }
//            this.auctionTaskMapper.biddedFailOne(auctionTaskView);//流拍和竞拍成功（存在竞拍明细的）
//
//
////            if(this.auctionTaskMapper.biddedSuccess(auctionTaskView)>=1?false:true)
////                this.auctionTaskMapper.biddedFailOne(auctionTaskView);
//        }
//        this.auctionTaskMapper.biddedFailTwo();
    }

    /**
     * @method
     * @description :biddeTask 所有已发布任务(与条件筛选)
     * @author:lzy
     */
    @Override
    public PageBean biddeTask(AuctionTask auctionTask,int pageCode,int pageSize) {
        this.auctionTaskClose();//截单
        PageHelper.startPage(pageCode,pageSize);
        Page<AuctionTask> page= this.auctionTaskMapper.biddeTask(auctionTask);
        return  new PageBean(page.getTotal(),page.getResult());
    }
    /**
     * @method
     * @description :指派承运商
     * @author:lzy
     */
    @Override
    public Boolean assignCarrier(String jsonStr){
    String userId=null;
    String bidTaskId=null;
    String transactionPrice=null;
    try{
        ObjectMapper mapper = new ObjectMapper();
        //序列化字符串
        JsonNode rootNode  = mapper.readTree(jsonStr);
        //去掉jackson转换字符串时加的双引号
        userId = mapper.writeValueAsString(rootNode.path("userId")).replace("\"","");
        bidTaskId = mapper.writeValueAsString(rootNode.path("bidTaskId")).replace("\"","");
        transactionPrice = mapper.writeValueAsString(rootNode.path("transactionPrice")).replace("\"","");
        log.info(userId+"///"+bidTaskId+"///"+transactionPrice);
    }catch (IOException exception ){
        exception.printStackTrace();
    }
        return this.auctionTaskMapper.assignCarrier(userId,bidTaskId,transactionPrice)==1?true:false;
    }

}
