package cn.jzt56.singleticketsystem.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IDEA
 *
 * @author: CHENG QI
 * @Date: 2018/12/13
 * Time: 11:11
 */
@Data
public class PriceDictionary {

    /**  价格id. */
    private String priceId ;

    /**  委托方id. */
    private String clientId ;

    /**  每件价格. */
    private BigDecimal perItemPrice ;

    /**  每重量价格. */
    private BigDecimal perWeightPrice ;

    /**  每体积价格. */
    private BigDecimal perVolumePrice ;

    /**  市内. */
    private String innerCity ;

    /**  市外. */
    private String outerCity ;

    /**  省外. */
    private String outerProvince ;

}
