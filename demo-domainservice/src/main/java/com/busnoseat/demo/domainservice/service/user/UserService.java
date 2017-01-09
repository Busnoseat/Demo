package com.busnoseat.demo.domainservice.service.user;

import com.busnoseat.common.pagination.Page;
import com.busnoseat.common.pagination.PaginationBean;
import com.busnoseat.demo.domain.model.User;

/**
 * The type UserService.
 *
 * @author xubo
 * @Description:
 * @Date 2016/7/14
 */
public interface UserService {
    /**
     * 分页查询用户信息
     * @param user
     * @return
     */
    public PaginationBean<User> queryUser(User user, Page page);

    /**
     * 更新用户
     * @param user
     */
    public void modifyUser(User user);
}