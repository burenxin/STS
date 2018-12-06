package cn.jzt56.singleticketsystem.service;

import cn.jzt56.singleticketsystem.entity.AuctionTask;

import java.util.List;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 11:22 2018/12/5
 * @ Description：竞价业务业务接口
 */
public interface AuctionTaskService {

    /**
     * @method
     * @description ：查询所有可竞价任务
     * @author:lzy
     */
    List<AuctionTask> findAllCurrentTask(String userId);
}
