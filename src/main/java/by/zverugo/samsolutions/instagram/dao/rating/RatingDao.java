package by.zverugo.samsolutions.instagram.dao.rating;

import by.zverugo.samsolutions.instagram.entity.Rating;

import java.util.List;

public interface RatingDao {
    public long saveRating(Rating rating);
    public void deleteRating(long id);
    public void updateRating(Rating rating);
    public Rating getRating(long id);
    public List<Rating> getListOfRatings();
    public Rating getRatingBySenderAndPostId(long sender, long postId);
}
