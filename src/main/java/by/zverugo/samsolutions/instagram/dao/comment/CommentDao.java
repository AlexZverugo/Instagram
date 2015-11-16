package by.zverugo.samsolutions.instagram.dao.comment;

import by.zverugo.samsolutions.instagram.entity.Comment;

import java.util.List;

public interface CommentDao {
    public void saveComment(Comment comment);
    public void deleteComment(Comment comment);
    public void updateComment(Comment comment);
    public Comment getComment(long id);
    public List<Comment> getListOfComments();
    public List<Comment> getListOfPostsByPostId(long id);
}
