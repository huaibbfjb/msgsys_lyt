package com.msgsys.entity;

import com.google.gson.Gson;

/**
 * 作者：LiuYunTao
 * 日期: 15:34 2020/11/26
 * 描述：
 */
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String imgPath;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

    public User(Integer id, String username, String password, String email, String imgPath) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.imgPath = imgPath;
    }

    public User(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email, String imgPath) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.imgPath = imgPath;
    }

    public User() {
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
