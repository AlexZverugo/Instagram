package by.zverugo.samsolutions.instagram.dao.profile;

import by.zverugo.samsolutions.instagram.entity.Profile;

import java.util.List;

public interface ProfileDao {
    public long saveProfile(Profile profile);
    public void deleteProfile(long id);
    public void updateProfile(Profile profile);
    public Profile getProfile(long id);
    public Profile getProfileByUserId(long id);
    public List<Profile> getListOfProfiles();
}
