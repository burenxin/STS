package cn.jzt56.singleticketsystem.mapper;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.Order;
import com.github.pagehelper.Page;
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
                                            @Param("transportType") String transportType);

    //按订单id查询订单
    public Order findOrdersById(String orderId);

    //按id修改订单状态为已合单
    public Integer modifyStatus(String orderId);

    //生成任务单
    public Integer buildTask(AuctionTask auctionTask);
}
