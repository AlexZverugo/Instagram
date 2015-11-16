import by.zverugo.samsolutions.instagram.dao.user.UserDao;
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

import java.util.Locale;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/application-context.xml"})
@Transactional
public class UserTest {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private User user;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordHashEncoder passwordHashEncoder;


    @Before
    public void init() {
        user = new User();
        user.setId(225L);
        user.setLogin("user3");
        user.setPassword(passwordHashEncoder.encode("123456"));
        user.setEmail("user@gmail.com");
        user.setRole(UserRoleEnum.USER.getRole());
        LOGGER.info(messageSource.getMessage("test.user.init", new Object[]{user}, Locale.ENGLISH));
    }


    @Test
    public void testAddUser() {
        userDao.saveUser(user);
        LOGGER.info(messageSource.getMessage("test.user.save", new Object[]{user}, Locale.ENGLISH));
    }

    @Test
    public void testGetUser() {
        userDao.saveUser(user);
        User userByName = userDao.getUserByName(user.getLogin());
        User userById = userDao.getUser(userByName.getId());
        LOGGER.info(messageSource.getMessage("test.user.get", new Object[]{userByName,userById}, Locale.ENGLISH));
    }

    @Test
    public void testUpdateUser() {
        userDao.updateUser(user);
        User temp = userDao.getUser(user.getId());
        LOGGER.info(messageSource.getMessage("test.profile.update", new Object[]{user.getId(),
                temp, temp.getId()}, Locale.ENGLISH));
    }

    @Test
    public void testDeleteUser() {
        userDao.saveUser(user);
        user = userDao.getUserByName(user.getLogin());
        userDao.deleteUserById(user.getId());
        LOGGER.info(messageSource.getMessage("test.profile.delete", new Object[]{user.getId()}, Locale.ENGLISH));
    }
}
