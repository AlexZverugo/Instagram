package by.zverugo.samsolutions.instagram.controller;

import by.zverugo.samsolutions.instagram.dto.CommentDTO;
import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.jsonview.Views;
import by.zverugo.samsolutions.instagram.service.comment.CommentService;
import by.zverugo.samsolutions.instagram.service.user.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.Map;

@RestController
@SessionAttributes({"authorizedUser"})
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @JsonView(Views.Comment.class)
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public CommentDTO addComment(@RequestBody CommentDTO comment,
                                 @ModelAttribute("authorizedUser") UserDTO authUser) {
        comment.setSender(authUser.getUserId());
        comment = commentService.getComment(commentService.saveComment(comment));
        comment.setSenderName(authUser.getLogin());
        
        return comment;
    }

    @JsonView(Views.Comment.class)
    @RequestMapping(value = "/getCommentOfPost", method = RequestMethod.GET)
    public List<CommentDTO> getComments(@RequestParam Long id) {
        List<CommentDTO> comments = commentService.getListOfPostsByPostId(id);
        Map<Long, String> senders = userService.getCommentSendersNames(comments);
        comments = commentService.setSendersNameToCommentList(comments, senders);

        return comments;
    }

    @JsonView(Views.Comment.class)
    @RequestMapping(value = "/deleteComment", method = RequestMethod.GET)
    public void removePost(@RequestParam Long id,
                              @ModelAttribute("authorizedUser") UserDTO authUser) {
        CommentDTO comment = commentService.getComment(id);

        if (authUser.getUserId().equals(comment.getSender()) || authUser.getUserId().equals(comment.getOwner())) {
            commentService.deleteComment(id);
        }
    }
}
