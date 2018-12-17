package cn.jzt56.singleticketsystem.service.impl;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.Order;
import cn.jzt56.singleticketsystem.mapper.OrderHandlerMapper;
import cn.jzt56.singleticketsystem.service.OrderHandlerService;
import cn.jzt56.singleticketsystem.tools.CreateNumber;
import cn.jzt56.singleticketsystem.tools.CreateUUID;
import cn.jzt56.singleticketsystem.tools.PageBean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @ Author     ：孙帅
 * @ Date       ：Created in 11:22 2018/12/6
 * @ Description：订单处理实现类
 */

@Service
public class OrderHandlerServiceImp implements OrderHandlerService {

    @Autowired(required = false)
    OrderHandlerMapper orderHandlerMapper;

    //根据起始时间或运输类型查出相应的订单
    @Override
    public PageBean findOrderByCondition(String jsonStr){
        String startTime= null;
        String endTime= null;

        String pageCodeStr= null;
        String pageSizeStr= null;
//        Order order = new Order();
        Order jsonOrder = null;
        //搜索状态是刚生成的订单
        String status = "0";
        try {
            ObjectMapper mapper = new ObjectMapper();
            //序列化字符串
            JsonNode rootNode  = mapper.readTree(jsonStr);
            //去掉jackson转换字符串时加的双引号
            startTime = mapper.writeValueAsString(rootNode.path("startTime")).replace("\"","");
            endTime = mapper.writeValueAsString(rootNode.path("endTime")).replace("\"","");

//            String orderStr = mapper.writeValueAsString(rootNode.path("order")).replace("\"","");
            String orderStr = mapper.writeValueAsString(rootNode.path("order"));
            jsonOrder = mapper.readValue(orderStr,Order.class);
            if(jsonOrder == null){
                jsonOrder = new Order();
            }
            jsonOrder.setStatus(status);


            pageCodeStr = mapper.writeValueAsString(rootNode.path("pageCode"));
            pageSizeStr = mapper.writeValueAsString(rootNode.path("pageSize"));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        order.setTransportType(jsonOrder.getTransportType());
//        order.setStartArea(jsonOrder.getStartArea());
//        order.setEndArea(jsonOrder.getEndArea());
//        order.setStatus(status);

        //字符串转换成整型
        int pageCode = Integer.parseInt(pageCodeStr);
        int pageSize = Integer.parseInt(pageSizeStr);

        //扩大检索范围为最后一天24:00截至
        if(endTime != null && !endTime.equals("")) {
            endTime += " 23:59:59";
        }

        //启用PageHelper
        PageHelper.startPage(pageCode,pageSize);
        Page<Order> orderList  = orderHandlerMapper.findOrderByCondition(startTime, endTime, jsonOrder);
        return new PageBean(orderList.getTotal(),orderList.getResult());
    }

    //订单手动打包生成任务单
    @Override
    public Integer buildTask(String[] orderIds) {
        //是否冷藏
        String taskType = null;//默认冷藏
        int TypeChangeTime = 0;// transportTypeFlag的变化次数
        List<Order> orderList = new ArrayList<>();
        String orderIdsInTask = "";//订单id
        int totalQuantity = 0;//总件数
        BigDecimal totalVolume = new BigDecimal("0.00");//总体积
        BigDecimal totalweight = new BigDecimal("0.00");//总重量
        BigDecimal proposedPrice = new BigDecimal("0.00");//合计金额
        String pickArea = null;//提货地区
        String deliverArea = null;//送货地区
        String serviceTime = null;//送达时间
        String bidStatus = "3";//竞拍状态(未发布)
//        String bidTaskId = CreateUUID.getUUID32();//生成订单id
        String bidTaskNum = null;
        try {
            bidTaskNum = CreateNumber.GetNumber("RW");//生成订单号
        } catch (Exception e) {
            e.printStackTrace();
        }
        Order order = null;

        for (int i = 0;i < orderIds.length;i++){
            order = orderHandlerMapper.findOrdersById(orderIds[i]);
            orderList.add(order);
//            /**
//             * 确定任务类型（冷藏或非冷藏）
//             * 如果order对象中的TransportType与transportTypeFlag不同
//             * TypeChangeTime+1
//             * 如果TypeChangeTime的值大于1
//             * 则认为所选订单中有不同的TransportType
//             */
//            if(!order.getTransportType().equals(taskType)){
//                taskType = order.getTransportType();
//                TypeChangeTime++;
//                if(TypeChangeTime > 1){
//                    return -1;//运输类型不同
//                }
//            }
            //计算商品件数
            if(order.getGoodsCount()!=null && !order.getGoodsCount().equals("")){
                totalQuantity += Integer.parseInt(order.getGoodsCount());
            }
            //计算商品体积
            if (order.getVolume() != null && !order.getVolume().equals("")){
                totalVolume = totalVolume.add(new BigDecimal(order.getVolume()));
            }
            //计算商品重量
            if (order.getWeight() != null && !order.getWeight().equals("")){
                totalweight = totalweight.add(new BigDecimal(order.getWeight()));
            }
            //计算商品总金额
            proposedPrice = proposedPrice.add(order.getTransportPrices());
            //确定提货、收货地点和收货时间、运输类型
            if(i==0){
                pickArea = order.getStartArea();
                deliverArea = order.getEndArea();
                serviceTime = order.getReceivingTime();
                taskType = order.getTransportType();
            }
            //取订单中最小的收货时间
            if(serviceTime.compareTo(order.getReceivingTime())>0){
                serviceTime = order.getReceivingTime();
            }
            if(!order.getTransportType().equals(taskType)){
                return -1;//运输类型不同
            }
            //将订单id拼接，以逗号隔开
            orderIdsInTask += order.getOrderId()+",";
        }

        orderIdsInTask = orderIdsInTask.substring(0,orderIdsInTask.lastIndexOf(","));//去掉订单字段最后一个逗号
//        serviceTime = serviceTime.split(" ")[0];//只保留年月日
        AuctionTask auctionTask = new AuctionTask();
        auctionTask.setOrderList(orderList);
        auctionTask.setOrderId(orderIdsInTask);
        auctionTask.setTotalQuantity(String.valueOf(totalQuantity));
        auctionTask.setTotalVolume(String.valueOf(totalVolume));
        auctionTask.setTotalWeight(String.valueOf(totalweight));
        auctionTask.setProposedPrice(proposedPrice);
        auctionTask.setPickArea(pickArea);
        auctionTask.setDeliverArea(deliverArea);
        auctionTask.setServiceTime(serviceTime);
        auctionTask.setBidStatus(bidStatus);
        auctionTask.setTaskType(taskType);
//        auctionTask.setBidTaskId(bidTaskId);
        auctionTask.setBidTaskNum(bidTaskNum);
        //插入任务单
        Integer isSuccess = orderHandlerMapper.buildTask(auctionTask);

        if(isSuccess == 1){
            String orderIdsForHandle[] = orderIdsInTask.split(",");
            String status = "1";//订单状态（已合单）
            int i = 0;
            while (isSuccess ==1 && i < orderIdsForHandle.length){
                //将订单表的状态设为已合单状态并填入任务单Id
                isSuccess = orderHandlerMapper.modifyOrder(status,auctionTask.getBidTaskId(),orderIdsForHandle[i]);
                i++;
            }
            return  isSuccess;
        }
        return 0;
    }

    @Override
    //发布任务单
    public Integer taskIssue(String[] taskIds) {
        //此对象用来传参
        AuctionTask auctionTask = new AuctionTask();
//        String auctionTaskId = "";
//        //将字符串数组拼接成一个字符串，用逗号隔开
//        for (int i =0;i<taskIds.length;i++){
//            auctionTaskId +=taskIds[i]+",";
//        }
//        //去掉最后一个逗号
//        auctionTaskId = auctionTaskId.substring(0,auctionTaskId.lastIndexOf(","));
//        //拼上"()"用于sql操作 例：auctionTaskId=(111,222)
//        auctionTaskId = "(" + auctionTaskId + ")";


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time = System.currentTimeMillis();//获得系统当前时间的毫秒数
        //获取发布时间
        String releaseTime = sdf.format(time);//转换成标准年月日的形式
        time += 120*1000*60;//在当前系统时间的基础上往后加2小时
        //获取封盘时间
        String sealedDiskTime = sdf.format(time);
//        auctionTask.setBidTaskId(auctionTaskId);
        auctionTask.setReleaseTime(releaseTime);
        auctionTask.setSealedDiskTime(sealedDiskTime);
        auctionTask.setBidStatus("1");//设置状态为竞拍中
        Integer result = 0;
        for(int i =0;i<taskIds.length;i++){
            auctionTask.setBidTaskId(taskIds[i]);
            result = orderHandlerMapper.taskIssue(auctionTask);
            if (result != 1){
                return result;
            }
        }
//        if(result == taskIds.length){
//            return 1;//发布成功
//        }
        return result;//发布失败
    }

    //关联查询任务单和订单
    @Override
    public PageBean findTaskByCondition(String jsonStr) {
        String pageCodeStr = null;
        String pageSizeStr = null;
        String bidStatus = "3";//任务单未发布
        AuctionTask auctionTask = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonStr);
            //序列化字符串
            pageCodeStr = mapper.writeValueAsString(rootNode.path("pageCode"));
            pageSizeStr = mapper.writeValueAsString(rootNode.path("pageSize"));

//            String auctionTaskJson = mapper.writeValueAsString(rootNode.path("auctionTask")).replace("\"","");
            String auctionTaskJson = mapper.writeValueAsString(rootNode.path("auctionTask"));

            auctionTask = mapper.readValue(auctionTaskJson, AuctionTask.class);
            if(auctionTask == null){
                auctionTask = new AuctionTask();
            }
            auctionTask.setBidStatus(bidStatus);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //字符串转换成整型
        int pageCode = Integer.parseInt(pageCodeStr);
        int pageSize = Integer.parseInt(pageSizeStr);

        //启用pageHelper
        PageHelper.startPage(pageCode,pageSize);
        //处理auctionTask为null的情况

        Page<AuctionTask> auctionTaskList  = orderHandlerMapper.findTaskByCondition(auctionTask);
        return new PageBean(auctionTaskList.getTotal(),auctionTaskList.getResult());
    }
}
