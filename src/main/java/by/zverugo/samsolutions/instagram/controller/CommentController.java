package by.zverugo.samsolutions.instagram.controller;

import by.zverugo.samsolutions.instagram.dto.AjaxCommentList;
import by.zverugo.samsolutions.instagram.dto.CommentDTO;
import by.zverugo.samsolutions.instagram.jsonview.Views;
import by.zverugo.samsolutions.instagram.service.comment.CommentService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"authorizedUser"})
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @JsonView(Views.Public.class)
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public @ResponseBody CommentDTO addComment(@RequestBody CommentDTO comment,
                             @ModelAttribute("authorizedUser") long authUser) {
        comment.setLike(0);
        comment.setDislike(0);
        comment.setSender(authUser);
        commentService.saveComment(comment);

        return comment;
    }

    @JsonView(Views.Public.class)
    @RequestMapping(value = "/getCommentOfPost", method = RequestMethod.GET)
    public @ResponseBody AjaxCommentList getComments(@RequestParam Long id) {
        AjaxCommentList result = new AjaxCommentList();
        result.setComments(commentService.getListOfPostsByPostId(id));

        return result;
    }
}
