package com.jone.springbootshiro.model;

/**
 * @author Jone
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020年09月14日 00:33
 */
public class User {

    private Integer id;
    private String name;
    private String password;
    private String perms;
    public String getPerms() {
        return perms;
    }
    public void setPerms(String perms) {
        this.perms = perms;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
