package by.zverugo.samsolutions.instagram.dao.comment;

import by.zverugo.samsolutions.instagram.entity.Comment;

import java.util.List;

/**
 * Created by alzv on 13.10.2015.
 */
public interface CommentDao {
    public void saveComment(Comment comment);
    public void deleteComment(Comment comment);
    public void updateComment(Comment comment);
    public Comment getComment(long id);
    public List<Comment> getListOfComments();
}
