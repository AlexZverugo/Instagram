package by.zverugo.samsolutions.instagram.service.user.impl;

import by.zverugo.samsolutions.instagram.dao.user.UserDao;
import by.zverugo.samsolutions.instagram.dto.CommentDTO;
import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.entity.User;
import by.zverugo.samsolutions.instagram.service.user.UserService;
import by.zverugo.samsolutions.instagram.util.InstagramConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ConversionService conversionService;

    @Override
    @Transactional
    public long saveUser(UserDTO userDTO) {
        User user = conversionService.convert(userDTO, User.class);
        long id = userDao.saveUser(user);
        LOGGER.info(messageSource.getMessage("service.user.save", new Object[]{userDTO}, InstagramConstants.LOGGER_LOCALE));
        return id;
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userDao.deleteUserById(id);
        LOGGER.info(messageSource.getMessage("service.user.delete", new Object[]{id}, InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    @Transactional
    public void updateUser(UserDTO userDTO) {
        User user = conversionService.convert(userDTO, User.class);
        userDao.updateUser(user);
        LOGGER.info(messageSource.getMessage("service.user.update", new Object[]{userDTO}, InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByLogin(String login) {
        UserDTO userDTO = conversionService.convert(userDao.getUserByName(login), UserDTO.class);
        LOGGER.info(messageSource.getMessage("service.user.getUserByLogin",
                new Object[]{login, userDTO}, InstagramConstants.LOGGER_LOCALE));

        return userDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(long id) {
        UserDTO userDTO = conversionService.convert(userDao.getUser(id), UserDTO.class);
        LOGGER.info(messageSource.getMessage("service.user.getUserById", new Object[]{id, userDTO}, InstagramConstants.LOGGER_LOCALE));

        return userDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getListOfUsers() {
        List<User> users = userDao.getListOfUsers();
        List<UserDTO> userDTOList = new ArrayList();
        for (User user : users) {
            userDTOList.add(conversionService.convert(user, UserDTO.class));
        }
        LOGGER.info(messageSource.getMessage("service.user.getList", new Object[]{userDTOList}, InstagramConstants.LOGGER_LOCALE));

        return userDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<Long, String> getPostSendersUsernames(List<PostDTO> posts) {
        Map<Long, String> usernames = new HashMap<>();

        for (PostDTO post : posts) {
            usernames.put(post.getSender(), getUserById(post.getSender()).getLogin());
        }
        LOGGER.info(messageSource.getMessage("service.user.getPostSendersUsernames",
                new Object[]{usernames}, InstagramConstants.LOGGER_LOCALE));

        return usernames;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<Long, String> getCommentSendersNames(List<CommentDTO> comments) {
        Map<Long, String> senders = new HashMap<>();

        for (CommentDTO comment : comments) {
            senders.put(comment.getSender(), getUserById(comment.getSender()).getLogin());
        }
        LOGGER.info(messageSource.getMessage("service.user.getCommentSendersNames",
                new Object[]{senders}, InstagramConstants.LOGGER_LOCALE));

        return senders;
    }
}
