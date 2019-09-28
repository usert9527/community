package cn.user9527.mycommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @date 2019/9/28 - 22:16
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){return "index";}
}
