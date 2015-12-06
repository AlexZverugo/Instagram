package by.zverugo.samsolutions.instagram.controller;

import by.zverugo.samsolutions.instagram.dto.ProfileDTO;
import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.service.AuthorizationService;
import by.zverugo.samsolutions.instagram.service.profile.ProfileService;
import by.zverugo.samsolutions.instagram.validator.ProfileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.IOException;

@Controller
@SessionAttributes({"authorizedUser"})
@RequestMapping(value = "/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileValidator profileValidator;

    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping(value = "/user={id}", method = RequestMethod.GET)
    public String showProfile(@PathVariable("id") long currentUserId, Model model) {
        UserDTO authUser = authorizationService.getAuthUser();
        ProfileDTO profileDTO = profileService.getProfileByUserId(currentUserId);

        if (currentUserId == authUser.getUserId()) {
            model.addAttribute("isEditable", true);
        } else {
            model.addAttribute("isEditable", false);
        }
        model.addAttribute("profile", profileDTO);

        return "profile/profile";
    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public String updateProfile(@ModelAttribute("editedProfile") ProfileDTO profile, BindingResult result, Model model) throws IOException {
        profileValidator.validate(profile, result);
        if (result.hasErrors()) {
            model.addAttribute("isValid", false);
        } else {
            model.addAttribute("isValid", true);
        }
        profileService.updateProfile(profile);
        return "redirect:/profile/user=" + profile.getUser();
    }

}
