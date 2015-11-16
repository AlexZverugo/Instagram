package by.zverugo.samsolutions.instagram.service.comment;

import by.zverugo.samsolutions.instagram.dto.CommentDTO;
import by.zverugo.samsolutions.instagram.entity.Comment;

import java.util.List;

/**
 * Created by alzv on 13.10.2015.
 */
public interface CommentService {
    public void saveComment(CommentDTO commentDTO);
    public void deleteComment(CommentDTO commentDTO);
    public void updateComment(CommentDTO commentDTO);
    public CommentDTO getComment(long id);
    public List<CommentDTO> getListOfComments();
    public List<CommentDTO> getListOfPostsByPostId(long id);

}
