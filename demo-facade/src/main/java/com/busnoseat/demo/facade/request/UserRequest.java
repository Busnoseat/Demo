package com.busnoseat.demo.facade.request;

import com.busnoseat.common.facade.abs.BaseRequest;
import com.busnoseat.demo.facade.model.UserDTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The type UserRequest.
 *
 * @author xubo
 * @Description:
 * @Date 2016/7/14
 */
public class UserRequest extends BaseRequest {
    /** 用户ID */
    @NotNull(message="用户id不能为空")
    private Long userId;
    @NotBlank(message="用户姓名不能为空")
    /** 用户姓名  */
    private String userName;
    /** 每页大小 */
    private int pageSize = 10;
    /** 当前页数 */
    private int currPage = 1;
    /** lsit*/
    private List<UserDTO> list;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<UserDTO> getList() {
        return list;
    }

    public void setList(List<UserDTO> list) {
        this.list = list;
    }
}