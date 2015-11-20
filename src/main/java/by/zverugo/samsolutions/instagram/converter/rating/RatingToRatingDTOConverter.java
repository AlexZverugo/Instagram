package by.zverugo.samsolutions.instagram.converter.rating;

import by.zverugo.samsolutions.instagram.dto.RatingDTO;
import by.zverugo.samsolutions.instagram.entity.Rating;
import by.zverugo.samsolutions.instagram.util.enums.RatingTypeEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RatingToRatingDTOConverter implements Converter<Rating, RatingDTO> {
    @Override
    public RatingDTO convert(Rating rating) {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setRatingId(rating.getRatingId());
        ratingDTO.setPost(rating.getPost().getPostId());
        ratingDTO.setSender(rating.getSender().getId());

        if (RatingTypeEnum.LIKE.getType().equals(rating.getType())) {
            ratingDTO.setType(RatingTypeEnum.LIKE);
        } else {
            ratingDTO.setType(RatingTypeEnum.DISLIKE);
        }

        return ratingDTO;
    }
}
