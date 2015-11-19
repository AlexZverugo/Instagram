package by.zverugo.samsolutions.instagram.service.comment.impl;

import by.zverugo.samsolutions.instagram.dao.comment.CommentDao;
import by.zverugo.samsolutions.instagram.dto.CommentDTO;
import by.zverugo.samsolutions.instagram.entity.Comment;
import by.zverugo.samsolutions.instagram.service.comment.CommentService;
import by.zverugo.samsolutions.instagram.util.InstagramConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private ConversionService conversionService;

    @Override
    @Transactional
    public void saveComment(CommentDTO commentDTO) {
        Comment comment = conversionService.convert(commentDTO, Comment.class);
        commentDao.saveComment(comment);
        LOGGER.info(messageSource.getMessage("service.comment.save", new Object[]{commentDTO}, InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    @Transactional
    public void deleteComment(CommentDTO commentDTO) {
        Comment comment = conversionService.convert(getComment(commentDTO.getId()), Comment.class);
        commentDao.deleteComment(comment);
        LOGGER.info(messageSource.getMessage("service.comment.delete", new Object[]{commentDTO}, InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    @Transactional
    public void updateComment(CommentDTO commentDTO) {
        Comment comment = conversionService.convert(getComment(commentDTO.getId()), Comment.class);
        commentDao.updateComment(comment);
        LOGGER.info(messageSource.getMessage("service.comment.update", new Object[]{commentDTO}, InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDTO getComment(long id) {
        CommentDTO commentDTO = conversionService.convert(commentDao.getComment(id), CommentDTO.class);
        LOGGER.info(messageSource.getMessage("service.comment.getById", new Object[]{id, commentDTO}, InstagramConstants.LOGGER_LOCALE));
        return commentDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDTO> getListOfComments() {
        List<Comment> comments = commentDao.getListOfComments();
        List<CommentDTO> commentDTOList = new ArrayList();

        for (Comment comment : comments) {
            commentDTOList.add(conversionService.convert(comment, CommentDTO.class));
        }
        LOGGER.info(messageSource.getMessage("service.comment.getList", new Object[]{commentDTOList}, InstagramConstants.LOGGER_LOCALE));

        return commentDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDTO> getListOfPostsByPostId(long id) {
        List<CommentDTO> commentDTOList = new ArrayList<>();
        List<Comment> comments = commentDao.getListOfPostsByPostId(id);
        for (Comment comment : comments) {
            commentDTOList.add(conversionService.convert(comment, CommentDTO.class));
        }

        LOGGER.info(messageSource.getMessage("service.comment.getListOfPostsByPostId",
                new Object[]{id, commentDTOList}, InstagramConstants.LOGGER_LOCALE));

        return commentDTOList;
    }


    @Override
    public List<CommentDTO> setSendersNameToCommentList(List<CommentDTO> comments, Map<Long, String> senders) {
        for (CommentDTO comment : comments) {
            comment.setSenderName(senders.get(comment.getSender()));
        }

        return comments;
    }
}
