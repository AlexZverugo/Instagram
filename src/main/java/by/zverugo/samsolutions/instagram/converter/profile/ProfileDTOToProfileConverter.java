package by.zverugo.samsolutions.instagram.converter.profile;

import by.zverugo.samsolutions.instagram.dto.ProfileDTO;
import by.zverugo.samsolutions.instagram.entity.Profile;
import by.zverugo.samsolutions.instagram.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfileDTOToProfileConverter implements Converter<ProfileDTO, Profile> {
    @Override
    public Profile convert(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        profile.setId(profileDTO.getId());
        profile.setFirstName(profileDTO.getFirstName());
        profile.setSurname(profileDTO.getSurname());
        profile.setSecondName(profileDTO.getSecondName());
        profile.setCountry(profileDTO.getCountry());
        profile.setCity(profileDTO.getCity());
        profile.setSex(profileDTO.getSex());

        convertPostImage(profileDTO, profile);
        profile.setBirthday(profileDTO.getBirthday());

        User user = new User();
        user.setId(profileDTO.getUser());
        profile.setUser(user);

        return profile;
    }

    private void convertPostImage(ProfileDTO profileDTO, Profile profile) {
        if (profileDTO.getAvatar() == null || profileDTO.getAvatar().length <= 0) {
            profile.setAvatar(null);
        } else {
            profile.setAvatar(profileDTO.getAvatar());
        }
    }

}
