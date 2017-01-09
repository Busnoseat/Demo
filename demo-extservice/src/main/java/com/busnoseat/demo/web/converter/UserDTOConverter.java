package com.busnoseat.demo.web.converter;

import com.busnoseat.common.util.ConverterBeanUtil;
import com.busnoseat.demo.domain.model.User;
import com.busnoseat.demo.facade.model.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * The type UserDTOConverter.
 *
 * @author xubo
 * @Description:
 * @Date 2016/9/12
 */
public class UserDTOConverter {
    public static List<UserDTO> convert(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();
        ConverterBeanUtil.copyListBeanPropertiesToList(users, userDTOs, UserDTO.class);
        return userDTOs;
    }
}
