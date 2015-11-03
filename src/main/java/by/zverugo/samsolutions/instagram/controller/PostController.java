package by.zverugo.samsolutions.instagram.controller;

import by.zverugo.samsolutions.instagram.dto.ImageDTO;
import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.service.post.PostService;
import org.apache.log4j.Logger;
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

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET)
    public String postPage(Map<String, Object> model) {
        model.put("postForm", new PostDTO());
        model.put("photoForm", new ImageDTO());
        return "post/post";
    }

    @RequestMapping(value = "addPost", method = RequestMethod.POST)
    public String addPost(@ModelAttribute("postForm") PostDTO post,
                          @ModelAttribute("postOwnerId") long id,
                          @ModelAttribute("authorizedUser") UserDTO authUser) {
        //Temp!! Will be removed later
        post.setLike(0);
        post.setDislike(0);

        post.setSender(authUser.getId());
        post.setOwner(id);

        postService.savePost(post);
        return "redirect:../users/user/" + id;
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFileUpload(@ModelAttribute("photoForm") ImageDTO imageDTO) {

        System.getProperty("java.io.tmpdir");

        if (imageDTO.getPhoto() != null) {
            logger.info("File name: " + imageDTO.getPhoto().getOriginalFilename());
            logger.info("File content: " + imageDTO.getPhoto());
        } else {
            logger.info("No one file is upload!!!");
        }
        return "redirect:/post";
    }
}
