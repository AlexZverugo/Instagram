package by.zverugo.samsolutions.instagram.dao.user.impl;

import by.zverugo.samsolutions.instagram.dao.user.UserDao;
import by.zverugo.samsolutions.instagram.entity.User;
import by.zverugo.samsolutions.instagram.util.InstagramConstants;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long saveUser(User user) {
        sessionFactory.getCurrentSession().save(user);
        LOGGER.info(messageSource.getMessage("dao.user.save", new Object[]{user}, InstagramConstants.LOGGER_LOCALE));
        return user.getId();
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
        LOGGER.info(messageSource.getMessage("dao.user.update", new Object[]{user}, InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    public User getUser(long id) {
        User user;
        user = sessionFactory.getCurrentSession().get(User.class, id);
        LOGGER.info(messageSource.getMessage("dao.user.getById", new Object[]{id, user}, InstagramConstants.LOGGER_LOCALE));

        return user;
    }

    @Override
    public User getUserByName(String login) {
        String userhql = "FROM  by.zverugo.samsolutions.instagram.entity.User U WHERE U.login = :login";
        Query query = sessionFactory.getCurrentSession().createQuery(userhql);
        query.setParameter("login", login);
        User user = (User) query.uniqueResult();
        LOGGER.info(messageSource.getMessage("dao.user.getUserByName", new Object[]{login, user}, InstagramConstants.LOGGER_LOCALE));

        return user;
    }

    @Override
    public List<User> getListOfUsers() {
        List<User> users;
        users = sessionFactory.getCurrentSession().createCriteria(User.class).list();
        LOGGER.info(messageSource.getMessage("dao.user.getList", new Object[]{users}, InstagramConstants.LOGGER_LOCALE));

        return users;
    }

    @Override
    public void deleteUserById(long id) {
        User user = new User();
        user.setId(id);
        User mergedUser = (User) sessionFactory.getCurrentSession().merge(user);
        sessionFactory.getCurrentSession().delete(mergedUser);

        LOGGER.info(messageSource.getMessage("dao.user.deleteUserById", new Object[]{id}, InstagramConstants.LOGGER_LOCALE));
    }
}
