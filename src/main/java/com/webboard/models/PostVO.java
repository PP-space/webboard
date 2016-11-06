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
    private int id_post;
    @Column(nullable = false)
    private int user_id;

    @NotNull
    @Column(nullable = false)
    private String user_name;
    @NotNull
    @Column(nullable = false)
    private String title;

    @Column(columnDefinition="varchar(1000)")
    private String data;
    @NotNull
    @Column(nullable = false)
    private Date post_date;
    @NotNull
    @Column(nullable = false)
    private int view_count;

    public PostVO() {
    }

    public PostVO(int user_id, String user_name, String title, String data, Date post_date, int view_count) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.title = title;
        this.data = data;
        this.post_date = post_date;
        this.view_count = view_count;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int userId) {
        this.user_id = userId;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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

    public Date getPost_date() {
        return post_date;
    }

    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    @Override
    public String toString() {
        return "PostVO{" +
                "id_post=" + id_post +
                ", userId=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", title='" + title + '\'' +
                ", data='" + data + '\'' +
                ", post_date=" + post_date +
                ", view_count=" + view_count +
                '}';
    }
}
