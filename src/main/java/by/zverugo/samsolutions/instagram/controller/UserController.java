package by.zverugo.samsolutions.instagram.controller;

import by.zverugo.samsolutions.instagram.dto.CommentDTO;
import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.dto.ProfileDTO;
import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.jsonview.Views;
import by.zverugo.samsolutions.instagram.service.AuthorizationService;
import by.zverugo.samsolutions.instagram.service.post.PostService;
import by.zverugo.samsolutions.instagram.service.profile.ProfileService;
import by.zverugo.samsolutions.instagram.service.rating.RatingService;
import by.zverugo.samsolutions.instagram.service.user.UserService;
import by.zverugo.samsolutions.instagram.util.enums.UserRoleEnum;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String redirectToAuthUser() {
        UserDTO authUser = authorizationService.getAuthUser();

        return "redirect:user/" + authUser.getUserId();
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public String userPage(@PathVariable("id") long id, Model model) throws UnsupportedEncodingException {
        UserDTO authUser = authorizationService.getAuthUser();
        UserDTO user = userService.getUserById(id);

        if (user == null || UserRoleEnum.ADMIN.getRole().equals(user.getRole().getRole())) {
            return "redirect:/users/user";
        }

        List<PostDTO> posts = postService.getReversedListOfPostsByIdOfOwner(id);
        userService.setPostSendersUsernames(posts);
        ProfileDTO profile = profileService.getProfileByUserId(id);
        profileService.setPostSendersProfiles(posts);
        for (PostDTO post : posts) {
            ratingService.setPostRatings(post);
        }
        model.addAttribute("authUser", authUser);
        model.addAttribute("profile", profile);
        model.addAttribute("commentForm", new CommentDTO());
        model.addAttribute("posts", posts);
        model.addAttribute("username", user.getLogin());

        return "user/user";
    }

    @JsonView(Views.Search.class)
    @RequestMapping(value = "/findUser", method = RequestMethod.POST)
    public
    @ResponseBody
    List<UserDTO> searchPage(@RequestBody UserDTO user) {
        if (StringUtils.isNotEmpty(user.getLogin())) {
            List<UserDTO> users = userService.findByPattern(user.getLogin());
            return users;
        }

        return new ArrayList<UserDTO>();
    }

}
