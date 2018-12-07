package cn.jzt56.singleticketsystem.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ Author     ：孙帅
 * @ Date       ：Created in 17:22 2018/12/7
 * @ Description：生成单号
 */
public class CreateNumber {

    public static String GetNumber(String Type) throws Exception {
        //RW代表任务单，DD代表订单
        if(Type.equals("RW") || Type.equals("DD")){
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
            String number = sdf.format(date).replace("-","").substring(2);
            return Type+number;
        }else{
            //参数值传递异常
            throw new Exception("单据类型错误，只能使用'RW'或'DD'作为参数传递");
        }
    }
}
