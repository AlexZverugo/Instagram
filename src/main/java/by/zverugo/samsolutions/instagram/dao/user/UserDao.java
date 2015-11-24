package by.zverugo.samsolutions.instagram.dao.user;

import by.zverugo.samsolutions.instagram.entity.User;

import java.util.List;

public interface UserDao {
    long saveUser(User user);
    void updateUser(User user);
    User getUser(long id);
    User getUserByName(String login);
    List<User> getListOfUsers();
    void deleteUserById(long id);
}
