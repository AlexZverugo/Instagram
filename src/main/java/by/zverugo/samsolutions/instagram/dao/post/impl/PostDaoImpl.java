package by.zverugo.samsolutions.instagram.dao.post.impl;

import by.zverugo.samsolutions.instagram.dao.post.PostDao;
import by.zverugo.samsolutions.instagram.entity.Post;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alzv on 12.10.2015.
 */
@Repository("postDao")
public class PostDaoImpl implements PostDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void savePost(Post post) {
        sessionFactory.getCurrentSession().save(post);
    }

    @Override
    @Transactional
    public void deletePost(Post post) {
        sessionFactory.getCurrentSession().delete(post);
    }

    @Override
    @Transactional
    public void updatePost(Post post) {
        sessionFactory.getCurrentSession().update(post);
    }

    @Override
    @Transactional
    public Post getPost(long id) {
        Post post;
        post = (Post) sessionFactory.getCurrentSession().load(Post.class,id);
        return post;
    }

    @Override
    @Transactional
    public List<Post> getListOfPosts() {
        List<Post> posts;
        posts = sessionFactory.getCurrentSession().createCriteria(Post.class).list();
        return posts;
    }
}
