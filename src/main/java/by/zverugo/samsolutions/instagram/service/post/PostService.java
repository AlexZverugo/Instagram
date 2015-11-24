package by.zverugo.samsolutions.instagram.service.post;

import by.zverugo.samsolutions.instagram.dto.PostDTO;
import by.zverugo.samsolutions.instagram.entity.Post;

import java.util.List;

public interface PostService {
   long savePost(PostDTO postDTO);
   void deletePost(long id);
   void updatePost(PostDTO postDTO);
   PostDTO getPost(long id);
   List<PostDTO> getListOfPosts();
   List<PostDTO> getListOfPostsByIdOfOwner(long id);
   List<PostDTO> getReversedListOfPostsByIdOfOwner(long id);
}
