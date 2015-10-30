package by.zverugo.samsolutions.instagram.converter.user;

import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDTOToUserConverter implements Converter<UserDTO, User> {
    @Override
    public User convert(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setLogin(userDTO.getLogin());
        user.setRole(userDTO.getRole().getRole());
        return user;
    }
}
