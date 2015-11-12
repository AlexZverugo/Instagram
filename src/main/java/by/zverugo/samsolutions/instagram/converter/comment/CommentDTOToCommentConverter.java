package by.zverugo.samsolutions.instagram.converter.comment;

import by.zverugo.samsolutions.instagram.dto.CommentDTO;
import by.zverugo.samsolutions.instagram.entity.Comment;
import by.zverugo.samsolutions.instagram.entity.Post;
import by.zverugo.samsolutions.instagram.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class CommentDTOToCommentConverter implements Converter<CommentDTO, Comment> {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Override
    public Comment convert(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setCommentId(commentDTO.getId());
        comment.setLike(commentDTO.getLike());
        comment.setDislike(commentDTO.getDislike());
        comment.setCommentContent(commentDTO.getCommentContent());

        Post post = new Post();
        post.setPostId(commentDTO.getPost());
        comment.setPost(post);
        User user = new User();
        user.setId(commentDTO.getSender());
        comment.setSender(user);

        LOGGER.info(messageSource.getMessage("converter.convert",
                new Object[]{"CommentDTO", "Comment", commentDTO, comment}, Locale.ENGLISH));

        return comment;
    }
}
