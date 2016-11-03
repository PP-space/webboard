package com.webboard.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Sorravit on 3/11/2559.
 */
@Entity
@Table(name = "users")
public class UserVO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    @Column(nullable = false)
    private String email;
    @NotNull
    @Column(nullable = false)
    private String username;
    @NotNull
    @Column(nullable = false)
    private String password;

    public UserVO() {
    }

    public UserVO(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
