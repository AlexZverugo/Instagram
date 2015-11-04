package by.zverugo.samsolutions.instagram.service.post;

import by.zverugo.samsolutions.instagram.dto.PostDTO;

import java.util.List;

/**
 * Created by alzv on 13.10.2015.
 */
public interface PostService {
    public long savePost(PostDTO postDTO);
    public void deletePost(PostDTO postDTO);
    public void updatePost(PostDTO postDTO);
    public PostDTO getPost(long id);
    public List<PostDTO> getListOfPosts();
    public List<PostDTO> getListOfPostsByIdOfOwner(long id);
    public List<PostDTO> getReversedListOfPostsByIdOfOwner(long id);
    public void saveFileResourceDir(PostDTO postDTO);

}
