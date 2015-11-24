import by.zverugo.samsolutions.instagram.dao.rating.RatingDao;
import by.zverugo.samsolutions.instagram.entity.Rating;
import by.zverugo.samsolutions.instagram.util.InstagramConstants;
import by.zverugo.samsolutions.instagram.util.enums.RatingTypeEnum;
import org.apache.log4j.Logger;
import org.junit.Assert;
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

    @Before
    public void init() {
        rating = new Rating();
        rating.setType(RatingTypeEnum.LIKE.getType());

        LOGGER.info(messageSource.getMessage("test.rating.init", new Object[]{rating}, InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testSaveProfile() {
        ratingDao.saveRating(rating);
        Rating temp = ratingDao.getRating(rating.getRatingId());
        Assert.assertNotNull(temp);

        LOGGER.info(messageSource.getMessage("test.rating.save.success", new Object[]{rating, rating.getRatingId(), temp},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testGetProfile() {
        ratingDao.saveRating(rating);
        Rating temp = ratingDao.getRating(rating.getRatingId());
        Assert.assertNotNull(temp);

        LOGGER.info(messageSource.getMessage("test.rating.get.success", new Object[]{rating.getRatingId(), temp},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testUpdateProfile() {
        ratingDao.saveRating(rating);
        rating.setType(RatingTypeEnum.DISLIKE.getType());
        ratingDao.updateRating(rating);
        String type = ratingDao.getRating(rating.getRatingId()).getType();
        Assert.assertEquals(type, RatingTypeEnum.DISLIKE.getType());

        LOGGER.info(messageSource.getMessage("test.rating.update.success", null, InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testDeleteProfile() {
        ratingDao.saveRating(rating);
        ratingDao.deleteRating(rating.getRatingId());
        Rating deletedRating = ratingDao.getRating(rating.getRatingId());
        Assert.assertNull(deletedRating);

        LOGGER.info(messageSource.getMessage("test.rating.delete.success", null, InstagramConstants.LOGGER_LOCALE));
    }

}
