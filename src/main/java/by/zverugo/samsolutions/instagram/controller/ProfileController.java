package by.zverugo.samsolutions.instagram.controller;

import by.zverugo.samsolutions.instagram.dto.ProfileDTO;
import by.zverugo.samsolutions.instagram.service.profile.ProfileService;
import by.zverugo.samsolutions.instagram.validator.UpdateProfileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
@RequestMapping(value = "/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UpdateProfileValidator updateProfileValidator;

    @RequestMapping(value = "/user={id}", method = RequestMethod.GET)
    public String showProfile(@PathVariable("id") long userId, Model model) {
        ProfileDTO profileDTO = profileService.getProfileByUserId(userId);
        ProfileDTO editedProfile = new ProfileDTO();
        editedProfile.setId(profileDTO.getId());
        editedProfile.setUser(profileDTO.getUser());
        editedProfile.setAvatar(profileDTO.getAvatar());
        model.addAttribute("profile", profileDTO);
        model.addAttribute("editedProfile", editedProfile);
        return "profile/profile";
    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public String updateProfile(@ModelAttribute("editedProfile") ProfileDTO profile) throws IOException {
        updateProfileValidator.checkCorrectData(profile);
        profileService.updateProfile(profile);
        return "redirect:/profile/user=" + profile.getUser();
    }

}
