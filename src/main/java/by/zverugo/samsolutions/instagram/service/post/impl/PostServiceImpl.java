package by.zverugo.samsolutions.instagram.service.post.impl;

import by.zverugo.samsolutions.instagram.converter.post.PostDTOToPostConverter;
import by.zverugo.samsolutions.instagram.converter.post.PostToPostDTOConverter;
import by.zverugo.samsolutions.instagram.dao.post.PostDao;
import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.entity.Post;
import by.zverugo.samsolutions.instagram.service.post.PostService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    private PostDTOToPostConverter postDTOToPostConverter;

    @Autowired
    private PostToPostDTOConverter postToPostDTOConverter;

    @Override
    @Transactional
    public long savePost(PostDTO postDTO) {
        Post post;
        post = postDTOToPostConverter.convert(postDTO);
        return postDao.savePost(post);
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

    @Override
    public void saveFileResourceDir(PostDTO postDTO) {
        StringBuilder loggerInf = new StringBuilder();

        String imageName = postDTO.getPicture().getOriginalFilename();
        StringBuilder imageUrl = new StringBuilder();
        imageUrl.append(postDTO.getOwner()).append("/").append(postDTO.getId());

        StringBuilder dirPath = new StringBuilder();
        dirPath.append(messageSource.getMessage("post.resource.dir", null, Locale.ENGLISH)).append(imageUrl);
        File dir = new File(dirPath.toString());
        dir.mkdirs();

        StringBuilder picturePath = new StringBuilder();
        picturePath.append(dirPath).append("/").append(imageName);
        File picture = new File(picturePath.toString());

        try {
            postDTO.getPicture().transferTo(picture);
            postDTO.setPicturePath(imageUrl + "/" + imageName);
            LOGGER.info(loggerInf.append("Successful saving file:")
                    .append(imageName).append("; at folder:").append(dirPath));
        } catch (IOException e) {
            LOGGER.warn(loggerInf.append("Cannot save file:").append(imageName)
                    .append("; at folder:").append(dirPath));
        }
    }

}
