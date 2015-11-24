package by.zverugo.samsolutions.instagram.converter.profile;

import by.zverugo.samsolutions.instagram.dto.ProfileDTO;
import by.zverugo.samsolutions.instagram.entity.Profile;
import by.zverugo.samsolutions.instagram.util.InstagramConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfileToProfileDTOConverter implements Converter<Profile,ProfileDTO> {
    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

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
        profileDTO.setAvatar(profile.getAvatar());
        profileDTO.setBirthday(profile.getBirthday());
        profileDTO.setUser(profile.getUser().getId());

        LOGGER.info(messageSource.getMessage("converter.convert",
                new Object[]{"Profile", "ProfileDTO", profile, profileDTO}, InstagramConstants.LOGGER_LOCALE));

        return profileDTO;
    }
}
