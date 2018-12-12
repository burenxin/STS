package cn.jzt56.singleticketsystem.swaggercontroller;

import cn.jzt56.singleticketsystem.entity.UserInfo;
import cn.jzt56.singleticketsystem.service.impl.UserInfoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * Created with IDEA
 *
 * @author: CHENG QI
 * @Date: 2018/12/11
 * Time: 9:23
 * 访问：http://localhost:8081/STS/swagger-ui.html#/    测试
 */
@RestController
@RequestMapping("api")
@Api("swaggerDemoController相关的api")
@Slf4j
public class SwaggerUserInfoController {

    @Autowired
    private UserInfoServiceImpl userInfoService;

    @ApiOperation(value = "根据id查询用户信息",notes = "查询数据库中某个用户信息")
    @ApiImplicitParam(name = "UserId",value = "用户id",paramType = "path",required = true,dataType = "String")
    @RequestMapping(value = "getUserinfoById/{UserId}",method = RequestMethod.GET)
    public UserInfo getUserinfoById(@PathVariable String UserId){
        log.info("根据id查询用户信息");
        return  userInfoService.getUserinfoByUserId(UserId);
    }

    @ApiOperation(value = "创建UserInfo对象",notes = "在数据库中添加用户信息")
    @ApiImplicitParam(name = "userinfo",value = "用户",paramType = "body",required = true,dataType = "UserInfo")
    @RequestMapping(value = "insert/{userinfo}",method = RequestMethod.POST)
    public String insertUserinfo(@RequestBody UserInfo userinfo){
        log.info("创建新用户");
         userInfoService.insertUserinfo(userinfo);
        return "Success";
    }

    @ApiOperation(value = "用户登录",notes = "根据用户名查找用户是否存在")
    @ApiImplicitParam(name = "Userinfo",value = "用户",paramType = "body",required = true,dataType = "UserInfo")
    @RequestMapping(value = "login/{Userinfo}",method = RequestMethod.POST)
    public UserInfo login(@RequestBody UserInfo Userinfo, HttpServletRequest request){
        log.info(Userinfo.toString());
        UserInfo userinfo = userInfoService.getUserinfoByUserName(Userinfo.getUserName());
        if(Userinfo == null){
            log.info("用户不存在");
            return Userinfo;
        }else if(!userinfo.getUserPassword().equals(Userinfo.getUserPassword())){
            log.info("密码错误");
            return Userinfo;
        }
        request.getSession().setAttribute("UserInfo",userinfo);
        if(userinfo.getType() == "0"){
        }
        return userinfo;
    }

    @ApiOperation(value = "修改用户信息",notes = "根据用户id修改用户信息")
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String updateUserinfo(UserInfo userinfo){

     userInfoService.updateUserinfoByUserId(userinfo);
        return "修改成功。。。";
    }

    @ApiOperation(value = "删除用户",notes = "根据用户id删除用户")
    @ApiImplicitParam(name = "UserId",value = "用户id",paramType = "path",required = true,dataType = "String")
    @RequestMapping(value = "removeUserinfoByUserId/{UserId}",method = RequestMethod.GET)
    public String removeUserinfoByUserId(@PathVariable String UserId){
        log.info(UserId);
       int i = userInfoService.removeUserinfoByUserId(UserId);
       if(i==0){
           return "删除失败 UserId = "+UserId;
       }else {
           return "删除成功 UserId = " + UserId;
       }
    }


}
