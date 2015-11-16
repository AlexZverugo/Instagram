package by.zverugo.samsolutions.instagram.dao.post;

import by.zverugo.samsolutions.instagram.entity.Post;

import java.util.List;

public interface PostDao {
    public long savePost(Post post);
    public void deletePost(long id);
    public void updatePost(Post post);
    public Post getPost(long id);
    public List<Post> getListOfPosts();
    public List<Post> getListOfPostsByIdOfOwner(long id);
}
