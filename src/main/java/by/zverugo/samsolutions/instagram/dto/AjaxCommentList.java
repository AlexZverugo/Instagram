package by.zverugo.samsolutions.instagram.dto;

import by.zverugo.samsolutions.instagram.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class AjaxCommentList {
    @JsonView(Views.Public.class)
    private List<AjaxComment> comments;

    public List<AjaxComment> getComments() {
        return comments;
    }

    public void setComments(List<AjaxComment> comments) {
        this.comments = comments;
    }
}
