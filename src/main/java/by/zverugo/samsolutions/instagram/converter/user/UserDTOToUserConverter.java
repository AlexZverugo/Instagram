package by.zverugo.samsolutions.instagram.converter.user;

import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class UserDTOToUserConverter implements Converter<UserDTO, User> {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Override
    public User convert(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setLogin(userDTO.getLogin());
        user.setRole(userDTO.getRole().getRole());

        LOGGER.info(messageSource.getMessage("converter.convert",
                new Object[]{"UserDTO", "User", userDTO, user}, Locale.ENGLISH));

        return user;
    }
}
