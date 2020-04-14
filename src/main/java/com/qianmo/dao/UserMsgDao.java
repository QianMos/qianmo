package com.qianmo.dao;

import com.qianmo.entity.UserMsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: QianMo
 * @Date: Create in 12:32 2020/4/13
 */
@Mapper
@Repository
public interface UserMsgDao {
    //添加留言
    void setMsg(@Param("username") String username,@Param("msg") String msg);
    //流加载留言
    List<UserMsg> getAllMsg(Integer page);
    //留言总条数
    Integer getCount();
}
