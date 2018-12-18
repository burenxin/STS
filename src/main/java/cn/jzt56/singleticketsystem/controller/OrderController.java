package cn.jzt56.singleticketsystem.controller;

import cn.jzt56.singleticketsystem.entity.Order;
import cn.jzt56.singleticketsystem.tools.PageBean;
import cn.jzt56.singleticketsystem.tools.Result;
import cn.jzt56.singleticketsystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
     *
     * @param order    查询条件
     * @param pageCode 当前页
     * @param pageSize 每页显示的记录数
     * @return
     */
    @RequestMapping("/findByConPage")
    public PageBean findByConPage(Order order,
                                  @RequestParam(value = "pageCode", required = false) int pageCode,
                                  @RequestParam(value = "pageSize", required = false) int pageSize) {
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
            orderService.update(order);
            return new Result(true, "修改成功");
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
     */
    @RequestMapping("deleteOrder")
    public Result deleteOrder(@RequestBody Order order) {
        try {
            orderService.deleteOrder(order);
            return new Result(true, "订单删除成功");
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
