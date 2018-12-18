package cn.jzt56.singleticketsystem.service.impl;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.CarrierInfo;
import cn.jzt56.singleticketsystem.mapper.CarrierInfoMapper;
import cn.jzt56.singleticketsystem.service.CarrierInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 9:50 2018/12/14
 * @ Description：n内部承运商业务实现类
 */
@Service
public class CarrierInfoServiceImpl implements CarrierInfoService {

    /**
     * @method
     * @description :可指派承运商的信息
     * @author:lzy
     */
    @Autowired
    private CarrierInfoMapper carrierInfoMapper;
    @Override
    public List<CarrierInfo> findManyCarrierInfo(AuctionTask auctionTask) {
        return this.carrierInfoMapper.findManyCarrierInfo(auctionTask);
    }
}
