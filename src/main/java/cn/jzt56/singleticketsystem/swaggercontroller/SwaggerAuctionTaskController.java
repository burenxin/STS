package cn.jzt56.singleticketsystem.swaggercontroller;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.Order;
import cn.jzt56.singleticketsystem.service.impl.AuctionTaskServiceImpl;
import cn.jzt56.singleticketsystem.tools.PageBean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created with IDEA
 *
 * @author: CHENG QI
 * @Date: 2018/12/11
 * Time: 15:36
 */
@RestController
@RequestMapping("api")
@Api("swaggerDemoController相关的api")
@Slf4j
public class SwaggerAuctionTaskController {

    @Autowired
    private AuctionTaskServiceImpl auctionTaskService;

    @ApiOperation(value = "根据运输商id查询竞拍成功的任务单",notes = "查询数据库中对应运输商的任务单信息")
    @ApiImplicitParam(name = "userId",value = "用户id",paramType = "path",required = true,dataType = "String")
    @RequestMapping(value = "findSuccessCurrentTaskByUserId/{userId}",method = RequestMethod.POST)
    List<AuctionTask> findAllSuccessCurrentTaskByUserId(@PathVariable String userId){
        return auctionTaskService.findAllSuccessCurrentTaskByUserId(userId);
    }

    @ApiOperation(value = "分页实现任务单模糊查询",notes = "查询数据库中对应任务单号的订单信息")
    @RequestMapping(value = "/findSuccessByPage",method = RequestMethod.POST)
    public PageBean findSuccessByPage(@RequestBody String jsonStr) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        //序列化字符串
        JsonNode rootNode = mapper.readTree(jsonStr);
        String PageCodeStr = mapper.writeValueAsString(rootNode.path("pageCode"));
        String PageSizeStr = mapper.writeValueAsString(rootNode.path("pageSize"));
        //字符串转换成整型
        int pageCode = Integer.parseInt(PageCodeStr);
        log.info(pageCode+"");
        int pageSize = Integer.parseInt(PageSizeStr);
        log.info(pageSize+"");

        String auctionTaskJson = mapper.writeValueAsString(rootNode.path("auctionTask"));
        log.info(auctionTaskJson);
        AuctionTask auctionTask = mapper.readValue(auctionTaskJson,AuctionTask.class);

        return auctionTaskService.findSuccessByPage(auctionTask, pageCode, pageSize);
    }

    @ApiOperation(value = "根据运输商id查询所属任务单",notes = "查询数据库中对应运输商的任务单信息")
    @ApiImplicitParam(name = "userId",value = "用户id",paramType = "path",required = true,dataType = "String")
    @RequestMapping(value = "findAllCurrentTaskByUserId/{userId}",method = RequestMethod.POST)
    public List<AuctionTask> findAllCurrentTaskByUserId(@PathVariable String userId){
        return auctionTaskService.findAllCurrentTaskByUserId(userId);
    }

    @ApiOperation(value = "分页实现历史任务单模糊查询",notes = "查询数据库中对应任务单号的订单信息")
//    @ApiImplicitParams({
//          //  @ApiImplicitParam(name = "auctionTask", value = "任务单", dataType = "AuctionTask", paramType = "body",required = true),
//            @ApiImplicitParam(name = "pageCode", value = "当前页",   dataType = "int", paramType = "path"),
//            @ApiImplicitParam(name = "pageSize", value = "页面大小", dataType = "int", paramType = "path") })
    @RequestMapping(value = "/findHistoryByPage",method = RequestMethod.POST)
    public PageBean findHistoryByPage(@RequestBody String jsonStr) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        //序列化字符串
        JsonNode rootNode = mapper.readTree(jsonStr);
        String PageCodeStr = mapper.writeValueAsString(rootNode.path("pageCode"));
        String PageSizeStr = mapper.writeValueAsString(rootNode.path("pageSize"));
        //字符串转换成整型
        int pageCode = Integer.parseInt(PageCodeStr);
        log.info(pageCode+"");
        int pageSize = Integer.parseInt(PageSizeStr);
        log.info(pageSize+"");

        String auctionTaskJson = mapper.writeValueAsString(rootNode.path("auctionTask"));
        log.info(auctionTaskJson);
        AuctionTask auctionTask = mapper.readValue(auctionTaskJson,AuctionTask.class);
        return auctionTaskService.findHistoryByPage(auctionTask, pageCode, pageSize);
    }

//{
//        "pageCode":1,
//                "pageSize":1,
//                "auctionTask":{
//            "userId":"u1001"
//        }
//    }


//    @ApiOperation(value = "根据任务单号查询任务单详情",notes = "查询数据库中对应任务单号的订单信息")
//    @ApiImplicitParam(name = "bidTaskId",value = "任务单id",paramType = "path",required = true,dataType = "String")
//    @RequestMapping(value = "getTaskDetail/{bidTaskId}",method = RequestMethod.GET)
//    public Object getTaskDetailsByBidTaskId(@PathVariable String bidTaskId){
//        List<Order> list = auctionTaskService.getTaskDetailsByBidTaskId(bidTaskId);
//        if (list.size() == 0){
//            return "订单详情不存在，请检查订单是否出错";
//        }
//        return list;
//    }

    @ApiOperation(value = "竞价页面-根据任务单号查询任务单详情",notes = "查询数据库中对应任务单号的订单信息")
    @ApiImplicitParam(name = "bidTaskId",value = "任务单id",paramType = "path",required = true,dataType = "String")
    @RequestMapping(value = "getTaskDetails/{bidTaskId}",method = RequestMethod.GET)
    public Object getTaskDetailsByBidTaskId(@PathVariable String bidTaskId){
        List<Order> list = auctionTaskService.getTaskDetailsByBidTaskId(bidTaskId);
        if (list.size() == 0){
            return "订单详情不存在，请检查订单是否出错";
        }
        return list;
    }

    @ApiOperation(value = "竞价结果页面-根据任务单号查询任务单详情",notes = "查询数据库中对应任务单号的订单信息")
    @ApiImplicitParam(name = "bidTaskId",value = "任务单id",paramType = "path",required = true,dataType = "String")
    @RequestMapping(value = "getResultDetails/{bidTaskId}",method = RequestMethod.GET)
    public Object getTaskDetailsByBidTaskIdjjjg(@PathVariable String bidTaskId){
        List<Order> list = auctionTaskService.getResultDetailsByBidTaskId(bidTaskId);
        if (list.size() == 0){
            return "订单详情不存在，请检查订单是否出错";
        }
        return list;
    }

    @ApiOperation(value = "根据任务单号修改任务单(订单)状态",notes = "对数据库状态字段进行修改")
    @ApiImplicitParam(name = "bidTaskId",value = "任务单id",paramType = "path",required = true,dataType = "String")
    @RequestMapping(value = "updateTaskStatusByTaskId/{bidTaskId}&{bidStatus}",method = RequestMethod.GET)
    public String updateTaskStatusByTaskId(@PathVariable String bidTaskId,@PathVariable String bidStatus){
        int i = auctionTaskService.updateTaskStatusByTaskId(bidTaskId,bidStatus);
        if (i>0) {
            return "success";
        }else {
            return "false";
        }
    }

}
