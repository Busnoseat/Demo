package com.busnoseat.demo.core.dal.model;

/**
 * The type UserDO.
 *
 * @author xubo
 * @Description:
 * @Date 2016/7/14
 */
public class UserDO {
    private Long userId;
    private String userName;

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
}