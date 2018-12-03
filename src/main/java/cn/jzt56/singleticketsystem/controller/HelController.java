package cn.jzt56.singleticketsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 *
 * @author: CHENG QI
 * @Date: 2018/12/3
 * Time: 15:14
 */
@RestController
@Slf4j
public class HelController {

    @RequestMapping("hel")
    public String hel(){
        log.info("hel");
        log.error("error");
        return "Hello";
    }
}
