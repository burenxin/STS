package cn.jzt56.singleticketsystem.mapper;

import cn.jzt56.singleticketsystem.entity.PriceDictionary;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IDEA
 *
 * @author: CHENG QI
 * @Date: 2018/12/13
 * Time: 10:49
 */
@Mapper
public interface PriceMapper {

    int prejudgeUserId(String userId);

    PriceDictionary getPriceDictionaryByClientId(String userId);


}
