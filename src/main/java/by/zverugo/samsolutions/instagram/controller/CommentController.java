package by.zverugo.samsolutions.instagram.controller;

import by.zverugo.samsolutions.instagram.dto.CommentDTO;
import by.zverugo.samsolutions.instagram.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"authorizedUser"})
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public void addComment(@ModelAttribute("commentForm")CommentDTO commentDTO,
                           @ModelAttribute("authorizedUser") long authUser,
                           @RequestParam(required = true) long postId ) {
        commentDTO.setLike(0);
        commentDTO.setDislike(0);
        commentDTO.setPost(postId);
        commentDTO.setSender(authUser);
        commentService.saveComment(commentDTO);
    }
}
