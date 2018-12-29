package cn.jzt56.singleticketsystem.tools;

import cn.jzt56.singleticketsystem.service.AuctionTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 19:23 2018/12/12
 * @ Description：定时器
 */
@Component
@Slf4j
public class Timer {

  //  public static Boolean flage=false;
    @Autowired
    private AuctionTaskService auctionTaskService;
    /**
     * @method
     * @description :定时器,报价任务截单两小时执行一次
     * @author:lzy
     */
    // @Scheduled(cron = "0 * */2 * * ?")
    @Scheduled (fixedRate = 1000)
    public void auctionTaskClose(){

        this.auctionTaskService.auctionTaskClose();
        log.info("输出123");


    }
//
//    /**
//     * @method
//     * @description :启动执行报价截止
//     * @author:lzy
//     */
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//
//        this.auctionTaskService.auctionTaskClose(flage);
//        flage=true;
//        BiddingDetail biddingDetail=new BiddingDetail();
//
//        log.info((biddingDetail.getQuotedPrice()==null)+"");
//        log.info("输出00000");
//    }


}
