package cn.jzt56.singleticketsystem.mapper;

import cn.jzt56.singleticketsystem.entity.AuctionTask;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ Author     ：lzy
 * @ Date       ：Created in 9:50 2018/12/5
 * @ Description：任务单操作Mapper
 */
@Mapper
public interface AuctionTaskMapper {

    /**
     * @method
     * @description ：查询所有可竞价任务
     * @author:lzy
     */
    List<AuctionTask> findAllCurrentTask(String userId);
}
