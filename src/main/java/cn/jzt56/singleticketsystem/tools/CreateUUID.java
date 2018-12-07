package cn.jzt56.singleticketsystem.tools;

import java.util.UUID;

/**
 * @ Author     ：孙帅
 * @ Date       ：Created in 17:22 2018/12/7
 * @ Description：生成uuid
 */
public class CreateUUID {
    public static String getUUID32(){
        //返回32位小写uuid
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
