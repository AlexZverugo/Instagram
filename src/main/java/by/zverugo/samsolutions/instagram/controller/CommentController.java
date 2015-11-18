package by.zverugo.samsolutions.instagram.controller;

import by.zverugo.samsolutions.instagram.dto.AjaxComment;
import by.zverugo.samsolutions.instagram.dto.AjaxCommentList;
import by.zverugo.samsolutions.instagram.dto.CommentDTO;
import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.jsonview.Views;
import by.zverugo.samsolutions.instagram.service.comment.CommentService;
import by.zverugo.samsolutions.instagram.service.user.UserService;
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

import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes({"authorizedUser"})
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @JsonView(Views.Public.class)
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public @ResponseBody AjaxComment addComment(@RequestBody CommentDTO comment,
                           @ModelAttribute("authorizedUser") UserDTO authUser) {
        AjaxComment ajaxComment = new AjaxComment();
        comment.setSender(authUser.getId());
        commentService.saveComment(comment);
        ajaxComment.setPost(comment.getPost());
        ajaxComment.setCommentContent(comment.getCommentContent());
        ajaxComment.setSenderName(authUser.getLogin());

        return ajaxComment;
    }

    @JsonView(Views.Public.class)
    @RequestMapping(value = "/getCommentOfPost", method = RequestMethod.GET)
    public @ResponseBody AjaxCommentList getComments(@RequestParam Long id) {
        AjaxCommentList result = new AjaxCommentList();
        List<CommentDTO> comments = commentService.getListOfPostsByPostId(id);
        Map<Long, String> senders = userService.getCommentSendersNames(comments);
        result.setComments(commentService.getAjaxCommentList(comments, senders));

        return result;
    }
}
