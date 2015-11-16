package by.zverugo.samsolutions.instagram.dto;

import by.zverugo.samsolutions.instagram.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class AjaxCommentList {
    @JsonView(Views.Public.class)
    private List<CommentDTO> comments;

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}
