package by.zverugo.samsolutions.instagram.dao.user.impl;

import by.zverugo.samsolutions.instagram.dao.user.UserDao;
import by.zverugo.samsolutions.instagram.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }


    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public User getUser(long id) {
        User user;
        user = sessionFactory.getCurrentSession().get(User.class, id);
        return user;
    }

    @Override
    public User getUserByName(String login) {
        String userhql = "FROM  by.zverugo.samsolutions.instagram.entity.User U WHERE U.login = :login";
        Query query = sessionFactory.getCurrentSession().createQuery(userhql);
        query.setParameter("login", login);
        logger.info("search result: " + query.uniqueResult());
        User user = (User) query.uniqueResult();

        return user;
    }

    @Override
    public List<User> getListOfUsers() {
        List<User> users;
        users = sessionFactory.getCurrentSession().createCriteria(User.class).list();
        return users;
    }

    @Override
    public void deleteUserById(long id) {
        String userhql = "FROM  by.zverugo.samsolutions.instagram.entity.User U WHERE U.id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(userhql);
        query.setParameter("id", id);
        logger.info("INFO: search by id result: " + query.list());
        List<User> users = query.list();
        if (users.size() > 0) {
            sessionFactory.getCurrentSession().delete(users.get(0));
        } else {
            return;
        }
    }
}
