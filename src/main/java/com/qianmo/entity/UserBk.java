package com.qianmo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: QianMo
 * @Date: Create in 14:59 2020/4/5
 */
//文章实体类
public class UserBk implements Serializable {
    private String userbkid;
    private String userid;
    private String username;
    private String bktitle;
    private String bktext;
    private Integer bkopen;
    private Date bkcreatetime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserbkid() {
        return userbkid;
    }

    public void setUserbkid(String userbkid) {
        this.userbkid = userbkid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBktitle() {
        return bktitle;
    }

    public void setBktitle(String bktitle) {
        this.bktitle = bktitle;
    }

    public String getBktext() {
        return bktext;
    }

    public void setBktext(String bktext) {
        this.bktext = bktext;
    }

    public Integer getBkopen() {
        return bkopen;
    }

    public void setBkopen(Integer bkopen) {
        this.bkopen = bkopen;
    }

    public Date getBkcreatetime() {
        return bkcreatetime;
    }

    public void setBkcreatetime(Date bkcreatetime) {
        this.bkcreatetime = bkcreatetime;
    }
}
