package by.zverugo.samsolutions.instagram.controller;

import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.service.user.UserService;
import by.zverugo.samsolutions.instagram.util.enums.UserRoleEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class AccessController {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/roleChecker",method = RequestMethod.GET)
     public String checkRolePage(HttpSession httpSession) {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         GrantedAuthority roleAuthority = (GrantedAuthority) authentication.getAuthorities().toArray()[0];

         if (UserRoleEnum.ADMIN.getRole().equals(roleAuthority.getAuthority())) {
             return "redirect:/admin";
         } else if (UserRoleEnum.USER.getRole().equals(roleAuthority.getAuthority())) {
             UserDTO userDTO = userService.getUserByLogin(authentication.getName());
             httpSession.setAttribute("authorizedUser", userDTO.getId());
             return "redirect:/users/user/" + userDTO.getId();
         }

        return "error/403";
     }

    @RequestMapping("/403page")
    public String page403() {
        LOGGER.warn("ACCESS DENIED");
        return "error/403";
    }
}
