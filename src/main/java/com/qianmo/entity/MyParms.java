package com.qianmo.entity;

import java.io.Serializable;

/**
 * @Author: QianMo
 * @Date: Create in 15:37 2020/4/11
 */
public class MyParms implements Serializable {
    private String uid;
    private Integer page;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
