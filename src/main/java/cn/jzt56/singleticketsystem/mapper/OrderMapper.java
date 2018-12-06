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

    void create(Order order);

    void update(Order order);

    void delete(String id);
}
