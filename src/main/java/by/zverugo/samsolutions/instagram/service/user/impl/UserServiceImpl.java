package by.zverugo.samsolutions.instagram.service.user.impl;

import by.zverugo.samsolutions.instagram.converter.user.UserDTOToUserConverter;
import by.zverugo.samsolutions.instagram.converter.user.UserToUserDTOConverter;
import by.zverugo.samsolutions.instagram.dao.user.UserDao;
import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import by.zverugo.samsolutions.instagram.service.user.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alzv on 12.10.2015.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private final Logger logger = Logger.getLogger(getClass());


    @Autowired
    private UserDao userDao;

    @Autowired
    private UserToUserDTOConverter userToUserDTOConverter;

    @Autowired
    private UserDTOToUserConverter userDTOToUserConverter;

    @Override
    @Transactional
    public void saveUser(UserDTO userDTO) {
        User user;
        user = userDTOToUserConverter.convert(userDTO);
        userDao.saveUser(user);
        logger.info("INFO: UserServiceImpl: save user" + user);
    }

    @Override
    @Transactional
    public void deleteUser(UserDTO userDTO) {
        userDao.deleteUserById(userDTO.getId());
        logger.info("INFO: UserServiceImpl: delete user");
    }

    @Override
    @Transactional
    public void updateUser(UserDTO userDTO) {
        User user;
        user = userDTOToUserConverter.convert(getUserById(userDTO.getId()));
        userDao.updateUser(user);
        logger.info("INFO: UserServiceImpl: update user" + user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByLogin(String login) {
        return userToUserDTOConverter.convert(userDao.getUserByName(login));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(long id) {
        return userToUserDTOConverter.convert(userDao.getUser(id));
    }


    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getListOfUsers() {
        List<User> users = userDao.getListOfUsers();
        List<UserDTO> userDTOList = new ArrayList();
        for (User user : users) {
            userDTOList.add(userToUserDTOConverter.convert(user));
        }
        return userDTOList;
    }
}
