package cn.jzt56.singleticketsystem.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IDEA
 *
 * @author: CHENG QI
 * @Date: 2018/12/12
 * Time: 13:39
 */
@Data
public class Price {

    /**  用户ID. */
    private String userId;

    /**  起始地址. */
    private String startArea;

    /**  目的地. */
    private String endArea;

    /**  商品类型. */
    private String goodsType;

    /**  总件数. */
    private String goodsCount;

    /**  体积. */
    private String volume;

    /**  重量. */
    private String weight;

    /**  运输费用. */
    private BigDecimal transportPrices;

    private String mess;
}
