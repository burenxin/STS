package cn.jzt56.singleticketsystem.service.impl;

import cn.jzt56.singleticketsystem.entity.Order;
import cn.jzt56.singleticketsystem.tools.PageBean;
import cn.jzt56.singleticketsystem.mapper.OrderMapper;
import cn.jzt56.singleticketsystem.service.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> findAll() {
        return orderMapper.findAll();
    }

    @Override
    public List<Order> findById(String id) {
        return orderMapper.findById(id);
    }
    @Override
    public List<Order> findByUserId(String id) {
        return orderMapper.findByUserId(id);
    }
    @Override
    public void create(Order order) {
        orderMapper.create(order);
    }

    @Override
    public void update(Order order) {
        orderMapper.update(order);
    }

    @Override
    public void delete(String... ids) {
        for (String id : ids) {
            orderMapper.delete(id);
        }
    }

    /**
     * 分页查询-条件查询方法
     *
     * @param order    查询条件
     * @param pageCode 当前页
     * @param pageSize 每页的记录数
     * @return
     */
    public PageBean findByPage(Order order, int pageCode, int pageSize) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode, pageSize);

        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        Page<Order> page = orderMapper.findByPage(order);

        return new PageBean(page.getTotal(), page.getResult());
    }
}
