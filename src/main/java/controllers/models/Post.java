package controllers.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPost")
    private long idPost;

    @ManyToOne
    @JoinColumn(name = "idCategory")
    private Category idCategory;

    @Column(name = "createTime")
    private Timestamp createTime;

    @Column(name = "content")
    private String content;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "idPost")
    private List<Comment> comments;

    public Post() {
    }

    public Post(long idPost, Category idCategory, Timestamp createTime, String content, String title) {
        this.idPost = idPost;
        this.idCategory = idCategory;
        this.createTime = createTime;
        this.content = content;
        this.title = title;
    }

    public Post(Category idCategory, Timestamp createTime, String content, String title) {
        this.idCategory = idCategory;
        this.createTime = createTime;
        this.content = content;
        this.title = title;
    }

    public long getIdPost() {
        return idPost;
    }

    public void setIdPost(long idPost) {
        this.idPost = idPost;
    }

    public Category getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Category idCategory) {
        this.idCategory = idCategory;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post [idPost=" + idPost + ", idCategory=" + idCategory + ", createTime=" + createTime + ", content="
                + content + "]";
    }

}
