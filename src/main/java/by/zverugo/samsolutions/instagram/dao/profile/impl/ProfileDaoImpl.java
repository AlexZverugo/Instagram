package by.zverugo.samsolutions.instagram.dao.profile.impl;

import by.zverugo.samsolutions.instagram.dao.profile.ProfileDao;
import by.zverugo.samsolutions.instagram.entity.Profile;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository("profileDao")
public class ProfileDaoImpl implements ProfileDao {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long saveProfile(Profile profile) {
        sessionFactory.getCurrentSession().save(profile);
        LOGGER.info(messageSource.getMessage("dao.profile.save", new Object[]{profile}, Locale.ENGLISH));

        return profile.getId();
    }

    @Override
    public void deleteProfile(long id) {
        String posthql = "delete FROM  by.zverugo.samsolutions.instagram.entity.Profile P WHERE P.id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(posthql);
        query.setParameter("id", id);
        query.executeUpdate();
        LOGGER.info(messageSource.getMessage("dao.profile.delete", new Object[]{id}, Locale.ENGLISH));
    }

    @Override
    public void updateProfile(Profile profile) {
        sessionFactory.getCurrentSession().update(profile);
        LOGGER.info(messageSource.getMessage("dao.profile.update", new Object[]{profile}, Locale.ENGLISH));
    }

    @Override
    public Profile getProfile(long id) {
        Profile profile;
        profile = sessionFactory.getCurrentSession().get(Profile.class, id);
        LOGGER.info(messageSource.getMessage("dao.profile.getById", new Object[]{id}, Locale.ENGLISH));

        return profile;
    }

    @Override
    public Profile getProfileByUserId(long id) {
        String posthql = "FROM  by.zverugo.samsolutions.instagram.entity.Profile P WHERE P.user.id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(posthql);
        query.setParameter("id", id);
        Profile profile = (Profile) query.uniqueResult();
        LOGGER.info(messageSource.getMessage("dao.profile.getByUserId", new Object[]{id}, Locale.ENGLISH));

        return profile;
    }

    @Override
    public List<Profile> getListOfProfiles() {
        List<Profile> profiles;
        profiles = sessionFactory.getCurrentSession().createCriteria(Profile.class).list();
        LOGGER.info(messageSource.getMessage("dao.profile.getList", new Object[]{profiles}, Locale.ENGLISH));

        return profiles;
    }
}
