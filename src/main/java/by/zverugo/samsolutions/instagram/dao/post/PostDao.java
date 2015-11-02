package by.zverugo.samsolutions.instagram.dao.post;

import by.zverugo.samsolutions.instagram.entity.Post;

import java.util.List;

/**
 * Created by alzv on 13.10.2015.
 */
public interface PostDao {
    public void savePost(Post post);
    public void deletePost(Post post);
    public void updatePost(Post post);
    public Post getPost(long id);
    public List<Post> getListOfPosts();
    public List<Post> getListOfPostsByIdOfOwner(long id);
}
