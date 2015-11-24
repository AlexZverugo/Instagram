import by.zverugo.samsolutions.instagram.dao.comment.CommentDao;
import by.zverugo.samsolutions.instagram.entity.Comment;
import by.zverugo.samsolutions.instagram.util.InstagramConstants;
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
public class CommentTest {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private Comment comment;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CommentDao commentDao;

    @Before
    public void init() {
        comment = new Comment();
        comment.setCommentContent("Hello world!");

        LOGGER.info(messageSource.getMessage("test.comment.init", new Object[]{comment}, InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testAddPost() {
        commentDao.saveComment(comment);
        Comment storedComment = commentDao.getComment(comment.getCommentId());
        Assert.assertNotNull(storedComment);

        LOGGER.info(messageSource.getMessage("test.comment.save", new Object[]{comment, storedComment},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testGetPost() {
        commentDao.saveComment(comment);
        Comment storedComment = commentDao.getComment(comment.getCommentId());
        Assert.assertNotNull(storedComment);

        LOGGER.info(messageSource.getMessage("test.comment.get", new Object[]{storedComment},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testUpdatePost() {
        commentDao.saveComment(comment);
        String commentContent = "Hi!";
        comment.setCommentContent(commentContent);
        commentDao.updateComment(comment);
        Comment updateComment = commentDao.getComment(comment.getCommentId());
        Assert.assertEquals(comment, updateComment);
        Assert.assertEquals(commentContent, updateComment.getCommentContent());

        LOGGER.info(messageSource.getMessage("test.comment.update", new Object[]{comment, comment.getCommentId()},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testDeletePost() {
        commentDao.saveComment(comment);
        commentDao.deleteComment(comment.getCommentId());
        comment = commentDao.getComment(comment.getCommentId());
        Assert.assertNull(comment);

        LOGGER.info(messageSource.getMessage("test.comment.delete",
                new Object[]{}, InstagramConstants.LOGGER_LOCALE));
    }
}
