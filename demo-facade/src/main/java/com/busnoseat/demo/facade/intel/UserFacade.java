package com.busnoseat.demo.facade.intel;

import com.busnoseat.common.facade.abs.BaseResponse;
import com.busnoseat.demo.facade.request.UserRequest;
import com.busnoseat.demo.facade.response.UserResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The type UserFacade.
 *
 * @author xubo
 * @Description:
 * @Date 2016/7/14
 */
public interface UserFacade {
    /**
     * 分页查询用户
     * @param request
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    UserResponse queryUser(UserRequest request);

    /**
     * 检验参数
     * @param request
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    BaseResponse checkParam(UserRequest request);

    /**
     * 修改用户
     * @param request
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    BaseResponse modifyUser(UserRequest request);

}