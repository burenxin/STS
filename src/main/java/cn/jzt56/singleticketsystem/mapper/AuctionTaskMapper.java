package cn.jzt56.singleticketsystem.mapper;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.BiddingDetail;
import cn.jzt56.singleticketsystem.entity.Order;
import cn.jzt56.singleticketsystem.entity.entityView.BiddingDetailView;
import cn.jzt56.singleticketsystem.tools.AuctionTaskView;
import cn.jzt56.singleticketsystem.tools.PageBean;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 9:50 2018/12/5
 * @ Description：任务单操作Mapper
 */
@Mapper
public interface AuctionTaskMapper {

    /**
     * @method
     * @description ：查询竞价任务
     * @author:lzy
     */
    Page<AuctionTask> findAllCurrentTask(AuctionTask auctionTask);

    /**
     * @method
     * @description : 查询当前用户已竞价任务
     * @author:lzy
     */
    Page findBidded(AuctionTaskView auctionTaskView);




//    /**
//     * @method
//     * @description :竞拍成功
//     * @author:lzy
//     */
//    int biddedSuccess(AuctionTaskView auctionTaskView);
//    /**
//     * @method
//     * @description ：竞价失败
//     * @author:lzy
//     */
//    int biddedFail(AuctionTaskView auctionTaskView);
//
//    int biddedFailOne(AuctionTaskView auctionTaskView);
//
//    /**
//     * @method
//     * @description ：无人竞价
//     * @author:lzy
//     */
//    int biddedFailTwo();
    /**
     * @Date  12.17 16:47
     * @description :查询所有竞价未完成任务
     * @author :lzy
    */
    List<AuctionTask> findAllBiddingTask();

    /**
     * @method
     * @description :biddeTask 所有已发布任务(与条件筛选)
     * @author:lzy
     */
    Page<AuctionTask> biddeTask(AuctionTask auctionTask);
    /**
     * @method
     * @description :指派承运商
     * @author:lzy
     */
    int assignCarrier(String userId,String bidTaskId,String transactionPrice);

    /**
     * @description :查询详情运输详情（竞价成功的信息）
     * @author :lzy
    */
    BiddingDetailView findBiddingDetailView(String bidTaskId);

    /**
     * @description :判断是否为空
     * @author :lzy
    */
    int findBiddingDetailViewNumber(String bidTaskId);



    /**
     * @Date12.17 20：31
     * @description :截单biddingEnd
     * @author :lzy
    */
    int biddingEnd(BiddingDetail biddingDetail);



//    /**
//     * @param bidTaskId
//     * @return
//     * @description ：根据任务单号查询任务单详情
//     * 由于某些原因，此mapper写在OrderMapper中
//     * @author: CHENG QI
//     */
//    List<Order> getTaskDetailsByBidTaskId(String bidTaskId);

    /**
     * @param userId
     * @return List<AuctionTask>
     * @description : 根据运输商id查询竞拍成功的任务单(当前任务单)
     * @author : CHENG QI
     */
    List<AuctionTask> findAllSuccessCurrentTaskByUserId(String userId);

    /**
     * @description 分页实现历史任务单模糊查询
     * @param auctionTask
     * @return
     * @author : CHENG QI
     */
    Page<AuctionTask> findHistoryByPage(AuctionTask auctionTask);

    /**
     * 分页实现模糊查询竞拍成功的任务单
     * @author CHENG QI
     */
    Page<AuctionTask> findSuccessByPage(AuctionTask auctionTask);

    /**
     * @description : 根据运输商id查询所属任务单
     * @param userId
     * @return
     * @author : CHENG QI
     */
    List<AuctionTask> findAllCurrentTaskByUserId(String userId);

    /**
     * @description 修改任务单状态，同时修改任务单包含的订单的订单状态
     * @param bidTaskId
     * @param bidStatus
     * @author ：CHENG QI
     */
     int updateTaskStatusByTaskId(@Param(value = "bidTaskId") String bidTaskId, @Param(value = "bidStatus") String bidStatus);
}
