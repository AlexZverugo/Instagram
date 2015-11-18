package by.zverugo.samsolutions.instagram.dto;

import by.zverugo.samsolutions.instagram.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class AjaxComment {
    @JsonView(Views.Public.class)
    private String commentContent;
    @JsonView(Views.Public.class)
    private Long post;
    @JsonView(Views.Public.class)
    private String senderName;

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Long getPost() {
        return post;
    }

    public void setPost(Long post) {
        this.post = post;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
