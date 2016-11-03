package com.webboard.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sorravit on 3/11/2559.
 */
@Entity
@Table(name = "posts")
public class PostVO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idPost;
//    @Column(nullable = false)
//    private UserVO user;
    @NotNull
    @Column(nullable = false)
    private String title;
    @NotNull
    @Column(nullable = false)
    private String data;
    @NotNull
    @Column(nullable = false)
    private Date postDate;
    @NotNull
    @Column(nullable = false)
    private int viewCount;

    public long getIdPost() {
        return idPost;
    }

    public void setIdPost(long idPost) {
        this.idPost = idPost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}
