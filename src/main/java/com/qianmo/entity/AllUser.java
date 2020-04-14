package com.qianmo.entity;

import java.io.Serializable;

/**
 * @Author: QianMo
 * @Date: Create in 14:57 2020/4/5
 */
//用户实体类
public class AllUser implements Serializable {
    private String userid;
    private String username;
    private String useraccount;
    private String userpassword;
    private String userphone;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }
}
