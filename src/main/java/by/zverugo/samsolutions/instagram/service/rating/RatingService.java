package by.zverugo.samsolutions.instagram.service.rating;

import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.dto.RatingDTO;

import java.util.List;

public interface RatingService {
    public long saveRating(RatingDTO ratingDTO);
    public void deleteRating(long id);
    public void updateRating(RatingDTO ratingDTO);
    public RatingDTO getRatingById(long id);
    public List<RatingDTO> getListOfRatings();
    public PostDTO saveOrDeleteRating(PostDTO postDTO,RatingDTO ratingDTO);
}
