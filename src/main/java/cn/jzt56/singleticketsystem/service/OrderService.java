package cn.jzt56.singleticketsystem.service;

import cn.jzt56.singleticketsystem.entity.Order;
import cn.jzt56.singleticketsystem.tools.PageBean;

import java.util.List;

public interface OrderService extends BaseService<Order>{
    /**
     * 分页查询
     * @param order 查询条件
     * @param pageCode 当前页
     * @param pageSize 每页的记录数
     * @return
     */
    PageBean findByPage(Order order, int pageCode, int pageSize);
    PageBean findByUserId(Order order, int pageCode, int pageSize);
    void deleteOrder(Order order);
}
