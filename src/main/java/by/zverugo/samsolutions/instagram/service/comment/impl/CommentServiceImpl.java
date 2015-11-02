package by.zverugo.samsolutions.instagram.service.comment.impl;

import by.zverugo.samsolutions.instagram.converter.comment.CommentDTOToCommentConverter;
import by.zverugo.samsolutions.instagram.converter.comment.CommentToCommentDTOConverter;
import by.zverugo.samsolutions.instagram.dao.comment.CommentDao;
import by.zverugo.samsolutions.instagram.dto.CommentDTO;
import by.zverugo.samsolutions.instagram.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import by.zverugo.samsolutions.instagram.service.comment.CommentService;

import java.util.ArrayList;
import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private CommentDTOToCommentConverter commentDTOToCommentConverter;

    @Autowired
    private CommentToCommentDTOConverter commentToCommentDTOConverter;

    @Override
    @Transactional
    public void saveComment(CommentDTO commentDTO) {
        Comment comment;
        comment = commentDTOToCommentConverter.convert(commentDTO);
        commentDao.saveComment(comment);
    }

    @Override
    @Transactional
    public void deleteComment(CommentDTO commentDTO) {
        Comment comment;
        comment = commentDTOToCommentConverter.convert(getComment(commentDTO.getId()));
        commentDao.deleteComment(comment);
    }

    @Override
    @Transactional
    public void updateComment(CommentDTO commentDTO) {
        Comment comment;
        comment = commentDTOToCommentConverter.convert(getComment(commentDTO.getId()));
        commentDao.updateComment(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDTO getComment(long id) {
        return commentToCommentDTOConverter.convert(commentDao.getComment(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDTO> getListOfComments() {
        List<Comment> comments = commentDao.getListOfComments();
        List<CommentDTO> commentDTOList = new ArrayList();

        for (Comment comment : comments) {
            commentDTOList.add(commentToCommentDTOConverter.convert(comment));
        }

        return commentDTOList;
    }
}
