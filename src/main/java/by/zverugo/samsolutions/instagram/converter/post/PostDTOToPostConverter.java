package by.zverugo.samsolutions.instagram.converter.post;

import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.entity.Post;
import by.zverugo.samsolutions.instagram.entity.User;
import by.zverugo.samsolutions.instagram.util.InstagramConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
        post.setPostContent(postDTO.getPostContent().trim());

        convertStringToTimestamp(post);
        convertPostImage(postDTO, post);

        User owner = new User();
        owner.setId(postDTO.getOwner());
        User sender = new User();
        sender.setId(postDTO.getSender());
        post.setOwner(owner);
        post.setSender(sender);

        LOGGER.info(messageSource.getMessage("converter.convert",
                new Object[]{"PostDTO", "Post", postDTO, post}, InstagramConstants.LOGGER_LOCALE));

        return post;
    }

    private void convertPostImage(PostDTO postDTO, Post post) {
        if (postDTO.getImageByte() == null || postDTO.getImageByte().length <= 0) {
            post.setImageBytes(null);
        } else {
            post.setImageBytes(postDTO.getImageByte());
        }
    }

    private void convertStringToTimestamp(Post post) {
        Date date = new Date();
        post.setDateDispatch(new Timestamp(date.getTime()));
    }

}
