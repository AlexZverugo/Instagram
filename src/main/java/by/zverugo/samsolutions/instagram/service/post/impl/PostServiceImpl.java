package by.zverugo.samsolutions.instagram.service.post.impl;

import by.zverugo.samsolutions.instagram.dao.post.PostDao;
import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.entity.Post;
import by.zverugo.samsolutions.instagram.service.post.PostService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service("postService")
public class PostServiceImpl implements PostService {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PostDao postDao;

    @Autowired
    private ConversionService conversionService;

    @Override
    @Transactional
    public long savePost(PostDTO postDTO) {
        Post post = conversionService.convert(postDTO, Post.class);
        LOGGER.info(messageSource.getMessage("service.post.save", new Object[]{postDTO}, Locale.ENGLISH));

        return postDao.savePost(post);
    }

    @Override
    @Transactional
    public void deletePost(long id) {
//        Post post = conversionService.convert(postDTO, Post.class);
        postDao.deletePost(id);
        LOGGER.info(messageSource.getMessage("service.post.delete", new Object[]{id}, Locale.ENGLISH));
    }

    @Override
    @Transactional
    public void updatePost(PostDTO postDTO) {
        Post post = conversionService.convert(postDTO, Post.class);
        postDao.updatePost(post);
        LOGGER.info(messageSource.getMessage("service.post.update", new Object[]{postDTO}, Locale.ENGLISH));
    }

    @Override
    @Transactional(readOnly = true)
    public PostDTO getPost(long id) {
        PostDTO postDTO = conversionService.convert(postDao.getPost(id), PostDTO.class);
        LOGGER.info(messageSource.getMessage("service.post.getPostById", new Object[]{id, postDTO}, Locale.ENGLISH));
        return postDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> getListOfPosts() {
        List<Post> posts = postDao.getListOfPosts();
        List<PostDTO> postDTOList = new ArrayList();

        for (Post post : posts) {
            postDTOList.add(conversionService.convert(post, PostDTO.class));
        }
        LOGGER.info(messageSource.getMessage("service.post.getList", new Object[]{postDTOList}, Locale.ENGLISH));

        return postDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> getListOfPostsByIdOfOwner(long id) {
        List<Post> posts = postDao.getListOfPostsByIdOfOwner(id);
        List<PostDTO> postDTOList = new ArrayList();

        for (Post post : posts) {
            postDTOList.add(conversionService.convert(post, PostDTO.class));
        }
        LOGGER.info(messageSource.getMessage("service.post.getListByIdOfOwner",
                new Object[]{id, postDTOList}, Locale.ENGLISH));

        return postDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> getReversedListOfPostsByIdOfOwner(long id) {
        List<Post> posts = postDao.getListOfPostsByIdOfOwner(id);
        List<PostDTO> postDTOList = new ArrayList();

        for (Post post : posts) {
            postDTOList.add(conversionService.convert(post, PostDTO.class));
        }

        Collections.reverse(postDTOList);
        LOGGER.info(messageSource.getMessage("service.post.getReversedListByIdOfOwner",
                new Object[]{id, postDTOList}, Locale.ENGLISH));

        return postDTOList;
    }

    @Override
    public void encodePostContent(List<PostDTO> posts) {
        for (PostDTO post : posts) {
            String encodedStr = post.getPostContent().replaceAll("\n", "%0A").replaceAll("\r", "%0D");
            post.setPostContent(encodedStr);
        }
    }

}
