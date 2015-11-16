package by.zverugo.samsolutions.instagram.converter.profile;

import by.zverugo.samsolutions.instagram.dto.ProfileDTO;
import by.zverugo.samsolutions.instagram.entity.Profile;
import by.zverugo.samsolutions.instagram.entity.User;
import by.zverugo.samsolutions.instagram.util.enums.PicturesByteArrayEnum;
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
        profile.setRelationshipStatus(profileDTO.getRelationshipStatus());
        profile.setAvatar(profileDTO.getAvatar());

//        if (profileDTO.getAvatar() == null) {
//            profile.setAvatar(PicturesByteArrayEnum.DEFAULT_AVATAR.getByte());
//        } else {
//            profile.setAvatar(profileDTO.getAvatar());
//        }
        profile.setBirthday(profileDTO.getBirthday());

        User user = new User();
        user.setId(profileDTO.getUser());
        profile.setUser(user);

        return profile;
    }
}
