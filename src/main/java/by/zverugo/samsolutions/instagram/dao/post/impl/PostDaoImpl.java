package by.zverugo.samsolutions.instagram.dao.post.impl;

import by.zverugo.samsolutions.instagram.dao.post.PostDao;
import by.zverugo.samsolutions.instagram.entity.Post;
import by.zverugo.samsolutions.instagram.entity.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postDao")
public class PostDaoImpl implements PostDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void savePost(Post post) {
        sessionFactory.getCurrentSession().save(post);
    }

    @Override
    public void deletePost(Post post) {
        sessionFactory.getCurrentSession().delete(post);
    }

    @Override
    public void updatePost(Post post) {
        sessionFactory.getCurrentSession().update(post);
    }

    @Override
    public Post getPost(long id) {
        Post post;
        post = sessionFactory.getCurrentSession().get(Post.class,id);
        return post;
    }

    @Override
    public List<Post> getListOfPosts() {
        List<Post> posts;
        posts = sessionFactory.getCurrentSession().createCriteria(Post.class).list();
        return posts;
    }

    @Override
    public List<Post> getListOfPostsByIdOfOwner(long id) {
        String posthql = "FROM  by.zverugo.samsolutions.instagram.entity.Post P WHERE P.owner.id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(posthql);
        query.setParameter("id", id);
        List<Post> posts = query.list();
        return posts;
    }
}
