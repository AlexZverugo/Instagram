package by.zverugo.samsolutions.instagram.validator;

import by.zverugo.samsolutions.instagram.dto.ProfileDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ProfileValidator implements Validator {
    private final String FULL_NAME_PATTERN = "^[a-zA-Zа-яА-Я]{0,35}$";

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProfileDTO profileDTO = (ProfileDTO) target;
        checkFullNameChars(profileDTO, errors);
    }

    private void checkFullNameChars(ProfileDTO profileDTO, Errors errors) {
        Pattern pattern = Pattern.compile(FULL_NAME_PATTERN);

        Matcher firstNameMatcher = pattern.matcher(profileDTO.getFirstName());
        if (!firstNameMatcher.matches()) {
            errors.rejectValue("firstName", "validator.profile.firstnamelegalchars");
        }

        Matcher surnameMatcher = pattern.matcher(profileDTO.getSurname());
        if (!surnameMatcher.matches()) {
            errors.rejectValue("surname", "validator.profile.surnamelegalchars");
        }

        Matcher secondNameMatcher = pattern.matcher(profileDTO.getSecondName());
        if (!secondNameMatcher.matches()) {
            errors.rejectValue("secondName", "validator.profile.secondnamelegalchars");
        }
    }
}
