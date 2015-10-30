package by.zverugo.samsolutions.instagram.service.user;

import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.entity.User;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

/**
 * Created by alzv on 13.10.2015.
 */
public interface UserService {
    public void saveUser(UserDTO userDTO) throws ConstraintViolationException;
    public void deleteUser(UserDTO userDTO);
    public void updateUser(UserDTO userDTO);
    public UserDTO getUserByLogin(String login);
    public UserDTO getUserById(long id);
    public List<UserDTO> getListOfUsers();
}
