package com.busnoseat.demo.web.rest.user;

import com.busnoseat.demo.domain.model.User;
import com.busnoseat.demo.domainservice.service.user.UserService;
import com.busnoseat.demo.facade.intel.UserFacade;
import com.busnoseat.demo.facade.request.UserRequest;
import com.busnoseat.demo.facade.response.UserResponse;
import com.busnoseat.demo.web.converter.UserDTOConverter;
import com.busnoseat.demo.web.converter.ValidationUtil;
import com.busnoseat.common.code.CommonRspCode;
import com.busnoseat.common.facade.abs.BaseResponse;
import com.busnoseat.common.pagination.Page;
import com.busnoseat.common.pagination.PaginationBean;
import com.busnoseat.common.util.ConverterBeanUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * The type UserController.
 *
 * @author xubo
 * @Description:
 * @Date 2016/7/14
 */
@Controller
public class UserController implements UserFacade {
    @Resource
    private UserService userService;

    /**@see UserFacade#checkParam(UserRequest) */
    public @ResponseBody BaseResponse checkParam(@RequestBody UserRequest request) {
        BaseResponse response=new BaseResponse();
        Set<ConstraintViolation<UserRequest>> set= ValidationUtil.validEntity(request);
        if(set!=null&&set.size()>0){
            ConstraintViolation valitation= set.iterator().next();
            response.setRespCode(CommonRspCode.VERIFY_FAIL.getCode());
            response.setRespMessage(valitation.getMessage());
            return response;
        }
        response.setRespCode(CommonRspCode.SUCCESS.getCode());
        response.setRespMessage(CommonRspCode.SUCCESS.getName());
        return response;
    }

    /**@see UserFacade#queryUser(UserRequest) */
    public @ResponseBody UserResponse queryUser(UserRequest request) {
        UserResponse response = new UserResponse();
        //step2:组装领域对象
        User user=new User();
        user.setUserName(request.getUserName());
        //step3:调用服务
        PaginationBean<User> paginationBean=userService.queryUser(user,new Page(request.getPageSize(),request.getCurrPage()));

        response.setRespCode(CommonRspCode.SUCCESS.getCode());
        response.setRespMessage(CommonRspCode.SUCCESS.getName());
        response.setTotalPage(paginationBean.getTotalPage());
        response.setTotalRecord(paginationBean.getTotalRecords());
        response.setUserList(UserDTOConverter.convert(paginationBean.getPageList()));
        return response;
    }



    /**@see UserFacade#modifyUser(UserRequest)  */
    public @ResponseBody  BaseResponse modifyUser(@RequestBody UserRequest request) {
        BaseResponse response=new BaseResponse();
        //step1: 参数检验
        Assert.isTrue(request.getUserId()!=null ,"用户id不能为空");
        Assert.isTrue(request.getUserName()!=null&&StringUtils.isNotBlank(request.getUserName()) ,"用户姓名不能为空");
        //step2: 调用服务
        User user=new User();
        ConverterBeanUtil.copyBeanProperties(request,user);
        userService.modifyUser(user);
        //step3: 返回结果
        response.setRespCode(CommonRspCode.SUCCESS.getCode());
        response.setRespMessage(CommonRspCode.SUCCESS.getName());
        return response;
    }
}