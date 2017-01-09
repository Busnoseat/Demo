package com.busnoseat.demo.domainservice.repository.user;

import com.busnoseat.common.pagination.Page;
import com.busnoseat.common.pagination.PaginationBean;
import com.busnoseat.demo.domain.model.User;

/**
 * The type UserReposity.
 *
 * @author xubo
 * @Description:
 * @Date 2016/7/14
 */
public interface UserReposity {
    /**
     * 分页查询用户
     * @param user
     * @param page
     * @return
     */
    public PaginationBean<User> queryUser(User user, Page page);

    /**
     * 更新用户
     * @param user
     */
    public void modify(User user);
}