package by.zverugo.samsolutions.instagram.controller;

import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.dto.RatingDTO;
import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.jsonview.Views;
import by.zverugo.samsolutions.instagram.service.AuthorizationService;
import by.zverugo.samsolutions.instagram.service.post.PostService;
import by.zverugo.samsolutions.instagram.service.rating.RatingService;
import by.zverugo.samsolutions.instagram.util.enums.RatingTypeEnum;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(value = "/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private PostService postService;

    @JsonView(Views.Rating.class)
    @RequestMapping(value = "/setLike", method = RequestMethod.POST)
    public @ResponseBody PostDTO setLike(@RequestBody RatingDTO rating) {
        return setRating(RatingTypeEnum.LIKE, rating);
    }

    @JsonView(Views.Rating.class)
    @RequestMapping(value = "/setDislike", method = RequestMethod.POST)
    public @ResponseBody PostDTO setDislike(@RequestBody RatingDTO rating) {
        return setRating(RatingTypeEnum.DISLIKE, rating);
    }

    private PostDTO setRating(RatingTypeEnum ratingTypeEnum, RatingDTO rating) {
        UserDTO authUser = authorizationService.getAuthUser();
        rating.setSender(authUser.getUserId());
        rating.setType(ratingTypeEnum);
        PostDTO post = postService.getPost(rating.getPost());
        post = ratingService.saveOrDeleteRating(post, rating);
        postService.updatePost(post);

        return post;
    }
}
