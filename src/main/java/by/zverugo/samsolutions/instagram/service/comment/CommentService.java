package by.zverugo.samsolutions.instagram.service.comment;

import by.zverugo.samsolutions.instagram.dto.CommentDTO;
import java.util.List;
import java.util.Map;

public interface CommentService {
    long saveComment(CommentDTO commentDTO);
    void deleteComment(long id);
    void updateComment(CommentDTO commentDTO);
    CommentDTO getComment(long id);
    List<CommentDTO> getListOfComments();
    List<CommentDTO> getListOfPostsByPostId(long id);
    List<CommentDTO> setSendersNameToCommentList(List<CommentDTO> comments, Map<Long, String> senders);

}
