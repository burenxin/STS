package cn.jzt56.singleticketsystem.service.impl;

import cn.jzt56.singleticketsystem.entity.Order;
import cn.jzt56.singleticketsystem.tools.CreateUUID;
import cn.jzt56.singleticketsystem.tools.PageBean;
import cn.jzt56.singleticketsystem.mapper.OrderMapper;
import cn.jzt56.singleticketsystem.service.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    public PageBean findByUserId(Order order, int pageCode, int pageSize) {
        PageHelper.startPage(pageCode, pageSize);
        Page<Order> page = orderMapper.findByUserId(order);
        return new PageBean(page.getTotal(), page.getResult());
    }
    @Override
    public void create(Order order) {
        order.setOrderId(CreateUUID.getUUID32());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        SimpleDateFormat sdg = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        order.setCreatedTime(sdg.format(date));
        order.setUpdatedTime(sdg.format(date));
        order.setOrderNum("DD"+sdf.format(date));
        orderMapper.create(order);
    }

    @Override
    public void update(Order order) {
        Date date = new Date();
        SimpleDateFormat sdg = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        order.setUpdatedTime(sdg.format(date));
        orderMapper.update(order);
    }

    @Override
    public void delete(String... ids) {
        for (String id : ids) {
            orderMapper.delete(id);
        }
    }

    public void deleteOrder(Order order) {
        orderMapper.deleteOrder(order);
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
