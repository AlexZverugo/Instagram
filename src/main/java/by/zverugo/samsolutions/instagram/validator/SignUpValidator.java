package by.zverugo.samsolutions.instagram.validator;

import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class SignUpValidator implements Validator {

    private Pattern pattern;

    private final String LOGIN_PATTERN = "^[a-zA-Z0-9_.]{4,20}$";

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object user, Errors errors) {
        UserDTO userDTO = (UserDTO) user;

        if (checkValidChars(userDTO.getLogin(), errors)) {
            return;
        } else if (checkEmptyValue(userDTO, errors)) {
            return;
        } else {
            checkLoginRepeat(userDTO.getLogin(), errors);
            checkEqualPassword(userDTO.getPassword(), userDTO.getRepeatedPassword(), errors);
        }

    }

    private boolean checkValidChars(String login, Errors errors) {
        boolean error = false;
        pattern = Pattern.compile(LOGIN_PATTERN);
        Matcher matcher = pattern.matcher(login);

        if (!matcher.matches()) {
            errors.rejectValue("login", "validator.registration.loginillegalchars");
            error = true;
        }

        return error;
    }

    private void checkEqualPassword(String password, String repeatedPassword, Errors errors) {
        if (!password.equals(repeatedPassword)) {
            errors.rejectValue("password", "validator.registration.passwordsnotequal");
            errors.rejectValue("repeatedPassword", "validator.registration.passwordsnotequal");
        }
    }

    private void checkLoginRepeat(String login, Errors errors) {
        if (userService.getUserByLogin(login) != null) {
            errors.rejectValue("login", "validator.registration.loginalreadyexists");
        }
    }

    private boolean checkEmptyValue(UserDTO userDTO, Errors errors) {
        boolean error = false;
        if (!StringUtils.hasText(userDTO.getLogin())) {
            errors.rejectValue("login", "validator.registration.cannotbeempty");
            error = true;
        }
        if (!StringUtils.hasText(userDTO.getPassword())) {
            errors.rejectValue("password", "validator.registration.cannotbeempty");
            error = true;
        }
        if (!StringUtils.hasText(userDTO.getRepeatedPassword())) {
            errors.rejectValue("repeatedPassword", "validator.registration.cannotbeempty");
            error = true;
        }
        if (!StringUtils.hasText(userDTO.getEmail())) {
            errors.rejectValue("email", "validator.registration.cannotbeempty");
            error = true;
        }

        return error;
    }

    //TODO Admin delete
    //TODO Koval bugs (posts and comments)
}
