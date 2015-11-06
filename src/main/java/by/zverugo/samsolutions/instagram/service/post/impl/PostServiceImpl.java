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
        return postDao.savePost(post);
    }

    @Override
    @Transactional
    public void deletePost(PostDTO postDTO) {
        Post post = conversionService.convert(postDTO, Post.class);
        postDao.deletePost(post);
    }

    @Override
    @Transactional
    public void updatePost(PostDTO postDTO) {
        Post post = conversionService.convert(postDTO, Post.class);
        postDao.updatePost(post);
    }

    @Override
    @Transactional(readOnly = true)
    public PostDTO getPostDTO(long id) {
        return conversionService.convert(postDao.getPost(id), PostDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> getListOfPosts() {
        List<Post> posts = postDao.getListOfPosts();
        List<PostDTO> postDTOList = new ArrayList();

        for (Post post : posts) {
            postDTOList.add(conversionService.convert(post, PostDTO.class));
        }
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

        return postDTOList;
    }

}
