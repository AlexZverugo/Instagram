package by.zverugo.samsolutions.instagram.converter.post;

import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.entity.Post;
import by.zverugo.samsolutions.instagram.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class PostDTOToPostConverter implements Converter<PostDTO, Post> {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Override
    public Post convert(PostDTO postDTO) {
        Post post = new Post();
        post.setPostId(postDTO.getId());
        post.setDislike(postDTO.getDislike());
        post.setLike(postDTO.getLike());
        post.setPostContent(postDTO.getPostContent());

        if (postDTO.getImageByte().length > 0) {
            post.setImageBytes(postDTO.getImageByte());
        } else {
            post.setImageBytes(null);
        }

        User owner = new User();
        owner.setId(postDTO.getOwner());
        User sender = new User();
        sender.setId(postDTO.getSender());
        post.setOwner(owner);
        post.setSender(sender);

        LOGGER.info(messageSource.getMessage("converter.convert",
                new Object[]{"PostDTO", "Post", postDTO, post}, Locale.ENGLISH));

        return post;
    }


}
