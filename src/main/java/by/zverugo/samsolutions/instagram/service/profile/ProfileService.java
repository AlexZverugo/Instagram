package by.zverugo.samsolutions.instagram.service.profile;

import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.dto.ProfileDTO;

import java.util.List;
import java.util.Map;

public interface ProfileService {
    public long saveProfile(ProfileDTO profileDTO);
    public void deleteProfile(long id);
    public void updateProfile(ProfileDTO profileDTO);
    public ProfileDTO getProfileById(long id);
    public ProfileDTO getProfileByUserId(long id);
    public List<ProfileDTO> getListOfProfiles();
    public Map<Long, byte[]> getPostSendersProfiles(List<PostDTO> posts);
}
