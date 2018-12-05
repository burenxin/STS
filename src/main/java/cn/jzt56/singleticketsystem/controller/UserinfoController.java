package cn.jzt56.singleticketsystem.controller;

import cn.jzt56.singleticketsystem.entity.Userinfo;
import cn.jzt56.singleticketsystem.service.impl.UserinfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
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
    private UserinfoServiceImpl userinfoService;

    //   http://localhost:8080/STS/userinfo/insert
    @RequestMapping("insert")
    public String insertUserinfo(Userinfo userinfo){
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
    public String updateUserinfo(Userinfo userinfo){
        String id = "1001";

        userinfo = userinfoService.getUserinfoByUserId(id);

        userinfo.setUserName(userinfo.getUserName()+1);
        userinfoService.updateUserinfoByUserId(userinfo);
        return "修改成功。。。";
    }

    //   http://localhost:8080/STS/userinfo/getUserinfoById
    @RequestMapping("getUserinfoById")
    public Userinfo getUserinfoById(String UserId){
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

    //    http://10.2.65.53:8080/STS/userinfo/login?UserName=李四&UserPassword=123
    //    ,produces={"application/json;charset=UTF-8"}

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestBody Userinfo Userinfo, HttpServletRequest request){
        log.info(Userinfo.toString());
        Userinfo userinfo = userinfoService.getUserinfoByUserName(Userinfo.getUserName());
        if(Userinfo == null){
            log.info("用户不存在");
            return "用户名不存在";
        }else if(!userinfo.getUserPassword().equals(Userinfo.getUserPassword())){
            log.info("密码错误");
            return "密码错误";
        }
        request.getSession().setAttribute("Userinfo",userinfo);
        return userinfo.toString();

    }

}
