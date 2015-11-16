package by.zverugo.samsolutions.instagram.service.profile;

import by.zverugo.samsolutions.instagram.dto.ProfileDTO;

import java.util.List;

public interface ProfileService {
    public long saveProfileDTO(ProfileDTO profileDTO);
    public void deleteProfileDTO(long id);
    public void updateProfileDTO(ProfileDTO profileDTO);
    public ProfileDTO getProfileDTOById(long id);
    public ProfileDTO getProfileDTOByUserId(long id);
    public List<ProfileDTO> getListOfProfileDTOs();
}
