package cn.jzt56.singleticketsystem.service.impl;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.Order;
import cn.jzt56.singleticketsystem.mapper.AuctionTaskMapper;
import cn.jzt56.singleticketsystem.mapper.OrderMapper;
import cn.jzt56.singleticketsystem.service.AuctionTaskService;
import cn.jzt56.singleticketsystem.tools.PageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
