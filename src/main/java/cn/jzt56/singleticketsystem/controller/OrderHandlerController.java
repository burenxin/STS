package cn.jzt56.singleticketsystem.controller;


import cn.jzt56.singleticketsystem.service.OrderHandlerService;
import cn.jzt56.singleticketsystem.tools.PageBean;
import cn.jzt56.singleticketsystem.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @ Author     ：孙帅
 * @ Date       ：Created in 11:31 2018/12/6
 * @ Description：订单控制层
 */
@Controller
@RequestMapping(value = "/orderHandler")
public class OrderHandlerController {

    @Autowired
    OrderHandlerService orderHandlerService;

    /**
     * 查询订单列表
     * @param jsonStr  json字符串参数
     * @return 订单列表
     */
    @RequestMapping(value = "/findOrders",method= RequestMethod.POST)
    @ResponseBody
    public PageBean findOrdersByCondition(@RequestBody String jsonStr) {


        return orderHandlerService.findOrderByCondition(jsonStr);
    }
    /**
     * 手动打包
     * @param orderIds 需要打包的订单Id
     * @return 订单列表
     */
    @RequestMapping(value = "/buildTask",method = RequestMethod.POST)
    @ResponseBody
    public Result buildTask(@RequestBody String[] orderIds){
        Integer isSuccess = orderHandlerService.buildTask(orderIds);
        Result result = new Result();
        if(isSuccess == 1){
            result.setSuccess(true);
            result.setMessage("打包成功");
        }
        else if(isSuccess == -1){
            result.setSuccess(false);
            result.setMessage("运输类型不一致");
        }
        else if(isSuccess == 2){
            result.setSuccess(false);
            result.setMessage("未选中订单");
        }
        else if(isSuccess == 0){
            result.setSuccess(false);
            result.setMessage("打包失败，未知异常");
        }
        return result;
    }

    /**
     * 自动打包
     * @param jsonStr  json字符串参数
     * @return 打包结果
     */
    @RequestMapping(value = "/autoBuildTask",method= RequestMethod.POST)
    @ResponseBody
    public Result autoBuildTask(@RequestBody String jsonStr) {
        Integer isSuccess = orderHandlerService.autoBuildTask(jsonStr);
        Result result = new Result();
        if(isSuccess == 1){
            result.setSuccess(true);
            result.setMessage("打包成功");
        }
        else if(isSuccess == 2){
            result.setSuccess(false);
            result.setMessage("未检索到符合条件的订单");
        }
        else{
            result.setSuccess(false);
            result.setMessage("打包失败，未知异常");
        }
        return result;
    }

    /**
     * 任务单发布
     * @param taskIds 需要发布的订单Id
     * @return 订单列表
     */
    @RequestMapping(value = "/taskIssue",method= RequestMethod.POST)
    @ResponseBody
    public Result taskIssue(@RequestBody String[] taskIds){
        Result result = new Result();
        Integer isSuccess =  orderHandlerService.taskIssue(taskIds);
        if (isSuccess == 1){
            result.setSuccess(true);
            result.setMessage("发布成功");
        }
        else if(isSuccess == 2){
            result.setSuccess(false);
            result.setMessage("未选中任务单");
        }
        else{
            result.setSuccess(false);
            result.setMessage("发布失败");
        }
        return result;
    }

    /**
     * 任务单查询
     * @param jsonStr 查询条件以及分页
     * @return 任务单列表
     */
    @RequestMapping(value = "/findTask",method= RequestMethod.POST)
    @ResponseBody
    public PageBean findTask(@RequestBody String jsonStr) throws IOException {

        return orderHandlerService.findTaskByCondition(jsonStr);
    }

    /**
     * 拆单、拆包、删除拆包后空的包
     * @param jsonStr
     * @return
     */
    @RequestMapping(value = "/demolitionOrder",method= RequestMethod.POST)
    @ResponseBody
    public Result demolitionOrder(@RequestBody String jsonStr){

        return  orderHandlerService.demolitionOrder(jsonStr);

    }

}
