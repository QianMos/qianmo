package com.qianmo.services;

import com.qianmo.dao.UserBkDao;
import com.qianmo.entity.MyParms;
import com.qianmo.entity.UserBk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: QianMo
 * @Date: Create in 14:50 2020/4/7
 */
@Service
@Transactional
public class UserBkService {
    @Autowired
    private UserBkDao userBkDao;

    public List<UserBk> getAllBk(Integer page){
        return userBkDao.getAllBk(page);
    }
    public Integer getCount(){
        return userBkDao.getCount();
    }

    public List<UserBk> getMyBk(MyParms myParms){
        return userBkDao.getMyBk(myParms);
    }
    public Integer getMyCount(String uid){
        return userBkDao.getMyCount(uid);
    }
    public void delBk(String bkid){
        userBkDao.delBk(bkid);
    }
    //添加帖子
    public void addBk(UserBk userBk){
        userBkDao.addBk(userBk);
    }
    //搜索
    public List<UserBk> searchBk(MyParms myParms){
        return userBkDao.searchBk(myParms);
    }
    public Integer searchCount(MyParms myParms){
        return userBkDao.searchCount(myParms);
    }
}
