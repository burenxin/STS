package cn.jzt56.singleticketsystem.controller;

import cn.jzt56.singleticketsystem.entity.Price;
import cn.jzt56.singleticketsystem.service.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

/**
 * Created with IDEA
 *
 * @author: CHENG QI
 * @Date: 2018/12/12
 * Time: 17:17
 */
@RestController
@RequestMapping(value = "/price")
@Slf4j
public class PriceController {
    @Autowired
    private PriceService priceService;

    //  http://10.2.65.67:8080/STS/price/getPrice
    @RequestMapping(value = "/getPrice")
    @ResponseBody
    public Price getPrice( Price price) throws Exception{
        String mess = "";
        String start = price.getStartArea();
        String end = price.getEndArea();
        String userId = price.getUserId();
        String volume = price.getVolume();
        String weight = price.getWeight();
        String goodsCount = price.getGoodsCount();

        log.info("userId: "+userId+" volume: "+volume+" weight: "+weight+" goodCount: "+goodsCount);

        Pattern pattern = Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$");
//        if( !( pattern.matcher(price.getGoodsCount()).matches() && pattern.matcher(price.getWeight()).matches() && pattern.matcher(price.getVolume()).matches()) ){
//            mess = "重量、体积或件数存在非法数字";
//        } if(s == null || s.length() <= 0);
        if(start == null || start.length() <= 0){
            price.setMess("起始地为空");
        }else if (end == null || end.length() <= 0){
            price.setMess("目的地为空");
        }else if(!( goodsCount != null  && goodsCount != "" && pattern.matcher(goodsCount).matches() )){
            price.setMess("件数存在非法数字或未填写");
        }else if(!( weight != null && weight != "" && pattern.matcher(weight).matches() )){
            price.setMess("重量存在非法数字或未填写");
        }else if (!( volume != null && volume != "" && pattern.matcher(volume).matches() )){
            price.setMess("体积存在非法数字或未填写");
        }else {
            if (userId != null && userId != "" && priceService.prejudgeUserId(userId) > 0) {

                log.info("价格字典表存在该用户的协议规则");
                log.info(priceService.getPriceDictionaryByClientId(userId).toString());
                 price.setTransportPrices(priceService.getPriceByPriceDictionary(priceService.getPriceDictionaryByClientId(userId), price) );
            } else {
                log.info("普通收费规则");
                 price.setTransportPrices(priceService.getPriceByWeightOrVolume(price) );
            }
        }


//        if (userId != null && userId != "" && priceService.prejudgeUserId(userId) > 0) {
//            log.info("价格字典表存在该用户的协议规则。");
//            log.info(priceService.getPriceDictionaryByClientId(userId).toString());
//            mess = priceService.getPriceByPriceDictionary(priceService.getPriceDictionaryByClientId(userId),price)+"";
//        }else {
//            mess = priceService.getPriceByWeightOrVolume(price) + "";
//        }

        return price;
    }
}
