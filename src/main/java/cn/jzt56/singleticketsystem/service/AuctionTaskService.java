package cn.jzt56.singleticketsystem.service;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.Order;
import cn.jzt56.singleticketsystem.tools.AuctionTaskView;
import cn.jzt56.singleticketsystem.tools.PageBean;

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
    PageBean findAllCurrentTask(AuctionTask auctionTask, int pageCode, int pageSize);

    /**
     * @method
     * @description :查询当前用户已竞价任务
     * @author:lzy
     */
   PageBean findBidded(AuctionTaskView auctionTaskView, int pageCode, int pageSize);

    /**
     * @param bidTaskId
     * @return
     * @description ：根据任务单号查询任务单详情
     * @author: CHENG QI
     */
    List<Order> getTaskDetailsByBidTaskId(String bidTaskId);

    /**
     * @param userId
     * @return List<AuctionTask>
     * @description : 根据运输商id查询竞拍成功的任务单
     * @author : CHENG QI
     */
    List<AuctionTask> findAllSuccessCurrentTaskByUserId(String userId);
}
