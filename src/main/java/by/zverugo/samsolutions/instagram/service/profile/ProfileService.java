package by.zverugo.samsolutions.instagram.service.profile;

import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.dto.ProfileDTO;

import java.util.List;
import java.util.Map;

public interface ProfileService {
    long saveProfile(ProfileDTO profileDTO);
    void deleteProfile(long id);
    void updateProfile(ProfileDTO profileDTO);
    ProfileDTO getProfileById(long id);
    ProfileDTO getProfileByUserId(long id);
    List<ProfileDTO> getListOfProfiles();
    Map<Long, byte[]> getPostSendersProfiles(List<PostDTO> posts);
}
