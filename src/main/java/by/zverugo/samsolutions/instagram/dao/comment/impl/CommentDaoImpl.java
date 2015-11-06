package by.zverugo.samsolutions.instagram.dao.comment.impl;

import by.zverugo.samsolutions.instagram.dao.comment.CommentDao;
import by.zverugo.samsolutions.instagram.entity.Comment;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository("commentDao")
public class CommentDaoImpl implements CommentDao {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveComment(Comment comment) {
        sessionFactory.getCurrentSession().save(comment);
        LOGGER.info(messageSource.getMessage("dao.comment.save", new Object[]{comment}, Locale.ENGLISH));
    }

    @Override
    public void deleteComment(Comment comment) {
        sessionFactory.getCurrentSession().delete(comment);
        LOGGER.info(messageSource.getMessage("dao.comment.delete", new Object[]{comment}, Locale.ENGLISH));
    }

    @Override
    public void updateComment(Comment comment) {
        sessionFactory.getCurrentSession().update(comment);
        LOGGER.info(messageSource.getMessage("dao.comment.update", new Object[]{comment}, Locale.ENGLISH));
    }

    @Override
    public Comment getComment(long id) {
        Comment comment = sessionFactory.getCurrentSession().get(Comment.class, id);
        LOGGER.info(messageSource.getMessage("dao.comment.getById", new Object[]{id}, Locale.ENGLISH));

        return comment;
    }

    @Override
    public List<Comment> getListOfComments() {
        List<Comment> comments = sessionFactory.getCurrentSession().createCriteria(Comment.class).list();
        LOGGER.info(messageSource.getMessage("dao.comment.getList", new Object[] {comments}, Locale.ENGLISH));

        return comments;
    }
}
