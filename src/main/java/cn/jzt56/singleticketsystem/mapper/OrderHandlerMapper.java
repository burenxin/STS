package cn.jzt56.singleticketsystem.mapper;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.Order;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ Author:孙帅
 * @ Date：Created in 9:50 2018/12/5
 * @ Description：订单处理Mapper
 */
@Mapper
public interface OrderHandlerMapper {

    //按时间段或运输类型查询订单
    public Page<Order> findOrderByCondition(@Param("startTime") String startTime,
                                            @Param("endTime") String endTime,
                                            @Param("order") Order order);

    //按订单id查询订单
    public Order findOrdersById(String orderId);

//    //按id修改订单状态为已合单
//    public Integer modifyStatus(String orderId);

    //生成任务单
    public Integer buildTask(AuctionTask auctionTask);



//    //按任务单id查询任务单中的ord_Id
//    public String findOrdId(String taskId);

    //订单修改task_Id字段
    public Integer modifyOrder(@Param(value = "status") String status,
                               @Param(value = "taskId") String taskId,
                               @Param(value = "orderId") String orderId);

    //发布任务单
    public Integer taskIssue(AuctionTask auctionTask);

    //关联查询任务单和订单
    public Page<AuctionTask> findTaskByCondition(@Param("auctionTask") AuctionTask auctionTask);
}
