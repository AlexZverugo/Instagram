package by.zverugo.samsolutions.instagram.controller;

import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.service.user.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String checkUser(HttpSession httpSession) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = userService.getUserByLogin(authentication.getName());
        httpSession.setAttribute("authorizedUser", userDTO);
        return "redirect:user/" + userDTO.getId();
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public String userPage(@PathVariable("id") long id, Model model) {
        UserDTO user = userService.getUserById(id);

        if (user == null || user.getRole().getRole().equals("ADMIN")) {
            return "redirect:/users/user";
        }

        model.addAttribute("id", id);
        return "user/user";
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public String addPost(@RequestParam(required = true) String id, HttpSession httpSession) {
        httpSession.setAttribute("postOwnerId", id);
        return "redirect:/post";
    }

}
