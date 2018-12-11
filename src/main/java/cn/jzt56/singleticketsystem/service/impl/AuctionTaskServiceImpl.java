package cn.jzt56.singleticketsystem.service.impl;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.Order;
import cn.jzt56.singleticketsystem.mapper.AuctionTaskMapper;
import cn.jzt56.singleticketsystem.mapper.OrderMapper;
import cn.jzt56.singleticketsystem.service.AuctionTaskService;
import cn.jzt56.singleticketsystem.tools.AuctionTaskView;
import cn.jzt56.singleticketsystem.tools.PageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 11:22 2018/12/5
 * @ Description：竞价业务实现类
 */
@Service
@Slf4j
@Component
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
        auctionTaskView.setUserId("ui001");
        Page<AuctionTaskView> page= this.auctionTaskMapper.findBidded(auctionTaskView);

        return new PageBean(page.getTotal(),page.getResult());
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
