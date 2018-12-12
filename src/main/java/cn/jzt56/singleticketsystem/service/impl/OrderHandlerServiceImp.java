package cn.jzt56.singleticketsystem.service.impl;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.Order;
import cn.jzt56.singleticketsystem.mapper.OrderHandlerMapper;
import cn.jzt56.singleticketsystem.service.OrderHandlerService;
import cn.jzt56.singleticketsystem.tools.CreateNumber;
import cn.jzt56.singleticketsystem.tools.CreateUUID;
import cn.jzt56.singleticketsystem.tools.PageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public PageBean findOrderByCondition(String startTime, String endTime, String transportType,int pageCode,int pageSize) {
        //扩大检索范围为最后一天24:00截至
        if(endTime != null && !endTime.equals("")) {
            endTime += " 23:59:59";
        }
        //启用PageHelper
        PageHelper.startPage(pageCode,pageSize);
        Page<Order> orderList  = orderHandlerMapper.findOrderByCondition(startTime, endTime, transportType);
        return new PageBean(orderList.getTotal(),orderList.getResult());
    }

    //订单手动打包生成任务单
    @Override
    public Integer buildTask(String[] orderIds) {
        //是否冷藏
        String taskType = "0";//默认冷藏
        int TypeChangeTime = 0;// transportTypeFlag的变化次数

        String orderIdsInTask = "";//订单id
        int totalQuantity = 0;//总件数
        BigDecimal totalVolume = new BigDecimal("0.00");//总体积
        BigDecimal totalweight = new BigDecimal("0.00");//总重量
        BigDecimal proposedPrice = new BigDecimal("0.00");//合计金额
        String pickArea = null;//提货地区
        String deliverArea = null;//送货地区
        String serviceTime = null;//送达时间
        String bidStatus = "3";//竞拍状态(未发布)
        String bidTaskId = CreateUUID.getUUID32();//生成订单id
        String bidTaskNum = null;
        try {
            bidTaskNum = CreateNumber.GetNumber("RW");//生成订单号
        } catch (Exception e) {
            e.printStackTrace();
        }
        Order order = null;

        for (int i = 0;i < orderIds.length;i++){
            order = orderHandlerMapper.findOrdersById(orderIds[i]);
            /**
             * 确定任务类型（冷藏或非冷藏）
             * 如果order对象中的TransportType与transportTypeFlag不同
             * TypeChangeTime+1
             * 如果TypeChangeTime的值大于1
             * 则认为所选订单中有不同的TransportType
             */
            if(!order.getTransportType().equals(taskType)){
                taskType = order.getTransportType();
                TypeChangeTime++;
                if(TypeChangeTime > 1){
                    return -1;//运输类型不同
                }
            }
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
            //确定提货、收货地点和收货时间
            if(i==0){
                pickArea = order.getStartArea();
                deliverArea = order.getEndArea();
                serviceTime = order.getReceivingTime();
            }
            //取订单中最小的收货时间
            if(serviceTime.compareTo(order.getReceivingTime())>0){
                serviceTime = order.getReceivingTime();
            }
            //将订单id拼接，以逗号隔开
            orderIdsInTask += order.getOrderId()+",";
        }

        orderIdsInTask = orderIdsInTask.substring(0,orderIdsInTask.lastIndexOf(","));//去掉订单字段最后一个逗号
//        serviceTime = serviceTime.split(" ")[0];//只保留年月日
        AuctionTask auctionTask = new AuctionTask();
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
        auctionTask.setBidTaskId(bidTaskId);
        auctionTask.setBidTaskNum(bidTaskNum);

        //isSuccess == 1表示插入任务单成功
        Integer isSuccess = orderHandlerMapper.buildTask(auctionTask);
        //将订单表的状态设为已合单状态
        if(isSuccess == 1){
            String orderIdsForModity[] = orderIdsInTask.split(",");
            int i = 0;
            while (isSuccess ==1 && i < orderIdsForModity.length){
                isSuccess = orderHandlerMapper.modifyStatus(orderIdsForModity[i]);
                i++;
            }
            return  isSuccess;
        }
        return 0;
    }
}
