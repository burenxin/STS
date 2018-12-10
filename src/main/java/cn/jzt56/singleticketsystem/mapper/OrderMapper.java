package cn.jzt56.singleticketsystem.mapper;

import cn.jzt56.singleticketsystem.entity.Order;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<Order> findAll();

    Page<Order> findByPage(Order order);

    List<Order> findById(String id);
    Page<Order> findByUserId(Order order);

    void create(Order order);

    void update(Order order);

    void delete(String id);

    void deleteOrder(Order order);


    /**
     * @param bidTaskId
     * @return
     * @description ：根据任务单号查询任务单详情
     * @author: CHENG QI
     */
    List<Order> getTaskDetailsByBidTaskId(String bidTaskId);
}
