package cn.jzt56.singleticketsystem.service;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.CarrierInfo;

import java.util.List;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 9:51 2018/12/14
 * @ Description：内部承运商业务接口
 */
public interface CarrierInfoService {
    /**
     * @method
     * @description ：可指派承运商时信息
     * @author:lzy
     */
    List<CarrierInfo> findManyCarrierInfo(AuctionTask auctionTask);



}
