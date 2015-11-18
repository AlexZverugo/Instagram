package by.zverugo.samsolutions.instagram.converter.post;

import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.entity.Post;
import by.zverugo.samsolutions.instagram.util.InstagramConstants;
import by.zverugo.samsolutions.instagram.util.LoggerLocale;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Locale;


@Component
public class PostToPostDTOConverter implements Converter<Post, PostDTO> {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

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
        postDTO.setImageByte(post.getImageBytes());
        postDTO.setOwner(post.getOwner().getId());
        postDTO.setSender(post.getSender().getId());

        convertTimestampToString(post, postDTO);

        LOGGER.info(messageSource.getMessage("converter.convert",
                new Object[]{"Post", "PostDTO", post, postDTO}, LoggerLocale.LOCALE));

        return postDTO;
    }

    private void convertTimestampToString(Post post, PostDTO postDTO) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(InstagramConstants.DATE_FORMAT);
        String date = dateFormat.format(post.getDateDispatch());
        postDTO.setDateDispatch(date);
    }
}
