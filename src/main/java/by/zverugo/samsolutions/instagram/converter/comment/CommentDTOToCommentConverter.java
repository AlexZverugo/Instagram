package by.zverugo.samsolutions.instagram.converter.comment;

import by.zverugo.samsolutions.instagram.converter.post.PostDTOToPostConverter;
import by.zverugo.samsolutions.instagram.dto.CommentDTO;
import by.zverugo.samsolutions.instagram.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import by.zverugo.samsolutions.instagram.service.post.PostService;

@Component
public class CommentDTOToCommentConverter implements Converter<CommentDTO, Comment> {

    @Autowired
    private PostService postService;



    @Autowired
    private PostDTOToPostConverter postDTOToPostConverter;

    @Override
    public Comment convert(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setCommentId(commentDTO.getId());
        comment.setLike(commentDTO.getLike());
        comment.setDislike(commentDTO.getDislike());
        comment.setCommentContent(commentDTO.getCommentContext());
        comment.setPost(postDTOToPostConverter.convert(postService.getPost(commentDTO.getId())));

        return comment;
    }
}
