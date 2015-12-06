package by.zverugo.samsolutions.instagram.dao.rating;

import by.zverugo.samsolutions.instagram.entity.Rating;
import org.hibernate.SessionFactory;

import java.util.List;

public interface RatingDao {
     long saveRating(Rating rating);
     void deleteRating(long id);
     void updateRating(Rating rating);
     Rating getRating(long id);
     List<Rating> getListOfRatings();
     Rating getRatingBySenderAndPostId(long sender, long postId);
     long getRatingCount(long postId, String type);
}
