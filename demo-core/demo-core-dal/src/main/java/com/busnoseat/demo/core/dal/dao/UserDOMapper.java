package com.busnoseat.demo.core.dal.dao;

import com.busnoseat.common.pagination.Page;
import com.busnoseat.demo.core.dal.model.UserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * The type UserDOMapper.
 *
 * @author xubo
 * @Description:
 * @Date 2016/7/14
 */
public interface UserDOMapper {

    /**
     * 分页查询用户
     * @param record
     * @param page
     * @return
     */
    List<UserDO> queryUser(@Param("record") UserDO record, @Param("page") Page page);

    /**
     * 查询总数
     * @param record
     * @return
     */
    int count(@Param("record") UserDO record);

    /**
     * 更新用户
     * @param userDO
     * @return
     */
    int modify(UserDO userDO);
}