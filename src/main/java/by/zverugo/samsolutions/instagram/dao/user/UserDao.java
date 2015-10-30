package by.zverugo.samsolutions.instagram.dao.user;

import by.zverugo.samsolutions.instagram.entity.User;

import java.util.List;

/**
 * Created by alzv on 13.10.2015.
 */
public interface UserDao {
    public void saveUser(User user);
    public void deleteUser(User user);
    public void updateUser(User user);
    public User getUser(long id);
    public User getUserByName(String login);
    public List<User> getListOfUsers();
    public void deleteUserById(long id);
}
