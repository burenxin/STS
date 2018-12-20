package cn.jzt56.singleticketsystem.service.impl;

import cn.jzt56.singleticketsystem.entity.Order;
import cn.jzt56.singleticketsystem.tools.AddDate;
import cn.jzt56.singleticketsystem.tools.CreateNumber;
import cn.jzt56.singleticketsystem.tools.CreateUUID;
import cn.jzt56.singleticketsystem.tools.PageBean;
import cn.jzt56.singleticketsystem.mapper.OrderMapper;
import cn.jzt56.singleticketsystem.service.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> findAll() {
        return orderMapper.findAll();
    }

    @Override
    public List<Order> findById(String id) {
        return orderMapper.findById(id);
    }

    public PageBean findByUserId(Order order, int pageCode, int pageSize) {
        PageHelper.startPage(pageCode, pageSize);
        Page<Order> page = orderMapper.findByUserId(order);
        return new PageBean(page.getTotal(), page.getResult());
    }
    @Override
    public void create(Order order) {
        //添加switch判断，文梁伟
        try {
            order.setOrderId(CreateUUID.getUUID32());
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            DateFormat sdg = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            order.setCreatedTime(sdg.format(date));
            order.setUpdatedTime(sdg.format(date));
            order.setStatus("0");//状态刚生成
            order.setOrderNum(CreateNumber.GetNumber("DD"));//修改委托单号
            int st=Integer.parseInt(order.getReceivingTime());//获取预计提货时间的前台值
            String strDate=order.getDeliveryTime();
            date=sdg.parse(strDate);//将提货时间转为字符串
            AddDate ad=new AddDate();
            try {
                switch(st){
                    case 1:
                        date=ad.addDate(date,3);//将提货时间加三天
                        break;
                    case 2:
                        date=ad.addDate(date,5);
                        break;
                    case 3:
                        date=ad.addDate(date,7);
                        break;
                    default:break;
                }

                order.setReceivingTime(sdg.format(date));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        orderMapper.create(order);
    }

    @Override
    public void update(Order order) {
        try{
        Date date = new Date();
        SimpleDateFormat sdg = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        order.setUpdatedTime(sdg.format(date));
            log.info(order.getGoodsCount());
            log.info(order.getDeliveryTime());
            String strDate=order.getDeliveryTime();
            int st=Integer.parseInt(order.getReceivingTime());//获取预计提货时间的前台值
            date=sdg.parse(strDate);//将提货时间转为字符串
            AddDate ad=new AddDate();
        try {
            switch(st){
                case 0:
                    date=ad.addDate(date,3);//将提货时间加三天
                    break;
                case 1:
                    date=ad.addDate(date,5);
                    break;
                case 2:
                    date=ad.addDate(date,7);
                    break;
                default:break;
            }

            order.setReceivingTime(sdg.format(date));
            orderMapper.update(order);
        } catch (Exception e) {
            e.printStackTrace();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    @Override
    public void delete(String... ids) {

        for (String id : ids) {
          //log.info(id);
            orderMapper.delete(id);
        }
    }

    public void deleteOrder(Order order) {
        //log.info(order.getOrderId());
        orderMapper.deleteOrder(order);
    }
    /**
     * 分页查询-条件查询方法
     *
     * @param order    查询条件
     * @param pageCode 当前页
     * @param pageSize 每页的记录数
     * @return
     */
    public PageBean findByPage(Order order, int pageCode, int pageSize) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode, pageSize);

        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        Page<Order> page = orderMapper.findByPage(order);

        return new PageBean(page.getTotal(), page.getResult());
    }
}
