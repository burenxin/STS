package cn.jzt56.singleticketsystem.service;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.Order;
import cn.jzt56.singleticketsystem.tools.PageBean;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 11:22 2018/12/5
 * @ Description：竞价业务业务接口
 */
public interface AuctionTaskService {

    /**
     * @method
     * @description ：查询所有可竞价任务
     * @author:lzy
     */
    List<AuctionTask> findAllCurrentTask(AuctionTask auctionTask);

    /**
     * @method
     * @description :查询当前用户已竞价任务
     * @author:lzy
     */
    List<AuctionTask> findBidded(String userId);

    /**
     * @param bidTaskId
     * @return
     * @description ：根据任务单号查询任务单详情
     * @author: CHENG QI
     */
    List<Order> getTaskDetailsByBidTaskId(String bidTaskId);

    List<Order> getResultDetailsByBidTaskId(String bidTaskId);
    /**
     * @param userId
     * @return List<AuctionTask>
     * @description : 根据运输商id查询竞拍成功的任务单
     * @author : CHENG QI
     */
    List<AuctionTask> findAllSuccessCurrentTaskByUserId(String userId);


    /**
     *
     * @param auctionTask
     * @param pageCode
     * @param pageSize
     * @description :分页实现任务单模糊查询
     * @author CHENG QI
     * @return
     */
    PageBean findSuccessByPage(AuctionTask auctionTask, int pageCode, int pageSize);

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
     * @param pageCode
     * @param pageSize
     * @return
     * @author : CHENG QI
     */
    PageBean findHistoryByPage(AuctionTask auctionTask, int pageCode, int pageSize);

    /**
     * @description 修改任务单状态，同时修改任务单包含的订单的订单状态
     * @param bidTaskId
     * @param bidStatus
     * @author ：CHENG QI
     */
    int updateTaskStatusByTaskId(String bidTaskId,String bidStatus);
}
