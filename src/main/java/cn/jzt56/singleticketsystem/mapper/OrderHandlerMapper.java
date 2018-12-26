package cn.jzt56.singleticketsystem.mapper;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.Order;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
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

    //按orderId查询taskId是否为空
    public List<String> findTaskId(ArrayList<String> list);

    //订单修改task_Id字段
    public Integer modifyOrder(@Param(value = "status") String status,
                               @Param(value = "taskId") String taskId,
                               @Param(value = "orderId") String orderId);

    //发布任务单
    public Integer taskIssue(AuctionTask auctionTask);

    //分页按条件检索任务单id
    public Page<String> findTaskIdByCondition(@Param("auctionTask") AuctionTask auctionTask);

    //关联查询任务单和订单
    public Page<AuctionTask> findTaskByIds(ArrayList<String> list);

    //分组查询订单
    public List<Order> findOrderByGroup(@Param(value = "order") Order order,
                                        @Param(value = "limit") int limit,
                                        @Param("startTime") String startTime,
                                        @Param("endTime") String endTime);

    //查询某一分组订单
    public List<Order> findTheOrders(@Param(value = "order") Order order,
                                     @Param(value = "limit") int limit);
    //拆单、拆包 mwy
    int demolitionOrder(@Param(value = "bidTaskId")String bidTaskId,
                        @Param(value = "removeOrderIds")String removeOrderIds);

    //查询保留的orderID  MWY
    List listOrderId(String bidTaskId);
    //修改任务单保存的id
    int upadteOrderId(@Param(value = "orderId") String orderId,
                      @Param(value = "bidTaskId") String bidTaskId);
    //查询此包是否为空mwy
    int findTaskIsExist(String bidTaskId);
  //删除拆包后空的包 mwy
    int deleteTask(String bidTaskId);
}
