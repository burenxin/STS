package cn.jzt56.singleticketsystem.swaggercontroller;

import cn.jzt56.singleticketsystem.entity.Price;
import cn.jzt56.singleticketsystem.service.impl.PriceServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created with IDEA
 *
 * @author: CHENG QI
 * @Date: 2018/12/13
 * Time: 8:37
 */
@RestController
@RequestMapping("api")
@Api("STS与Price相关的api")
@Slf4j
public class SwaggerPriceController {

    @Autowired
    private PriceServiceImpl priceService;

    @ApiOperation(value = "计算费用", notes = "根据收费规则计算费用")
    @ApiImplicitParam(name = "price", value = "价格", paramType = "body", required = true, dataType = "Price")
    @RequestMapping(value = "getPrice", method = RequestMethod.POST)
    public String getPrice(@RequestBody Price price) throws  Exception{
        String mess = "";
        String start = price.getStartArea();
        String end = price.getEndArea();
        String userId = price.getUserId();
        String volume = price.getVolume();
        String weight = price.getWeight();
        String goodsCount = price.getGoodsCount();

        //设置正则表达式的校验规则：判断一个字符串是否为数字(整数与小数)
        Pattern pattern = Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$");
        if(start == null || start.length() <= 0){
            mess = "起始地为空";
        }else if (end == null || end.length() <= 0){
            mess = "目的地为空";
        }else if(!( goodsCount != null  && goodsCount != "" && pattern.matcher(goodsCount).matches() )){
            mess = "件数存在非法数字或未填写";
        }else if(!( weight != null && weight != "" && pattern.matcher(weight).matches() )){
            mess = "重量存在非法数字或未填写";
        }else if (!( volume != null && volume != "" && pattern.matcher(volume).matches() )){
            mess = "体积存在非法数字或未填写";
        }else {
            if (userId != null && userId != "" && priceService.prejudgeUserId(userId) > 0) {

                log.info("价格字典表存在该用户的协议规则");
                log.info(priceService.getPriceDictionaryByClientId(userId).toString());
                mess = priceService.getPriceByPriceDictionary(priceService.getPriceDictionaryByClientId(userId), price) + "";
            } else {
                log.info("普通收费规则");
                mess = priceService.getPriceByWeightOrVolume(price) + "";
            }
        }
        return ("price:"+mess);
    }

}









//{
//"startArea":"湖南-益阳-赫山区",
//"endArea":"湖南-长沙-雨花区",
//"weight":"1",
//"volume":"0.01",
//"goodsCount":"2"
//}


