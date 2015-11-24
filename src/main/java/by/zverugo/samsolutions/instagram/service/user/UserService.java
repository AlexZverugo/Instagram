package by.zverugo.samsolutions.instagram.service.user;

import by.zverugo.samsolutions.instagram.dto.CommentDTO;
import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.entity.User;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;
import java.util.Map;

public interface UserService {
    long saveUser(UserDTO userDTO) throws ConstraintViolationException;
    void deleteUser(long id);
    void updateUser(UserDTO userDTO);
    UserDTO getUserByLogin(String login);
    UserDTO getUserById(long id);
    List<UserDTO> getListOfUsers();
    Map<Long, String> getCommentSendersNames(List<CommentDTO> comments);
    Map<Long,String> getPostSendersUsernames(List<PostDTO> posts);

}
