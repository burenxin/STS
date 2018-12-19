package cn.jzt56.singleticketsystem.controller;

import cn.jzt56.singleticketsystem.entity.Order;
import cn.jzt56.singleticketsystem.tools.PageBean;
import cn.jzt56.singleticketsystem.tools.Result;
import cn.jzt56.singleticketsystem.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    /*
     所有订单查询
     */
    @RequestMapping("/findAll")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    /**
     * 分页查询
     *  修改（去掉@@RequestBody）文梁伟
     * @param order    查询条件
     * @param pageCode 当前页
     * @param pageSize 每页显示的记录数
     * @return
     *
     */
    @RequestMapping("/findByConPage")
    public PageBean findByConPage(Order order,
                                  @RequestParam(value = "pageCode", required = false) Integer pageCode,
                                  @RequestParam(value = "pageSize", required = false) Integer pageSize) {
          return orderService.findByPage(order, pageCode, pageSize);
    }

    /**
     * 新增订单
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Result create(@RequestBody Order order) {
        try {
            orderService.create(order);
            return new Result(true, "创建成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    /**
     * 更新数据成功
     *
     * @param order
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody Order order) {
        try {
            if(!order.getOrderId() .equals("") && order.getOrderId().length() != 0) {
                if(order.getStatus().equals("0")){//状态为刚生成才允许修改
                    log.info(order.getOrderId());
                    orderService.update(order);
                    return new Result(true, "修改成功");
                }else{
                    return new Result(false, "不能修改");
                }

            }else{
                return new Result(false, "请选中一行");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    /**
     * 批量删除数据(修改订单状态)
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody String... ids) {
        try {
            orderService.delete(ids);
            return new Result(true, "订单删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }
    /**
    删除订单
     *添加判断，如果订单状态不为“0”，则不能删除--文梁伟
     */
    @RequestMapping(value = "/deleteOrder",method = RequestMethod.POST)
    public Result deleteOrder(@RequestBody Order order) {
        try {
            //log.info(order.getOrderId());
            //log.info(order.getStatus());
            if(!order.getOrderId().equals("")) {
                if (order.getStatus().equals("刚生成")) {
                    orderService.deleteOrder(order);
                    return new Result(true, "订单删除成功");
                } else if (order.getStatus().equals("已签收")) {
                    return new Result(false, "该订单已签收,不能操作删除");
                } else if (order.getStatus().equals("配送中")) {
                    return new Result(false, "该订单正在安排配送，不能操作删除");
                } else {
                    return new Result(false, "该订单正在合单，不能操作删除");
                }
            }else{
                return new Result(false, "订单号不能为空");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    /**
     * 根据id查询
     *
     * @param orderNum
     * @return
     */
    @RequestMapping("/findById")
    public List<Order> findById(@RequestParam(value = "orderNum", required = false) String orderNum) {
        return orderService.findById(orderNum);
    }
    @RequestMapping("/findByUserId")
//    public List<Order> findByUserId(@RequestParam(value = "userId", required = false) String userId) {
//        return orderService.findByUserId(userId);
//    }
    public PageBean findByUserId(Order order,
                                  @RequestParam(value = "pageCode", required = false) int pageCode,
                                  @RequestParam(value = "pageSize", required = false) int pageSize) {
        return orderService.findByUserId(order, pageCode, pageSize);
    }
}
