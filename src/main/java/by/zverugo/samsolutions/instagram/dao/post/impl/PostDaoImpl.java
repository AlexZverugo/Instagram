package by.zverugo.samsolutions.instagram.dao.post.impl;

import by.zverugo.samsolutions.instagram.dao.post.PostDao;
import by.zverugo.samsolutions.instagram.entity.Post;
import by.zverugo.samsolutions.instagram.util.LoggerLocale;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository("postDao")
public class PostDaoImpl implements PostDao {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long savePost(Post post) {
        sessionFactory.getCurrentSession().save(post);
        LOGGER.info(messageSource.getMessage("dao.post.save", new Object[]{post}, LoggerLocale.LOCALE));

        return post.getPostId();
    }

    @Override
    public void deletePost(long id) {
        String posthql = "delete FROM  by.zverugo.samsolutions.instagram.entity.Post P WHERE P.id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(posthql);
        query.setParameter("id", id);
        query.executeUpdate();
        LOGGER.info(messageSource.getMessage("dao.post.delete", new Object[]{id}, LoggerLocale.LOCALE));
    }

    @Override
    public void updatePost(Post post) {
        sessionFactory.getCurrentSession().update(post);
        LOGGER.info(messageSource.getMessage("dao.post.update", new Object[]{post}, LoggerLocale.LOCALE));
    }

    @Override
    public Post getPost(long id) {
        Post post;
        post = sessionFactory.getCurrentSession().get(Post.class, id);
        LOGGER.info(messageSource.getMessage("dao.post.getById", new Object[]{id}, LoggerLocale.LOCALE));

        return post;
    }

    @Override
    public List<Post> getListOfPosts() {
        List<Post> posts;
        posts = sessionFactory.getCurrentSession().createCriteria(Post.class).list();
        LOGGER.info(messageSource.getMessage("dao.post.getList", new Object[]{posts}, LoggerLocale.LOCALE));

        return posts;
    }

    @Override
    public List<Post> getListOfPostsByIdOfOwner(long id) {
        String posthql = "FROM  by.zverugo.samsolutions.instagram.entity.Post P WHERE P.owner.id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(posthql);
        query.setParameter("id", id);
        List<Post> posts = query.list();
        LOGGER.info(messageSource.getMessage("dao.post.getListByOwnerId", new Object[]{id, posts}, LoggerLocale.LOCALE));

        return posts;
    }
}
