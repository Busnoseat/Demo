package com.busnoseat.demo.domainservice.service.user.impl;

import com.busnoseat.common.pagination.Page;
import com.busnoseat.common.pagination.PaginationBean;
import com.busnoseat.demo.domain.model.User;
import com.busnoseat.demo.domainservice.repository.user.UserReposity;
import com.busnoseat.demo.domainservice.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * The type UserServiceImpl.
 *
 * @author xubo
 * @Description:
 * @Date 2016/7/14
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserReposity userReposity;

    /**@see UserService#queryUser(User, Page) */
    public PaginationBean<User> queryUser(User user, Page page) {
        return  userReposity.queryUser(user,page);
    }

    /**@see UserService#modifyUser(User) */
    public void modifyUser(User user) {
         userReposity.modify(user);
    }
}