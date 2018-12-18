package cn.jzt56.singleticketsystem.service;

import cn.jzt56.singleticketsystem.entity.Price;
import cn.jzt56.singleticketsystem.entity.PriceDictionary;

import java.math.BigDecimal;

/**
 * Created with IDEA
 *
 * @author: CHENG QI
 * @Date: 2018/12/12
 * Time: 14:00
 */
public interface PriceService{

    /**
     * 根据重量/体积计算运费
     * @param price
     * @return
     */
    BigDecimal getPriceByWeightOrVolume(Price price) throws Exception;

    /**
     * 判断价格字典表中是否有该用户的协议规则
     * @param userId
     * @return
     */
    int prejudgeUserId(String userId);

    /**
     * 获取价格字典表中的协议规则
     * @param userId
     * @return
     */
    PriceDictionary getPriceDictionaryByClientId(String userId);

    /**
     * 根据价格字典表协议规则计算价格
     * @param priceDictionary
     * @return
     */
    BigDecimal getPriceByPriceDictionary(PriceDictionary priceDictionary,Price price) throws Exception;

}
