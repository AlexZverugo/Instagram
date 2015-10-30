package by.zverugo.samsolutions.instagram.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "user_role")
    private String role;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "owner")
    private List<Post> ownPosts;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "sender")
    private List<Post> sentPosts;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "sender")
    private List<Comment> sentComment;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public List<Post> getOwnPosts() {
        return ownPosts;
    }

    public void setOwnPosts(List<Post> ownPosts) {
        this.ownPosts = ownPosts;
    }


    public List<Post> getSentPosts() {
        return sentPosts;
    }

    public void setSentPosts(List<Post> sentPosts) {
        this.sentPosts = sentPosts;
    }

    public List<Comment> getSentComment() {
        return sentComment;
    }

    public void setSentComment(List<Comment> sentComment) {
        this.sentComment = sentComment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
