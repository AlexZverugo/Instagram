package by.zverugo.samsolutions.instagram.validator;

import by.zverugo.samsolutions.instagram.dto.ProfileDTO;
import org.springframework.stereotype.Component;

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

        if(profile.getFirstName().equals("")) {
            profile.setFirstName(null);
        }
        if(profile.getSurname().equals("")) {
            profile.setSurname(null);
        }
        if(profile.getSecondName().equals("")) {
            profile.setSecondName(null);
        }
        if(profile.getCountry().equals("")) {
            profile.setCountry(null);
        }
        if(profile.getCity().equals("")) {
            profile.setCity(null);
        }
        return profile;
    }
}
