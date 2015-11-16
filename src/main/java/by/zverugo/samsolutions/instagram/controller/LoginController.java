package by.zverugo.samsolutions.instagram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(@RequestParam(required = false) String fail, Model model, HttpSession httpSession) {
        //TODO enable/disable
        String message;
        if (fail != null) {
            message = "Invalid login or password";
            model.addAttribute("message", message);
        }
        return "common/login";
    }

}
