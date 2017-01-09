package com.busnoseat.demo.facade.response;

import com.busnoseat.common.facade.abs.BaseResponse;
import com.busnoseat.demo.facade.model.UserDTO;

import java.util.List;

/**
 * The type UserResponse.
 *
 * @author xubo
 * @Description:
 * @Date 2016/7/14
 */
public class UserResponse extends BaseResponse {

    private List<UserDTO> userList;
    private Integer totalRecord;
    private Integer totalPage;

    public List<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDTO> userList) {
        this.userList = userList;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}