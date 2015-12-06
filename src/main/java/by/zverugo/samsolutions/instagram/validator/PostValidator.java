package by.zverugo.samsolutions.instagram.validator;

import by.zverugo.samsolutions.instagram.dto.PostDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PostValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {return false;}

    @Override
    public void validate(Object post, Errors errors) {
        PostDTO postDTO = (PostDTO)post;
        checkEmptyPost(postDTO, errors);
    }

    private void checkEmptyPost(PostDTO postDTO, Errors errors) {
        String postContent = postDTO.getPostContent().trim();
        if(postDTO.getPicture().isEmpty() && StringUtils.isBlank(postContent)) {
            errors.rejectValue("postContent", "validator.post.emptypost");
        }
    }
}
