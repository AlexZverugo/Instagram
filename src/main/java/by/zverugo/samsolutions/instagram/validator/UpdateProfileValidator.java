package by.zverugo.samsolutions.instagram.validator;

import by.zverugo.samsolutions.instagram.dto.ProfileDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component
public class UpdateProfileValidator {

    public ProfileDTO checkCorrectData(ProfileDTO profile) throws IOException {
        if (profile.getPicture().getBytes().length > 0) {
            profile.setAvatar(profile.getPicture().getBytes());
        }
        profile.setFirstName(profile.getFirstName().trim());
        profile.setSurname(profile.getSurname().trim());
        profile.setSecondName(profile.getSecondName().trim());
        profile.setCity(profile.getCity().trim());
        profile.setCountry(profile.getCountry().trim());

        if(StringUtils.isEmpty(profile.getFirstName())) {
            profile.setFirstName(null);
        }
        if(StringUtils.isEmpty(profile.getSurname())) {
            profile.setSurname(null);
        }
        if(StringUtils.isEmpty(profile.getSecondName())) {
            profile.setSecondName(null);
        }
        if(StringUtils.isEmpty(profile.getCountry())) {
            profile.setCountry(null);
        }
        if(StringUtils.isEmpty(profile.getCity())) {
            profile.setCity(null);
        }
        return profile;
    }
}
