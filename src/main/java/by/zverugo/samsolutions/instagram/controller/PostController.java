package by.zverugo.samsolutions.instagram.controller;

import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

@Controller
@SessionAttributes({"postOwnerId", "authorizedUser"})
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET)
    public String postPage(Map<String, Object> model) {
        model.put("postForm", new PostDTO());

        return "post/post";
    }

    @RequestMapping(value = "addPost", method = RequestMethod.POST)
    public String addPost(@ModelAttribute("postForm") PostDTO post,
                          @ModelAttribute("postOwnerId") String id,
                          @ModelAttribute("authorizedUser") UserDTO authUser) {
        //Temp!! Will be removed later
        post.setLike(0);
        post.setDislike(0);

        post.setSender(authUser.getId());
        post.setOwner(Long.valueOf(id));

        postService.savePost(post);
        return "redirect:../users/user/" + id;
    }
}
