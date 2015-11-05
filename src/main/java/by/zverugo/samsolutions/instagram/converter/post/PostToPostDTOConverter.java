package by.zverugo.samsolutions.instagram.converter.post;

import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.entity.Post;
import by.zverugo.samsolutions.instagram.service.post.PostService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PostToPostDTOConverter implements Converter<Post, PostDTO> {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private PostService postService;

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

        try {
            postDTO.setImageByte(postService.getByteOfPicture(post.getImgUrl()));
        } catch (IOException e) {
            LOGGER.warn("Convert warning image to byte[];",e);
        }

        postDTO.setPicturePath(post.getImgUrl());
        postDTO.setOwner(post.getOwner().getId());
        postDTO.setSender(post.getSender().getId());

        return postDTO;
    }
}
