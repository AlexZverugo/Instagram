package by.zverugo.samsolutions.instagram.converter.comment;

import by.zverugo.samsolutions.instagram.dto.CommentDTO;
import by.zverugo.samsolutions.instagram.entity.Comment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentToCommentDTOConverter implements Converter<Comment, CommentDTO> {
    @Override
    public CommentDTO convert(Comment comment) {
        if (comment == null) {
            return null;
        }
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getCommentId());
        commentDTO.setLike(comment.getLike());
        commentDTO.setDislike(comment.getDislike());
        commentDTO.setCommentContext(comment.getCommentContent());
        commentDTO.setPost(comment.getPost().getPostId());
        commentDTO.setSender(comment.getSender().getId());

        return commentDTO;
    }
}
