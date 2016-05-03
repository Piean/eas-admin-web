package com.yds.eas.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yds on 2016-5-3.
 */

@Controller
@RequestMapping("/user")
public class UserInfoController {

    @RequestMapping("login")
    public String login(Model model) {
        return "login";
    }
}
