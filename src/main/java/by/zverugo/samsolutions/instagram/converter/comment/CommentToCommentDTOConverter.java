package by.zverugo.samsolutions.instagram.converter.comment;

import by.zverugo.samsolutions.instagram.dto.CommentDTO;
import by.zverugo.samsolutions.instagram.entity.Comment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class CommentToCommentDTOConverter implements Converter<Comment, CommentDTO> {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Override
    public CommentDTO convert(Comment comment) {
        if (comment == null) {
            return null;
        }
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getCommentId());
        commentDTO.setLike(comment.getLike());
        commentDTO.setDislike(comment.getDislike());
        commentDTO.setCommentContent(comment.getCommentContent());
        commentDTO.setPost(comment.getPost().getPostId());
        commentDTO.setSender(comment.getSender().getId());

        LOGGER.info(messageSource.getMessage("converter.convert",
                new Object[]{"Comment", "CommentDTO", comment, commentDTO}, Locale.ENGLISH));

        return commentDTO;
    }
}
