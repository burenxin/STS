package cn.jzt56.singleticketsystem.service.impl;

import cn.jzt56.singleticketsystem.entity.Price;
import cn.jzt56.singleticketsystem.entity.PriceDictionary;
import cn.jzt56.singleticketsystem.mapper.PriceMapper;
import cn.jzt56.singleticketsystem.service.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created with IDEA
 *
 * @author: CHENG QI
 * @Date: 2018/12/12
 * Time: 14:25
 */
@Service
@Slf4j
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceMapper priceMapper;

    /**
     * 根据重量计算运费
     *
     * @param price
     * @return
     */
    @Override
    public BigDecimal getPriceByWeightOrVolume(Price price) throws Exception{
        //获取起始地
        String start = price.getStartArea();
        //获取目的地
        String end = price.getEndArea();
        //获取商品重量
        String weightStr = price.getWeight();
        BigDecimal weight = new BigDecimal(weightStr);//将字符串转换为BigDecimal型
        //获取商品体积
        String volumeStr = price.getVolume();
        BigDecimal volume = new BigDecimal(volumeStr);//将字符串转换为BigDecimal型

        BigDecimal weightPri = getPriceByWeight(start,end,weight);
        log.info("按照重量计算价格为："+weightPri);
        BigDecimal volumePri = getPriceByVolume(start,end,volume);
        log.info("按照体积计算价格为："+volumePri);

        if(weightPri.compareTo(volumePri) == - 1){  //weightPri<volumePri
            log.info("以体积收取费用");
            return volumePri;
        }else {
            log.info("以重量收取费用");
            return  weightPri;
        }

    }

    /**
     * 判断价格字典表中是否有该用户的协议规则
     *
     * @param userId
     * @return
     */
    @Override
    public int prejudgeUserId(String userId) {
        return priceMapper.prejudgeUserId(userId);
    }

    /**
     * 获取价格字典表中的协议规则
     *
     * @param userId
     * @return
     */
    @Override
    public PriceDictionary getPriceDictionaryByClientId(String userId) {
        return priceMapper.getPriceDictionaryByClientId(userId);
    }

    /**
     * 根据价格字典表协议规则计算价格(取价高者)
     *
     * @param priceDictionary
     * @return
     */
    @Override
    public BigDecimal getPriceByPriceDictionary(PriceDictionary priceDictionary,Price price) throws Exception{
        //获取起始地
        String start = price.getStartArea();

        //获取目的地
        String end = price.getEndArea();
        //获取商品数量
        BigDecimal count =  new BigDecimal(price.getGoodsCount());
        //获取商品重量
        BigDecimal weight = new BigDecimal(price.getWeight());
        //获取商品体积
        BigDecimal volume = new BigDecimal(price.getVolume());
        //获取市内价
        BigDecimal inner = new BigDecimal(priceDictionary.getInnerCity());  //35
        //获取省内价
        BigDecimal outer = new BigDecimal(priceDictionary.getOuterCity()); //45
        //获取省外价
        BigDecimal province = new BigDecimal(priceDictionary.getOuterProvince());//55
        //获取每件价格
        BigDecimal item = priceDictionary.getPerItemPrice();
        //获取每重量价格
        BigDecimal perWeight = priceDictionary.getPerWeightPrice();
        //获取每体积价格
        BigDecimal perVolume = priceDictionary.getPerVolumePrice();

        //计算 数量*每件价格
        BigDecimal  price1 = item.multiply(count).setScale(2, BigDecimal.ROUND_HALF_UP);
        //计算 重量*每重量价格
        BigDecimal  price2 = perWeight.multiply(weight).setScale(2,BigDecimal.ROUND_HALF_UP);
        //计算 体积*每体积价格
        BigDecimal  price3 = perVolume.multiply(volume).setScale(2,BigDecimal.ROUND_HALF_UP);
        //得到以上三个值的最大值
        BigDecimal price4 = findMax(price1,price2,price3);
        log.info("最大值："+price4);

        //截取字符串：省市
        start = start.substring(0, start.lastIndexOf("-"));
        log.info(start.toString());
        end = end.substring(0, end.lastIndexOf("-"));
        log.info(end);
        //判断是否在同一个市
        if(start.equals(end)){
            log.info("位于同一个市");
            //将三种计算方式得到的最大值与起步价比较，返回最大值;
            if(price4.compareTo(inner)>=1){
                return price4;
            }else {
                return inner;
            }

        }else {
            start = start.substring(0, start.indexOf("-"));
            log.info(start.toString());
            end = end.substring(0, end.indexOf("-"));
            log.info(end);
            if (start.equals(end)){
                log.info("位于同一个省");
                //将三种计算方式得到的最大值与起步价比较，返回最大值;
                if(price4.compareTo(outer)>=1){
                    return price4;
                }else {
                    return outer;
                }
            }else {
                log.info("不在同一个省份");
                //将三种计算方式得到的最大值与起步价比较，返回最大值;
                if(price4.compareTo(province)>=1){
                    return price4;
                }else {
                    return province;
                }
            }
        }
    }





    //比较3个BigDecimal的值的大小
    BigDecimal findMax(BigDecimal price1,BigDecimal price2,BigDecimal price3) throws Exception{
        log.info("price1: "+price1+" price2："+price2+" price3："+price3);
        if(price1.compareTo(price2)<= 0 ){   // price1 <= price2
            if (price2.compareTo(price3) >= 0){  // price2 >= price3
                return price2;   //返回最大值 price2
            }else {
                return price3;   //返回最大值 price3
            }
        }else {  // price1 > price2
            if(price1.compareTo(price3) >= 0){ // price1 >= price3
                return price1;  //返回最大值 price1
            }else {
                return price3;  //返回最大值 price3
            }
        }
    }


    /**
 * 根据重量计算运费
 * 物流收费规则：
 *   按重量计算：
 *    起始地-目的地 在同一个城市：50KG以内 50元，超过50KG超过部分 0.5元/KG
 *		     在同一个省份：50KG以内 80元，超过50KG超过部分 0.9元/KG
 *		     省外：50KG以内 80元，超过50KG超过部分 2元/KG
 */
    //方法：根据起始地目的地重量计算价格
    BigDecimal getPriceByWeight(String start,String end,BigDecimal weight) throws Exception{
        BigDecimal pri;

        //截取字符串：省市
        start = start.substring(0, start.lastIndexOf("-"));
        log.info(start.toString());
        end = end.substring(0, end.lastIndexOf("-"));
        log.info(end);
        //判断是否在同一个市
        BigDecimal a = new BigDecimal(50);
        BigDecimal b = new BigDecimal(0.5);
        if(start.equals(end)){
            log.info("位于同一个市");
            //判断重量区间
            if(weight.compareTo(a) == -1){    // weight < a
                //将价格设定为50元
                //price.setTransportPrices();
                pri = a;
            }else {
                pri = a.add(weight.subtract(a)).multiply(b);  // a + (weight-a) * b
            }
        }else {
            start = start.substring(0, start.indexOf("-"));
            log.info(start.toString());
            end = end.substring(0, end.indexOf("-"));
            log.info(end);

            BigDecimal c = new BigDecimal(80);
            BigDecimal d = new BigDecimal(0.9);
            if (start.equals(end)){
                log.info("位于同一个省");
                //判断重量区间
                if(weight.compareTo(a) == -1){   // weight < a
                    //将价格设定为80元
                    pri = c;
                }else {
                    pri = c.add((weight.subtract(a)).multiply(d));   // c + (weight - a) * d
                }
            }else {
                log.info("不在同一个省份");
                //判断重量区间
                BigDecimal e = new BigDecimal(100);
                BigDecimal f = new BigDecimal(2);
                if(weight.compareTo(a) == -1){    // weight < a
                    //将价格设定为100元
                    //price.setTransportPrices();
                    pri = e;
                }else {
                    pri = e.add((weight.subtract(a).multiply(f)));      ;  // e + (weight - a) * f
                }
            }
        }
        return pri;
    }

    /**
     * 根据体积计算运费
     * 物流收费规则：
     *   按体积计算：
     *    起始地-目的地 在同一个城市：起步价30，280元/立方
     *		     在同一个省份：起步价50元，300元/立方
     *		     省外：起步价60元，340元/立方
     */
    //方法：根据起始地目的地体积计算价格
    BigDecimal getPriceByVolume(String start,String end,BigDecimal volume) throws Exception{
        BigDecimal pri;
        //截取字符串：省市
        start = start.substring(0, start.lastIndexOf("-"));
        log.info(start.toString());
        end = end.substring(0, end.lastIndexOf("-"));
        log.info(end);
        //判断是否在同一个市
        if(start.equals(end)){
            log.info("位于同一个市");
            BigDecimal a = new BigDecimal(280);
            BigDecimal b = new BigDecimal(30);
            //判断重量区间
            if(volume.multiply(a).compareTo(b) == -1){ // volume * a < b
                //将价格设定为30元
                //price.setTransportPrices();
                pri = b;
            }else {
                pri = volume.multiply(a) ;   // volume * a
            }
        }else {
            start = start.substring(0, start.indexOf("-"));
            log.info(start.toString());
            end = end.substring(0, end.indexOf("-"));
            log.info(end);

            BigDecimal a = new BigDecimal(300);
            BigDecimal b = new BigDecimal(50);
            if (start.equals(end)){
                log.info("位于同一个省");
                //判断重量区间
                if(volume.multiply(a).compareTo(b) == -1){  // volume * a < b
                    //将价格设定为50元
                    //price.setTransportPrices();
                    pri = b;
                }else {
                    pri = volume.multiply(a);   // volume * a
                }
            }else {

                BigDecimal c = new BigDecimal(340);
                BigDecimal d = new BigDecimal(60);
                log.info("不在同一个省份");
                //判断重量区间
                if(volume.multiply(c).compareTo(d) == -1){  // volume * c < d
                    //将价格设定为 60元
                    pri = d;
                }else {
                    pri = volume.multiply(c);   // volume * c
                }
            }
        }
        return pri;
    }

}

