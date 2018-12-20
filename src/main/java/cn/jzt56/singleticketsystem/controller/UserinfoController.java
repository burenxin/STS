package cn.jzt56.singleticketsystem.controller;

import cn.jzt56.singleticketsystem.entity.UserInfo;
import cn.jzt56.singleticketsystem.service.impl.UserInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IDEA
 *
 * @author: CHENG QI
 * @Date: 2018/12/4
 * Time: 16:21
 */
@RestController
@RequestMapping("/userinfo")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class UserinfoController {

    @Autowired
    private UserInfoServiceImpl userinfoService;

    //   http://localhost:8080/STS/userinfo/insert
    @RequestMapping("insert")
    public String insertUserinfo(UserInfo userinfo){
        log.info("添加用户controller");
        userinfo.setUserId("1001");
        userinfo.setUserName("李四");
        userinfo.setUserPassword("lisi");
        userinfo.setPhoneNum("13878976078");
        userinfo.setType("0");
        userinfo.setStatus("0");
        userinfoService.insertUserinfo(userinfo);
        return "添加成功。。。";
    }
    //   http://localhost:8080/STS/userinfo/update
    @RequestMapping("update")
    public String updateUserinfo(UserInfo userinfo){
        String id = "1001";

        userinfo = userinfoService.getUserinfoByUserId(id);

        userinfo.setUserName(userinfo.getUserName()+1);
        userinfoService.updateUserinfoByUserId(userinfo);
        return "修改成功。。。";
    }

    //   http://localhost:8080/STS/userinfo/getUserinfoById
    @RequestMapping("getUserinfoById")
    public UserInfo getUserinfoById(String UserId){
        UserId = "1001";
        return  userinfoService.getUserinfoByUserId(UserId);
    }

    //   http://localhost:8080/STS/userinfo/removeUserinfoByUserId
    @RequestMapping(value = "/removeUserinfoByUserId")
    public String removeUserinfoByUserId(String UserId){
        UserId = "1001";
        userinfoService.removeUserinfoByUserId(UserId);
        return "删除成功 UserId = "+UserId;
    }

    //    http://10.2.65.67:8080/STS/userinfo/login
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public UserInfo login(@RequestBody UserInfo Userinfo, HttpServletRequest request) throws Exception {
        log.info(Userinfo.toString());
        UserInfo userinfo = userinfoService.getUserinfoByUserName(Userinfo.getUserName());
        if(userinfo == null){
            log.info("用户不存在");
            return Userinfo;
        }else if(!userinfo.getUserPassword().equals(Userinfo.getUserPassword())){
            log.info("密码错误");
            return Userinfo;
        }
       log.info(userinfo.getType());
        if(userinfo.getType().equals("0")){
            log.info("当前委托方登录");
        }else if(userinfo.getType().equals("2")){
            log.info("当前外部承运商登录");
        }else if(userinfo.getType().equals("3")){
            log.info("当前调度员登录");
        }else {
            log.info("当前内部承运商登录");
        }
        request.getSession().setAttribute("UserInfo",userinfo);
        return userinfo;
    }

}
