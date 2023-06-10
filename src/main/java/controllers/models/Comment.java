package controllers.models;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComment")
    private long idComment;

    @ManyToOne
    @JoinColumn(name = "idPost")
    private Post idPost;

    @Column(name = "createTime")
    private Timestamp createTime;

    @Column(name = "content")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "idParent", referencedColumnName = "idComment")
    private Comment idParent;

    @OneToMany(mappedBy = "idComment")
    private List<Comment> comments;

    private String author;

    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment [idComment=" + idComment + ", createTime=" + createTime + ", comment="
                + comment + ", idParent=" + idParent + ", author: " + author;
    }

    public Comment(Post idPost, Timestamp createTime, String comment, Comment idParent) {
        this.idPost = idPost;
        this.createTime = createTime;
        this.comment = comment;
        this.idParent = idParent;
    }

    public Comment(Post idPost, Timestamp createTime, String comment, Comment idParent, List<Comment> comments,
            String author) {
        this.idPost = idPost;
        this.createTime = createTime;
        this.comment = comment;
        this.idParent = idParent;
        this.comments = comments;
        this.author = author;
    }

    public Comment(Post idPost, Timestamp createTime, String comment, Comment idParent, String author) {
        this.idPost = idPost;
        this.createTime = createTime;
        this.comment = comment;
        this.idParent = idParent;
        this.author = author;
    }

    public Comment(Post idPost, Timestamp createTime, String comment) {
        this.idPost = idPost;
        this.createTime = createTime;
        this.comment = comment;
        // this.idParent = null;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getIdComment() {
        return idComment;
    }

    public void setIdComment(long idComment) {
        this.idComment = idComment;
    }

    public Post getIdPost() {
        return idPost;
    }

    public void setIdPost(Post idPost) {
        this.idPost = idPost;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Comment getIdParent() {
        return idParent;
    }

    public void setIdParent(Comment idParent) {
        this.idParent = idParent;
    }

}
