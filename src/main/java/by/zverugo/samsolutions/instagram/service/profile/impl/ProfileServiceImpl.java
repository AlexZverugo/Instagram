package by.zverugo.samsolutions.instagram.service.profile.impl;

import by.zverugo.samsolutions.instagram.dao.profile.ProfileDao;
import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.dto.ProfileDTO;
import by.zverugo.samsolutions.instagram.entity.Profile;
import by.zverugo.samsolutions.instagram.service.profile.ProfileService;
import by.zverugo.samsolutions.instagram.util.LoggerLocale;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
    public long saveProfile(ProfileDTO profileDTO) {
        Profile profile = conversionService.convert(profileDTO, Profile.class);
        long id = profileDao.saveProfile(profile);
        LOGGER.info(messageSource.getMessage("service.profile.save", new Object[]{profileDTO}, LoggerLocale.LOCALE));

        return id;
    }

    @Override
    @Transactional
    public void deleteProfile(long id) {
        profileDao.deleteProfile(id);
        LOGGER.info(messageSource.getMessage("service.profile.delete", new Object[]{id}, LoggerLocale.LOCALE));
    }

    @Override
    @Transactional
    public void updateProfile(ProfileDTO profileDTO) {
        Profile profile = conversionService.convert(profileDTO, Profile.class);
        profileDao.updateProfile(profile);
        LOGGER.info(messageSource.getMessage("service.profile.update", new Object[]{profileDTO}, LoggerLocale.LOCALE));
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileDTO getProfileById(long id) {
        ProfileDTO profileDTO = conversionService.convert(profileDao.getProfile(id), ProfileDTO.class);
        LOGGER.info(messageSource.getMessage("service.profile.getPostById", new Object[]{id, profileDTO}, LoggerLocale.LOCALE));

        return profileDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileDTO getProfileByUserId(long id) {
        ProfileDTO profileDTO = conversionService.convert(profileDao.getProfileByUserId(id), ProfileDTO.class);
        LOGGER.info(messageSource.getMessage("service.profile.getProfileDTOByUserId",
                new Object[]{id, profileDTO}, LoggerLocale.LOCALE));

        return profileDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfileDTO> getListOfProfiles() {
        List<Profile> profiles = profileDao.getListOfProfiles();
        List<ProfileDTO> profileDTOList = new ArrayList();

        for (Profile profile : profiles) {
            profileDTOList.add(conversionService.convert(profile, ProfileDTO.class));
        }
        LOGGER.info(messageSource.getMessage("service.profile.getList", new Object[]{profileDTOList}, LoggerLocale.LOCALE));

        return profileDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<Long, byte[]> getPostSendersProfiles(List<PostDTO> posts){
        Map<Long, byte[]> profiles = new HashMap<>();
        for (PostDTO post : posts) {
            profiles.put(post.getId(), getProfileByUserId(post.getSender()).getAvatar());
        }
        LOGGER.info(messageSource.getMessage("service.profile.getPostSendersProfiles",
                new Object[]{profiles}, LoggerLocale.LOCALE));

        return profiles;
    }
}
