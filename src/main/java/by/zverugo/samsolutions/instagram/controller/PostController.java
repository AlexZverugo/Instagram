package by.zverugo.samsolutions.instagram.controller;

import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.service.post.PostService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Controller
@SessionAttributes({"postOwnerId", "authorizedUser"})
@RequestMapping(value = "/post")
public class PostController {

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
                          @ModelAttribute("authorizedUser") long authUser) throws IOException{
        post.setLike(0);
        post.setDislike(0);
        post.setSender(authUser);
        post.setOwner(id);
        post.setImageByte(post.getPicture().getBytes());
        post.setDateDispatch(postService.getCurrentDate());
        post.setId(postService.savePost(post));

        return "redirect:../users/user/" + id;
    }

    @RequestMapping(value = "/deletePost/id={id}", method = RequestMethod.GET)
    public String removePost(@PathVariable long id) {
        postService.deletePost(id);

        return "redirect:/users/user";
    }

}
