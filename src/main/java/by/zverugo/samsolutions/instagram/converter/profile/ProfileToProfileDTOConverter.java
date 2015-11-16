package by.zverugo.samsolutions.instagram.converter.profile;

import by.zverugo.samsolutions.instagram.dto.ProfileDTO;
import by.zverugo.samsolutions.instagram.entity.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfileToProfileDTOConverter implements Converter<Profile,ProfileDTO> {
    @Override
    public ProfileDTO convert(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(profile.getId());
        profileDTO.setFirstName(profile.getFirstName());
        profileDTO.setSurname(profile.getSurname());
        profileDTO.setSecondName(profile.getSecondName());
        profileDTO.setCountry(profile.getCountry());
        profileDTO.setCity(profile.getCity());
        profileDTO.setSex(profile.getSex());
        profileDTO.setRelationshipStatus(profile.getRelationshipStatus());
        profileDTO.setAvatar(profile.getAvatar());
        profileDTO.setBirthday(profile.getBirthday());
        profileDTO.setUser(profile.getUser().getId());

        return profileDTO;
    }
}
