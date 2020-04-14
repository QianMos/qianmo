package com.qianmo.entity;

import java.io.Serializable;

/**
 * @Author: QianMo
 * @Date: Create in 12:19 2020/4/13
 */
//留言实体类
public class UserMsg implements Serializable {
    private String usremid;
    private String username;
    private String usertext;

    public String getUsremid() {
        return usremid;
    }

    public void setUsremid(String usremid) {
        this.usremid = usremid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertext() {
        return usertext;
    }

    public void setUsertext(String usertext) {
        this.usertext = usertext;
    }
}
