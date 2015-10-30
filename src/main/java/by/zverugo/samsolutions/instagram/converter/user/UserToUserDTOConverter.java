package by.zverugo.samsolutions.instagram.converter.user;

import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import by.zverugo.samsolutions.instagram.util.enums.UserRoleEnum;

@Component
public class UserToUserDTOConverter implements Converter<User, UserDTO> {
    @Override
    public UserDTO convert(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        if (user.getRole().equals(UserRoleEnum.ADMIN.getRole())) {
            userDTO.setRole(UserRoleEnum.ADMIN);
        } else {
            userDTO.setRole(UserRoleEnum.USER);
        }

        return userDTO;
    }
}
