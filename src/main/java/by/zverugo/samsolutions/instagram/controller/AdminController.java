package by.zverugo.samsolutions.instagram.controller;

import by.zverugo.samsolutions.instagram.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import by.zverugo.samsolutions.instagram.service.user.UserService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String adminPage(Map<String, Object> model) {
        List<UserDTO> userDTOList = userService.getListOfUsers();
        model.put("users", userDTOList);

        return "user/admin";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String deleteUser(@RequestParam(required = true) long id) {
        userService.deleteUser(id);

        return "redirect:../admin";
    }
}
