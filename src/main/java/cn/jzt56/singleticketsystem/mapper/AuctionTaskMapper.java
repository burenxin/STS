package cn.jzt56.singleticketsystem.mapper;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.Order;
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
     * @description ：查询所有可竞价任务
     * @author:lzy
     */
    List<AuctionTask> findAllCurrentTask(String userId);


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



}
