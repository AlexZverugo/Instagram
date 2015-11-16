import by.zverugo.samsolutions.instagram.dao.profile.ProfileDao;
import by.zverugo.samsolutions.instagram.entity.Profile;
import by.zverugo.samsolutions.instagram.entity.User;
import by.zverugo.samsolutions.instagram.hash.PasswordHashEncoder;
import by.zverugo.samsolutions.instagram.util.enums.UserRoleEnum;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Locale;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/application-context.xml"})
@Transactional
public class ProfileTest {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private Profile profile;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ProfileDao profileDao;

    @Autowired
    private PasswordHashEncoder passwordHashEncoder;

    @Before
    public void init() {
        profile = new Profile();
        profile.setId(222L);
        profile.setBirthday(new Date(100000));
        profile.setRelationshipStatus("married");
        profile.setFirstName("Max");
        profile.setSurname("Petrenko");
        profile.setSecondName("Vitalievich");
        profile.setSex("male");
        profile.setCountry("Belarus");
        profile.setCity("Minsk");

        User user = new User();
        user.setId(222L);
        user.setLogin("test_user");
        user.setPassword(passwordHashEncoder.encode("123456"));
        user.setEmail("user@gmail.com");
        user.setRole(UserRoleEnum.USER.getRole());
        profile.setUser(user);

        LOGGER.info(messageSource.getMessage("test.profile.init", new Object[]{profile}, Locale.ENGLISH));
    }

    @Test
    public void testSaveProfile() {
        long id = profileDao.saveProfile(profile);
        LOGGER.info(messageSource.getMessage("test.profile.save", new Object[]{profile, id}, Locale.ENGLISH));
    }

    @Test
    public void testGetProfile() {
        profileDao.updateProfile(profile);
        profile = profileDao.getProfile(profile.getId());
        LOGGER.info(messageSource.getMessage("test.profile.get", new Object[]{profile}, Locale.ENGLISH));
    }

    @Test
    public void testUpdateProfile() {
        profileDao.updateProfile(profile);
        Profile temp = profileDao.getProfile(profile.getId());
        LOGGER.info(messageSource.getMessage("test.profile.update", new Object[]{profile.getId(),
                temp, temp.getId()}, Locale.ENGLISH));
    }

    @Test
    public void testDeleteProfile() {
        long id = profileDao.saveProfile(profile);
        profileDao.deleteProfile(id);
        LOGGER.info(messageSource.getMessage("test.profile.delete", new Object[]{id}, Locale.ENGLISH));
    }
}
