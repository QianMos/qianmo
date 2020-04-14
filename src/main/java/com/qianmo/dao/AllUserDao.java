package com.qianmo.dao;

import com.qianmo.entity.AllUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: QianMo
 * @Date: Create in 15:08 2020/4/5
 */
@Mapper
@Repository
public interface AllUserDao {

    AllUser getUserByAccount(String account);

    void addUser(AllUser user);
}
