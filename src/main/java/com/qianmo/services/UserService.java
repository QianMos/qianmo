package com.qianmo.services;

import com.qianmo.dao.AllUserDao;
import com.qianmo.entity.AllUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: QianMo
 * @Date: Create in 14:50 2020/4/7
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private AllUserDao allUserDao;

    public AllUser login(String account){
        return allUserDao.getUserByAccount(account);
    }

    public void addUser(AllUser user) {
        allUserDao.addUser(user);
    }
}
