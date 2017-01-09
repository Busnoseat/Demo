package com.busnoseat.demo.domainservice.repository.user.impl;

import com.busnoseat.common.code.CommonRspCode;
import com.busnoseat.common.exception.RepositoryException;
import com.busnoseat.common.pagination.Page;
import com.busnoseat.common.pagination.PaginationBean;
import com.busnoseat.demo.core.dal.dao.UserDOMapper;
import com.busnoseat.demo.core.dal.model.UserDO;
import com.busnoseat.demo.domain.model.User;
import com.busnoseat.demo.domainservice.converter.UserConverter;
import com.busnoseat.demo.domainservice.repository.user.UserReposity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type UserReposityImpl.
 *
 * @author xubo
 * @Description:
 * @Date 2016/7/14
 */
@Service
public class UserReposityImpl implements UserReposity {
    @Resource
    private UserDOMapper userDOMapper;

    /**@see UserReposity#queryUser(User, Page) */
    public PaginationBean<User> queryUser(User user, Page page) {
        UserDO userDO = UserConverter.convert(user);
        List<UserDO> resultList = userDOMapper.queryUser(userDO, page);
        int count = userDOMapper.count(userDO);
        PaginationBean<User> paginationBean = new PaginationBean<User>(page, count);
        paginationBean.setPageList(UserConverter.convert(resultList));
        return paginationBean;
    }

    /**@see UserReposity#modify(User) */
    public void modify(User user) {
        int i = 0;
        try {
            i = userDOMapper.modify(UserConverter.convert(user));
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryException(CommonRspCode.DATA_REPEAT.getCode(), "修改的用户存在 无法修改");
        }
        if(i==0){
            throw new RepositoryException(CommonRspCode.DATA_NOT_EXIST.getCode(),"修改的用户不存在");
        }
    }
}