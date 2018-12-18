package cn.jzt56.singleticketsystem.tools;
/**
 * @ Author     ：文梁伟
 * @ Date       ：Created in 14:19 2018/12/17
 * @ Description：在指定时间上加天数
 */
import java.util.*;
public class AddDate {
    public static Date addDate(Date d, long day) throws Exception {

        long time = d.getTime();
        day = day * 24 * 60 * 60 * 1000;
        time += day;
        return new Date(time);

    }
}
