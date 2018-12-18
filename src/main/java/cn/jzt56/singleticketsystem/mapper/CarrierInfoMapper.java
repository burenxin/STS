package cn.jzt56.singleticketsystem.mapper;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.entity.CarrierInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 9:34 2018/12/14
 * @ Description：内部承运商信息
 */
@Mapper
public interface CarrierInfoMapper {

    /**
     * @method
     * @description ：指派承运商时信息
     * @author:lzy
     */
    List<CarrierInfo> findManyCarrierInfo(AuctionTask auctionTask);

    /**
     * @method
     * @description ：查询默认承运商最低价
     * @author:lzy
     */
    CarrierInfo findMinCarrierInfo(AuctionTask auctionTask);

}
