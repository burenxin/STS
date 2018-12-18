package cn.jzt56.singleticketsystem.controller;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.CarrierInfo;
import cn.jzt56.singleticketsystem.service.CarrierInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 9:54 2018/12/14
 * @ Description：承运商控制类
 */
@RestController
@RequestMapping(value = "/carrierInfo")
public class CarrierInfoController {

    @Autowired
    private CarrierInfoService carrierInfoService;


    /**
     * @method
     * @description 获取可指派运营商信息
     * @author:lzy
     */
    @RequestMapping(value = "/findManyCarrierInfo")
    public List<CarrierInfo> findManyCarrierInfo(AuctionTask auctionTask){
        return  this.carrierInfoService.findManyCarrierInfo(auctionTask);
    }
}
