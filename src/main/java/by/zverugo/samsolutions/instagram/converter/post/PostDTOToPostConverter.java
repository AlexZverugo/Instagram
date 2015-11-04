package by.zverugo.samsolutions.instagram.converter.post;

import by.zverugo.samsolutions.instagram.converter.user.UserDTOToUserConverter;
import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import by.zverugo.samsolutions.instagram.service.user.UserService;

@Component
public class PostDTOToPostConverter implements Converter<PostDTO, Post>{

    @Autowired
    private UserService userService;

    @Autowired
    private UserDTOToUserConverter userDTOToUserConverter;

    @Override
    public Post convert(PostDTO postDTO) {
        Post post = new Post();
        post.setPostId(postDTO.getId());
        post.setDislike(postDTO.getDislike());
        post.setLike(postDTO.getLike());
        post.setPostContent(postDTO.getPostContent());
        post.setImgUrl(postDTO.getPicturePath());
        post.setOwner(userDTOToUserConverter.convert(userService.getUserById(postDTO.getOwner())));
        post.setSender(userDTOToUserConverter.convert(userService.getUserById(postDTO.getSender())));

        return post;
    }
}
