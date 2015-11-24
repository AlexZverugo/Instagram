package by.zverugo.samsolutions.instagram.controller;

import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.jsonview.Views;
import by.zverugo.samsolutions.instagram.service.post.PostService;
import com.fasterxml.jackson.annotation.JsonView;
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
@SessionAttributes({"authorizedUser"})
@RequestMapping(value = "/post")
public class PostController {
    //TODO pagination

    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET)
    public String postPage(@RequestParam("postOwnerId") long id,
                           Map<String, Object> model) {
        PostDTO post = new PostDTO();
        post.setOwner(id);
        model.put("postForm", post);
        return "post/post";
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public String addPost(@ModelAttribute("postForm") PostDTO post,
                          @ModelAttribute("authorizedUser") UserDTO authUser) throws IOException {
        post.setLike(0);
        post.setDislike(0);
        post.setSender(authUser.getUserId());
        post.setImageByte(post.getPicture().getBytes());
        post.setId(postService.savePost(post));

        return "redirect:/users/user/" + post.getOwner();
    }

    @JsonView(Views.Comment.class)
    @RequestMapping(value = "/deletePost", method = RequestMethod.GET)
    public @ResponseBody void removePost(@RequestParam Long id,
                                            @ModelAttribute("authorizedUser") UserDTO authUser) {
        PostDTO post = postService.getPost(id);

        if (authUser.getUserId().equals(post.getOwner()) || authUser.getUserId().equals(post.getSender())) {
            postService.deletePost(id);
        }
    }

}
