import by.zverugo.samsolutions.instagram.dao.post.PostDao;
import by.zverugo.samsolutions.instagram.dao.rating.RatingDao;
import by.zverugo.samsolutions.instagram.dao.user.UserDao;
import by.zverugo.samsolutions.instagram.entity.Post;
import by.zverugo.samsolutions.instagram.entity.Rating;
import by.zverugo.samsolutions.instagram.entity.User;
import by.zverugo.samsolutions.instagram.hash.PasswordHashEncoder;
import by.zverugo.samsolutions.instagram.util.InstagramConstants;
import by.zverugo.samsolutions.instagram.util.enums.RatingTypeEnum;
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
public class RatingTest {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private Rating rating;
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private RatingDao ratingDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private PasswordHashEncoder passwordHashEncoder;

    @Before
    public void init() {
        rating = new Rating();
        rating.setType(RatingTypeEnum.LIKE.getType());
        rating.setRatingId(4523L);

        Post post = new Post();
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
        postDao.savePost(post);
        rating.setSender(user);
        rating.setPost(post);

        LOGGER.info(messageSource.getMessage("test.rating.init", new Object[]{rating}, InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testSaveProfile() {
        long id = ratingDao.saveRating(rating);
        Rating temp = ratingDao.getRating(id);
        if (temp != null) {
            LOGGER.info(messageSource.getMessage("test.rating.save.success", new Object[]{rating, id, temp},
                    InstagramConstants.LOGGER_LOCALE));
        } else {
            LOGGER.warn(messageSource.getMessage("test.rating.save.bad", new Object[]{rating, id},
                    InstagramConstants.LOGGER_LOCALE));
        }

    }

    @Test
    public void testGetProfile() {
        ratingDao.updateRating(rating);
        Rating temp = ratingDao.getRating(rating.getRatingId());
        if (temp != null) {
            LOGGER.info(messageSource.getMessage("test.rating.get.success", new Object[]{rating.getRatingId(), temp},
                    InstagramConstants.LOGGER_LOCALE));
        } else {
            LOGGER.warn(messageSource.getMessage("test.rating.get.bad", new Object[]{rating.getRatingId()},
                    InstagramConstants.LOGGER_LOCALE));
        }
    }

    @Test
    public void testUpdateProfile() {
        long id = ratingDao.saveRating(rating);
        rating.setType(RatingTypeEnum.DISLIKE.getType());
        ratingDao.updateRating(rating);
        String type = ratingDao.getRating(id).getType();
        if (type.equals(RatingTypeEnum.DISLIKE.getType())) {
            LOGGER.info(messageSource.getMessage("test.rating.update.success", null, InstagramConstants.LOGGER_LOCALE));
        } else {
            LOGGER.warn(messageSource.getMessage("test.rating.update.bad", null, InstagramConstants.LOGGER_LOCALE));
        }
    }

    @Test
    public void testDeleteProfile() {
        long id = ratingDao.saveRating(rating);
        ratingDao.deleteRating(id);
        Rating temp = ratingDao.getRating(id);
        if (temp == null) {
            LOGGER.info(messageSource.getMessage("test.rating.delete.success", null,
                    InstagramConstants.LOGGER_LOCALE));
        } else {
            LOGGER.warn(messageSource.getMessage("test.rating.delete.bad", null,
                    InstagramConstants.LOGGER_LOCALE));
        }
    }

}
