package com.qianmo.services;

import com.qianmo.dao.UserMsgDao;
import com.qianmo.entity.UserMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: QianMo
 * @Date: Create in 12:32 2020/4/13
 */
@Service
@Transactional
public class UserMsgService {
    @Autowired
    private UserMsgDao userMsgDao;

    //添加留言
    public void setMsg(String username,String msg){
        userMsgDao.setMsg(username,msg);
    }
    //流加载留言
    public List<UserMsg> getAllMsg(Integer page){
        return userMsgDao.getAllMsg(page);
    }
    //留言总条数
    public Integer getCount(){
        return userMsgDao.getCount();
    }
}
