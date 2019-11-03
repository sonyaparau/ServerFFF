package com.mobile.freeforfun.business.service;

import com.mobile.freeforfun.business.dto.UserDto;
import com.mobile.freeforfun.business.exceptions.BusinessException;

public interface IUserService{
    UserDto login(String username, String password) throws BusinessException;
    Integer deletedAccount(String username) throws BusinessException;
    UserDto changePassword(String username, String oldPassword) throws BusinessException;
}