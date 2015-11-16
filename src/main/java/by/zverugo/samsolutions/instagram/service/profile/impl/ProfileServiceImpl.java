package by.zverugo.samsolutions.instagram.service.profile.impl;

import by.zverugo.samsolutions.instagram.dao.profile.ProfileDao;
import by.zverugo.samsolutions.instagram.dto.ProfileDTO;
import by.zverugo.samsolutions.instagram.entity.Profile;
import by.zverugo.samsolutions.instagram.service.profile.ProfileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ProfileDao profileDao;

    @Autowired
    private ConversionService conversionService;

    @Override
    @Transactional
    public long saveProfileDTO(ProfileDTO profileDTO) {
        Profile profile = conversionService.convert(profileDTO, Profile.class);
        long id = profileDao.saveProfile(profile);
        LOGGER.info(messageSource.getMessage("service.profile.save", new Object[]{profileDTO}, Locale.ENGLISH));

        return id;
    }

    @Override
    @Transactional
    public void deleteProfileDTO(long id) {
        profileDao.deleteProfile(id);
        LOGGER.info(messageSource.getMessage("service.profile.delete", new Object[]{id}, Locale.ENGLISH));
    }

    @Override
    @Transactional
    public void updateProfileDTO(ProfileDTO profileDTO) {
        Profile profile = conversionService.convert(profileDTO, Profile.class);
        profileDao.updateProfile(profile);
        LOGGER.info(messageSource.getMessage("service.profile.update", new Object[]{profileDTO}, Locale.ENGLISH));
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileDTO getProfileDTOById(long id) {
        ProfileDTO profileDTO = conversionService.convert(profileDao.getProfile(id), ProfileDTO.class);
        LOGGER.info(messageSource.getMessage("service.profile.getPostById", new Object[]{id, profileDTO}, Locale.ENGLISH));

        return profileDTO;
    }

    @Override
    public ProfileDTO getProfileDTOByUserId(long id) {
        ProfileDTO profileDTO = conversionService.convert(profileDao.getProfileByUserId(id), ProfileDTO.class);
        LOGGER.info(messageSource.getMessage("service.profile.getProfileDTOByUserId",
                new Object[]{id, profileDTO}, Locale.ENGLISH));

        return profileDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfileDTO> getListOfProfileDTOs() {
        List<Profile> profiles = profileDao.getListOfProfiles();
        List<ProfileDTO> profileDTOList = new ArrayList();

        for (Profile profile : profiles) {
            profileDTOList.add(conversionService.convert(profile, ProfileDTO.class));
        }
        LOGGER.info(messageSource.getMessage("service.profile.getList", new Object[]{profileDTOList}, Locale.ENGLISH));

        return profileDTOList;
    }
}
