package com.webboard.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Sorravit on 5/11/2559.
 */
@Entity
@Table(name = "reply")
public class ReplyVO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idReply;

    @NotNull
    @Column(nullable = false)
    private int postId;

    @NotNull
    @Column(nullable = false)
    private String message;

    public ReplyVO() {
    }

    public ReplyVO(int postId, String message) {
        this.postId = postId;
        this.message = message;
    }

    public int getIdReply() {
        return idReply;
    }

    public void setIdReply(int idReply) {
        this.idReply = idReply;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getmessage() {
        return message;
    }

    public void setmessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ReplyVO{" +
                "idReply=" + idReply +
                ", postId=" + postId +
                ", message='" + message + '\'' +
                '}';
    }
}

