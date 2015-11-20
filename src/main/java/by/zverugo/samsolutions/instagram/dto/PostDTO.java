package by.zverugo.samsolutions.instagram.dto;

import by.zverugo.samsolutions.instagram.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.multipart.MultipartFile;


public class PostDTO {

    @JsonView(Views.Rating.class)
    private Long id;
    private String postContent;
    private MultipartFile picture;
    private byte[] imageByte;

    @JsonView(Views.Rating.class)
    private Integer like;

    @JsonView(Views.Rating.class)
    private Integer dislike;
    private String dateDispatch;
    private Long sender;
    private Long owner;


    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }

    public Integer getDislike() {
        return dislike;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public String getDateDispatch() {
        return dateDispatch;
    }

    public void setDateDispatch(String dateDispatch) {
        this.dateDispatch = dateDispatch;
    }
}
