package cn.jzt56.singleticketsystem.service;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.Order;
import cn.jzt56.singleticketsystem.tools.PageBean;

import java.util.List;

/**
 * @ Author     ：孙帅
 * @ Date       ：Created in 17:22 2018/12/5
 * @ Description：订单处理接口
 */
public interface OrderHandlerService {
    //按时间段或运输类型查询订单
    public PageBean findOrderByCondition(String startTime, String endTime, String transprotTypeint,int pageCode, int pageSize);

    //订单打包生成任务单
    public Integer buildTask(String[] orderIds);
}
