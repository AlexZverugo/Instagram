package by.zverugo.samsolutions.instagram.dto;

import by.zverugo.samsolutions.instagram.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class CommentDTO {
    private Long id;

    @JsonView(Views.Comment.class)
    private String commentContent;

    @JsonView(Views.Comment.class)
    private Long post;

    @JsonView(Views.Comment.class)
    private String senderName;
    private Long sender;

    public Long getPost() {
        return post;
    }

    public void setPost(Long post) {
        this.post = post;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
