package cn.jzt56.singleticketsystem.mapper;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.BiddingDetail;
import cn.jzt56.singleticketsystem.entity.Order;
import cn.jzt56.singleticketsystem.entity.entityView.BiddingDetailView;
import cn.jzt56.singleticketsystem.tools.AuctionTaskView;
import cn.jzt56.singleticketsystem.tools.PageBean;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

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
     * @description : 根据运输商id查询竞拍成功的任务单
     * @author : CHENG QI
     */
    List<AuctionTask> findAllSuccessCurrentTaskByUserId(String userId);

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
    int assignCarrier(String userId,String bidTaskId);
    
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

}
