package by.zverugo.samsolutions.instagram.controller;

import by.zverugo.samsolutions.instagram.dto.CommentDTO;
import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.service.post.PostService;
import by.zverugo.samsolutions.instagram.service.user.UserService;
import by.zverugo.samsolutions.instagram.util.enums.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes({"authorizedUser"})
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String checkUser(@ModelAttribute("authorizedUser") long authUser) {
        return "redirect:user/" + authUser;
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public String userPage(@PathVariable("id") long id,
                           @ModelAttribute("authorizedUser") long authUser,
                           Model model) throws UnsupportedEncodingException {
        UserDTO user = userService.getUserById(id);

        if (authUser == id) {
            model.addAttribute("removingCross", true);
        } else {
            model.addAttribute("removingCross", false);
        }

        if (user == null || UserRoleEnum.ADMIN.getRole().equals(user.getRole().getRole())) {
            return "redirect:/users/user";
        }

        List<PostDTO> posts = postService.getReversedListOfPostsByIdOfOwner(id);
        postService.encodePostContent(posts);
        Map<Long, String> usernames = userService.getPostSendersUsernames(posts);
//        model.addAttribute("commentForm", new CommentDTO());
        model.addAttribute("usernames", usernames);
        model.addAttribute("posts", posts);
        model.addAttribute("username", user.getLogin());

        return "user/user";
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public String addPost(@RequestParam(required = true) long id, HttpSession httpSession) {
        httpSession.setAttribute("postOwnerId", id);
        return "redirect:/post";
    }

    @RequestMapping(value = "/findUser", method = RequestMethod.POST)
    public String searchPage(@ModelAttribute("searchForm") UserDTO userDTO) {

        return "redirect:/users/user";
    }

}
