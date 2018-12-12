package cn.jzt56.singleticketsystem.controller;


import cn.jzt56.singleticketsystem.service.OrderHandlerService;
import cn.jzt56.singleticketsystem.tools.PageBean;
import cn.jzt56.singleticketsystem.tools.Result;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public PageBean findOrdersByCondition(@RequestBody String jsonStr) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
//        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        //序列化字符串
        JsonNode rootNode = mapper.readTree(jsonStr);

        //去掉jackson转换字符串时加的双引号
        String startTime = mapper.writeValueAsString(rootNode.path("startTime")).replace("\"","");
        String endTime = mapper.writeValueAsString(rootNode.path("endTime")).replace("\"","");
        String transportType = mapper.writeValueAsString(rootNode.path("transportType")).replace("\"","");
        String pageCodeStr = mapper.writeValueAsString(rootNode.path("pageCode"));
        String pageSizeStr = mapper.writeValueAsString(rootNode.path("pageSize"));
        //字符串转换成整型
        int pageCode = Integer.parseInt(pageCodeStr);
        int pageSize = Integer.parseInt(pageSizeStr);

        return orderHandlerService.findOrderByCondition(startTime,endTime,transportType,pageCode,pageSize);
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
        else if(isSuccess == 0){
            result.setSuccess(false);
            result.setMessage("打包失败，未知异常");
        }
        return result;
    }
}
