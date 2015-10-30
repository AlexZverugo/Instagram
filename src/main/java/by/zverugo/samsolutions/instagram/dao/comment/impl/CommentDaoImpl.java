package by.zverugo.samsolutions.instagram.dao.comment.impl;

import by.zverugo.samsolutions.instagram.dao.comment.CommentDao;
import by.zverugo.samsolutions.instagram.entity.Comment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("commentDao")
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void saveComment(Comment comment) {
        sessionFactory.getCurrentSession().save(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Comment comment) {
        sessionFactory.getCurrentSession().delete(comment);
    }

    @Override
    @Transactional
    public void updateComment(Comment comment) {
        sessionFactory.getCurrentSession().update(comment);
    }

    @Override
    @Transactional
    public Comment getComment(long id) {
        Comment comment;
        comment = (Comment) sessionFactory.getCurrentSession().load(Comment.class, id);
        return comment;
    }

    @Override
    @Transactional
    public List<Comment> getListOfComments() {
        List<Comment> comments;
        comments = sessionFactory.getCurrentSession().createCriteria(Comment.class).list();
        return comments;
    }
}
