package by.zverugo.samsolutions.instagram.controller;

import by.zverugo.samsolutions.instagram.dto.ProfileDTO;
import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.hash.PasswordHashEncoder;
import by.zverugo.samsolutions.instagram.service.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import by.zverugo.samsolutions.instagram.service.AuthorizationService;
import by.zverugo.samsolutions.instagram.service.user.UserService;
import by.zverugo.samsolutions.instagram.util.enums.UserRoleEnum;
import by.zverugo.samsolutions.instagram.validator.SignUpValidator;

import java.util.Map;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Autowired
    private SignUpValidator signUpValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private PasswordHashEncoder passwordEncoder;

    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping(method = RequestMethod.GET)
    public String regPage(Map<String, Object> model) {
        model.put("userForm", new UserDTO());

        return "common/signup";
    }

    @RequestMapping(value = "/registerUser", method = {RequestMethod.POST, RequestMethod.GET})
    public String registerUser(@ModelAttribute("userForm") UserDTO userDTO, BindingResult result) {
        if (userDTO.getLogin() == null) {

            return "common/signup";
        }
        signUpValidator.validate(userDTO, result);
        if (result.hasErrors()) {

            return "common/signup";
        }
        userDTO.setRole(UserRoleEnum.USER);
        String password = userDTO.getPassword();
        userDTO.setPassword(passwordEncoder.encode(password));
        userDTO.setEnable(true);
        long userId = userService.saveUser(userDTO);
        buildProfile(userId);
        authorizationService.login(userDTO.getLogin(), password);

        return "redirect:/users/user";
    }

    private void buildProfile(long userId) {
        ProfileDTO profile = new ProfileDTO();
        profile.setUser(userId);
        profile.setFirstName("");
        profile.setSurname("");
        profile.setSecondName("");
        profile.setCity("");
        profileService.saveProfile(profile);
    }
}
