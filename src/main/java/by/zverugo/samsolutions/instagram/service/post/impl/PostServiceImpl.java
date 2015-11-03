package by.zverugo.samsolutions.instagram.service.post.impl;

import by.zverugo.samsolutions.instagram.converter.post.PostDTOToPostConverter;
import by.zverugo.samsolutions.instagram.converter.post.PostToPostDTOConverter;
import by.zverugo.samsolutions.instagram.dao.post.PostDao;
import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import by.zverugo.samsolutions.instagram.service.post.PostService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("postService")
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private PostDTOToPostConverter postDTOToPostConverter;

    @Autowired
    private PostToPostDTOConverter postToPostDTOConverter;

    @Override
    @Transactional
    public void savePost(PostDTO postDTO) {
        Post post;
        post = postDTOToPostConverter.convert(postDTO);
        postDao.savePost(post);
    }

    @Override
    @Transactional
    public void deletePost(PostDTO postDTO) {
        Post post;
        post = postDTOToPostConverter.convert(postDTO);
        postDao.deletePost(post);
    }

    @Override
    @Transactional
    public void updatePost(PostDTO postDTO) {
        Post post;
        post = postDTOToPostConverter.convert(postDTO);
        postDao.updatePost(post);
    }

    @Override
    @Transactional(readOnly = true)
    public PostDTO getPost(long id) {
        return postToPostDTOConverter.convert(postDao.getPost(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> getListOfPosts() {
        List<Post> posts = postDao.getListOfPosts();
        List<PostDTO> postDTOList = new ArrayList();

        for (Post post : posts) {
            postDTOList.add(postToPostDTOConverter.convert(post));
        }
        return postDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> getListOfPostsByIdOfOwner(long id) {
        List<Post> posts = postDao.getListOfPostsByIdOfOwner(id);
        List<PostDTO> postDTOList = new ArrayList();

        for (Post post : posts) {
            postDTOList.add(postToPostDTOConverter.convert(post));
        }

        return postDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> getReversedListOfPostsByIdOfOwner(long id) {
        List<Post> posts = postDao.getListOfPostsByIdOfOwner(id);
        List<PostDTO> postDTOList = new ArrayList();

        for (Post post : posts) {
            postDTOList.add(postToPostDTOConverter.convert(post));
        }

        Collections.reverse(postDTOList);

        return postDTOList;
    }
}
