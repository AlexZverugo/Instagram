package by.zverugo.samsolutions.instagram.converter.post;

import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.entity.Post;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostToPostDTOConverter implements Converter<Post, PostDTO> {
    @Override
    public PostDTO convert(Post post) {
        if (post == null) {
            return null;
        }
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getPostId());
        postDTO.setDislike(post.getDislike());
        postDTO.setLike(post.getLike());
        postDTO.setPostContent(post.getPostContent());
//        postDTO.setPicture();
        postDTO.setPicturePath(post.getImgUrl());
        postDTO.setOwner(post.getOwner().getId());
        postDTO.setSender(post.getSender().getId());

        return postDTO;
    }
}
