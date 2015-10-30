package by.zverugo.samsolutions.instagram.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final Logger logger = Logger.getLogger(getClass());

    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(@RequestParam(required = false) String fail, Model model) {
        String message;
        if (fail != null) {
            message = "Invalid login or password";
            model.addAttribute("message", message);
        }
        return "common/login";
    }


    @RequestMapping("/403page")
    public String page403() {
        logger.warn("ACCESS DENIED");
        return "error/403";
    }


}
