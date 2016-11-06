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
    private int idPost;
    @Column(nullable = false)
    private int userId;

    @NotNull
    @Column(nullable = false)
    private String userName;
    @NotNull
    @Column(nullable = false)
    private String title;

    @Column(columnDefinition="varchar(1000)")
    private String data;
    @NotNull
    @Column(nullable = false)
    private Date postDate;
    @NotNull
    @Column(nullable = false)
    private int viewCount;

    public PostVO() {
    }

    public PostVO(int userId, String userName, String title, String data, Date postDate, int viewCount) {
        this.userId = userId;
        this.userName = userName;
        this.title = title;
        this.data = data;
        this.postDate = postDate;
        this.viewCount = viewCount;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    @Override
    public String toString() {
        return "PostVO{" +
                "idPost=" + idPost +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", title='" + title + '\'' +
                ", data='" + data + '\'' +
                ", postDate=" + postDate +
                ", viewCount=" + viewCount +
                '}';
    }
}
