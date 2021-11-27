package com.bawei.usercenter.entity;

public class UserEntity {


    /**
     * id : 1
     * username : sample string 2
     * pwd : sample string 3
     * sex : sample string 4
     * birthday : sample string 5
     */

    private int id;
    private String username;
    private String pwd;
    private String sex;
    private String birthday;

    public UserEntity() {
    }

    public UserEntity(String username, String pwd) {
        this.username = username;
        this.pwd = pwd;
    }

    public UserEntity(int id, String username, String pwd, String sex, String birthday) {
        this.id = id;
        this.username = username;
        this.pwd = pwd;
        this.sex = sex;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
