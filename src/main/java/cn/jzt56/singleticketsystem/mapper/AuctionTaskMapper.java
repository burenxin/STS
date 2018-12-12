package cn.jzt56.singleticketsystem.mapper;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.Order;
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
    List<AuctionTask> findAllCurrentTask(AuctionTask auctionTask);

    /**
     * @method
     * @description : 查询当前用户已竞价任务
     * @author:lzy
     */
    List<AuctionTask> findBidded(String userId);


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
     * @description 分页实现历史任务单模糊查询
     * @param auctionTask
     * @return
     * @author : CHENG QI
     */
    Page<AuctionTask> findHistoryByPage(AuctionTask auctionTask);

    /**
     * @description 修改任务单状态，同时修改任务单包含的订单的订单状态
     * @param bidTaskId
     * @author ：CHENG QI
     */
     int updateTaskStatusByTaskId(String bidTaskId);
}
