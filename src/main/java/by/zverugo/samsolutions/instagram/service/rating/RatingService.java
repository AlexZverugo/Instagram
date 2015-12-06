package by.zverugo.samsolutions.instagram.service.rating;

import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.dto.RatingDTO;

import java.util.List;

public interface RatingService {
   long saveRating(RatingDTO ratingDTO);
   void deleteRating(long id);
   void updateRating(RatingDTO ratingDTO);
   RatingDTO getRatingById(long id);
   List<RatingDTO> getListOfRatings();
   PostDTO saveOrDeleteRating(PostDTO postDTO,RatingDTO ratingDTO);
   void setPostRatings(PostDTO postDTO);
}
