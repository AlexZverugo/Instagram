package by.zverugo.samsolutions.instagram.converter.user;

import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.entity.User;
import by.zverugo.samsolutions.instagram.util.InstagramConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import by.zverugo.samsolutions.instagram.util.enums.UserRoleEnum;

@Component
public class UserToUserDTOConverter implements Converter<User, UserDTO> {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Override
    public UserDTO convert(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setEnable(user.isEnable());
        if (user.getRole().equals(UserRoleEnum.ADMIN.getRole())) {
            userDTO.setRole(UserRoleEnum.ADMIN);
        } else {
            userDTO.setRole(UserRoleEnum.USER);
        }

        LOGGER.info(messageSource.getMessage("converter.convert",
                new Object[]{"User", "UserDTO", user, userDTO}, InstagramConstants.LOGGER_LOCALE));

        return userDTO;
    }
}
