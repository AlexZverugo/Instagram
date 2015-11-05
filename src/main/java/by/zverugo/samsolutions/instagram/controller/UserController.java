package by.zverugo.samsolutions.instagram.controller;

import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.service.post.PostService;
import by.zverugo.samsolutions.instagram.service.user.UserService;
import by.zverugo.samsolutions.instagram.util.enums.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String checkUser(HttpSession httpSession) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = userService.getUserByLogin(authentication.getName());
        httpSession.setAttribute("authorizedUser", userDTO);
        return "redirect:user/" + userDTO.getId();
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public String userPage(@PathVariable("id") long id, Model model) throws UnsupportedEncodingException {
        UserDTO user = userService.getUserById(id);

        if (user == null || UserRoleEnum.ADMIN.getRole().equals(user.getRole().getRole())) {
            return "redirect:/users/user";
        }

        List<PostDTO> posts = postService.getReversedListOfPostsByIdOfOwner(id);
        Map<Long, String> usernames = userService.getPostSendersUsernames(posts);
        model.addAttribute("usernames", usernames);
        model.addAttribute("posts", posts);
        model.addAttribute("username", user.getLogin());

//        String base64Encoded  = Base64.encodeBase64String(postService.getByteOfPicture("26/21/melon.jpg"));

//        model.addAttribute("img",postService.getByteOfPicture("26/21/melon.jpg"));
        return "user/user";
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public String addPost(@RequestParam(required = true) long id, HttpSession httpSession) {
        httpSession.setAttribute("postOwnerId", id);
        return "redirect:/post";
    }

}
