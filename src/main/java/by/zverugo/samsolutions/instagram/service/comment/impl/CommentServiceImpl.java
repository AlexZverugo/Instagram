package by.zverugo.samsolutions.instagram.service.comment.impl;

import by.zverugo.samsolutions.instagram.dao.comment.CommentDao;
import by.zverugo.samsolutions.instagram.dto.CommentDTO;
import by.zverugo.samsolutions.instagram.entity.Comment;
import by.zverugo.samsolutions.instagram.service.comment.CommentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        LOGGER.info(messageSource.getMessage("service.comment.save", new Object[]{commentDTO}, Locale.ENGLISH));
    }

    @Override
    @Transactional
    public void deleteComment(CommentDTO commentDTO) {
        Comment comment = conversionService.convert(getComment(commentDTO.getId()), Comment.class);
        commentDao.deleteComment(comment);
        LOGGER.info(messageSource.getMessage("service.comment.delete", new Object[]{commentDTO}, Locale.ENGLISH));
    }

    @Override
    @Transactional
    public void updateComment(CommentDTO commentDTO) {
        Comment comment = conversionService.convert(getComment(commentDTO.getId()), Comment.class);
        commentDao.updateComment(comment);
        LOGGER.info(messageSource.getMessage("service.comment.update", new Object[]{commentDTO}, Locale.ENGLISH));
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDTO getComment(long id) {
        CommentDTO commentDTO = conversionService.convert(commentDao.getComment(id), CommentDTO.class);
        LOGGER.info(messageSource.getMessage("service.comment.getById", new Object[]{id, commentDTO}, Locale.ENGLISH));
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
        LOGGER.info(messageSource.getMessage("service.comment.getList", new Object[]{commentDTOList}, Locale.ENGLISH));

        return commentDTOList;
    }
}
