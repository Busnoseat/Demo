package com.busnoseat.demo.domainservice.converter;

import com.busnoseat.common.util.ConverterBeanUtil;
import com.busnoseat.demo.core.dal.model.UserDO;
import com.busnoseat.demo.domain.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * The type UserConverter.
 *
 * @author xubo
 * @Description:
 * @Date 2016/9/12
 */
public class UserConverter {
    public static UserDO convert(User user) {
        UserDO userDO = new UserDO();
        ConverterBeanUtil.copyBeanProperties(user, userDO);
        return userDO;
    }

    public static List<User> convert(List<UserDO> userDOs) {
        List<User> users = new ArrayList<User>();
        users = ConverterBeanUtil.copyListBeanPropertiesToList(userDOs, users, User.class);
        return users;
    }
}
