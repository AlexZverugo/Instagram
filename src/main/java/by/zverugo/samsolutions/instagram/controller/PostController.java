package by.zverugo.samsolutions.instagram.controller;

import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.jsonview.Views;
import by.zverugo.samsolutions.instagram.service.post.PostService;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.IOException;
import java.util.Map;

@Controller
@SessionAttributes({"postOwnerId", "authorizedUser"})
@RequestMapping(value = "/post")
public class PostController {
    //TODO pagination
    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET)
    public String postPage(Map<String, Object> model) {
        model.put("postForm", new PostDTO());
        return "post/post";
    }

    @RequestMapping(value = "addPost", method = RequestMethod.POST)
    public String addPost(@ModelAttribute("postForm") PostDTO post,
                          @ModelAttribute("postOwnerId") long id,
                          @ModelAttribute("authorizedUser") UserDTO authUser) throws IOException {
        post.setLike(0);
        post.setDislike(0);
        post.setSender(authUser.getId());
        post.setOwner(id);
        post.setImageByte(post.getPicture().getBytes());
        post.setId(postService.savePost(post));

        return "redirect:../users/user/" + id;
    }

    @JsonView(Views.Public.class)
    @RequestMapping(value = "/deletePost", method = RequestMethod.GET)
    public @ResponseBody void removePost(@RequestParam Long id) {
        postService.deletePost(id);
    }

}
