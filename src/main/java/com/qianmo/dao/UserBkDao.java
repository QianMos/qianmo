package com.qianmo.dao;

import com.qianmo.entity.MyParms;
import com.qianmo.entity.UserBk;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: QianMo
 * @Date: Create in 15:08 2020/4/5
 */
@Mapper
@Repository
public interface UserBkDao {
    List<UserBk> getAllBk(Integer page);

    Integer getCount();
    //获取用户所发帖子
    List<UserBk> getMyBk(MyParms myParms);
    //获取用户所发帖子总条数
    Integer getMyCount(String uid);
    //删除帖子
    void delBk(String bkid);
    //添加帖子
    void addBk(UserBk userBk);
    //搜索
    List<UserBk> searchBk(MyParms myParms);

    Integer searchCount(MyParms myParms);
}
