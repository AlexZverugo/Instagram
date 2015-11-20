import by.zverugo.samsolutions.instagram.dao.post.PostDao;
import by.zverugo.samsolutions.instagram.dao.user.UserDao;
import by.zverugo.samsolutions.instagram.entity.Post;
import by.zverugo.samsolutions.instagram.entity.User;
import by.zverugo.samsolutions.instagram.hash.PasswordHashEncoder;
import by.zverugo.samsolutions.instagram.util.InstagramConstants;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/application-context.xml"})
@Transactional
public class PostTest {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private Post post;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordHashEncoder passwordHashEncoder;

    @Before
    public void init() {
        post = new Post();
        post.setPostId(423L);
        post.setImageBytes(null);
        post.setDislike(10);
        post.setLike(15);
        post.setPostContent("Hello world!");

        User user = new User();
        user.setId(222L);
        user.setLogin("test_user");
        user.setPassword(passwordHashEncoder.encode("123456"));
        user.setEmail("user@gmail.com");
        user.setRole(UserRoleEnum.USER.getRole());
        userDao.saveUser(user);
        post.setOwner(user);
        post.setSender(user);
        LOGGER.info(messageSource.getMessage("test.post.init", new Object[]{post}, InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testAddPost() {
        postDao.savePost(post);
        LOGGER.info(messageSource.getMessage("test.post.save", new Object[]{post, postDao.getPost(post.getPostId())},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testGetPost() {
        long id = postDao.savePost(post);
        Post postById = postDao.getPost(id);
        LOGGER.info(messageSource.getMessage("test.post.get", new Object[]{postById}, InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testUpdatePost() {
        postDao.savePost(post);
        post.setPostContent("Hi!");
        postDao.updatePost(post);
        Post temp = postDao.getPost(post.getPostId());
        LOGGER.info(messageSource.getMessage("test.post.update", new Object[]{post.getPostId(), temp},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testDeletePost() {
        long id = postDao.savePost(post);
        post = postDao.getPost(id);
        postDao.deletePost(id);
        LOGGER.info(messageSource.getMessage("test.post.delete",
                new Object[]{id, postDao.getPost(post.getPostId())}, InstagramConstants.LOGGER_LOCALE));
    }
}
