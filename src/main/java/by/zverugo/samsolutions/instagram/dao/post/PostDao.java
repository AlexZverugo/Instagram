package by.zverugo.samsolutions.instagram.dao.post;

import by.zverugo.samsolutions.instagram.entity.Post;

import java.util.List;

public interface PostDao {
    long savePost(Post post);
    void deletePost(long id);
    void updatePost(Post post);
    Post getPost(long id);
    List<Post> getListOfPosts();
    List<Post> getListOfPostsByIdOfOwner(long id);
}
