package cn.jzt56.singleticketsystem.controller;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import cn.jzt56.singleticketsystem.service.AuctionTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 11:31 2018/12/5
 * @ Description：竞价控制层
 */

@RestController
@RequestMapping(value = "/auctionTask")
public class AuctionTaskController {

    @Autowired
    private AuctionTaskService auctionTaskService;
    /**
     * @method
     * @description:获取所有可以竞价而未竞价任务
     * @author:lzy
     */

    @RequestMapping(value = "/currentTask")
    public List<AuctionTask> findAllCurrentTask(HttpServletRequest request){

        //String userId=(String)request.getSession().getAttribute("userId");

        return this.auctionTaskService.findAllCurrentTask("ui001");

    }
}
