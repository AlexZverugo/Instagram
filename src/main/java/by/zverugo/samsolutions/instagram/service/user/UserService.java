package by.zverugo.samsolutions.instagram.service.user;

import by.zverugo.samsolutions.instagram.dto.CommentDTO;
import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.entity.User;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;
import java.util.Map;

public interface UserService {
    public long saveUser(UserDTO userDTO) throws ConstraintViolationException;
    public void deleteUser(UserDTO userDTO);
    public void updateUser(UserDTO userDTO);
    public UserDTO getUserByLogin(String login);
    public UserDTO getUserById(long id);
    public List<UserDTO> getListOfUsers();
    public Map<Long, String> getCommentSendersNames(List<CommentDTO> comments);
    public Map<Long,String> getPostSendersUsernames(List<PostDTO> posts);

}
