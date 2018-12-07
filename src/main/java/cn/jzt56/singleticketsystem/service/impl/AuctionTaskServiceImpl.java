package cn.jzt56.singleticketsystem.service.impl;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.Order;
import cn.jzt56.singleticketsystem.mapper.AuctionTaskMapper;
import cn.jzt56.singleticketsystem.mapper.OrderMapper;
import cn.jzt56.singleticketsystem.service.AuctionTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 11:22 2018/12/5
 * @ Description：竞价业务实现类
 */
@Service
public class AuctionTaskServiceImpl implements AuctionTaskService {

    @Autowired
    private AuctionTaskMapper auctionTaskMapper;

    @Autowired
    private OrderMapper orderMapper;


    /**
     * @method
     * @description :查询竞价任务
     * @author:lzy
     */
    @Override
    public List<AuctionTask> findAllCurrentTask(AuctionTask auctionTask) {
        return this.auctionTaskMapper.findAllCurrentTask( auctionTask);
    }
    /**
     * @method
     * @description :查询当前用户的已竞价任务
     * @author:lzy
     */

    @Override
    public List <AuctionTask> findBidded(String userId) {

        return this.auctionTaskMapper.findBidded(userId);
    }

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

    /**
     * @param userId
     * @return List<AuctionTask>
     * @description : 根据运输商id查询竞拍成功的任务单
     * @author : CHENG QI
     */
    @Override
    public List<AuctionTask> findAllSuccessCurrentTaskByUserId(String userId) {
        return this.auctionTaskMapper.findAllSuccessCurrentTaskByUserId(userId);
    }


}
