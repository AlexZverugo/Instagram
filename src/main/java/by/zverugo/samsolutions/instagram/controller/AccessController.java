package by.zverugo.samsolutions.instagram.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessController {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @RequestMapping("/403page")
    public String page403() {
        LOGGER.warn("ACCESS DENIED");
        return "error/403";
    }
}
