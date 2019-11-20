package com.mobile.freeforfun.business.service;

import com.mobile.freeforfun.business.dto.UserDto;
import com.mobile.freeforfun.business.dto.UserDtoWithPicture;
import com.mobile.freeforfun.business.exceptions.BusinessException;
import com.mobile.freeforfun.persistence.model.User;

import java.sql.Blob;

public interface IUserService{
    UserDto login(String username, String password) throws BusinessException;
    Integer deletedAccount(String username) throws BusinessException;
    UserDto changePassword(String username, String oldPassword) throws BusinessException;
    UserDto saveUser(User user) throws BusinessException;
    UserDto updateUser(User user) throws BusinessException;
    UserDtoWithPicture uploadPictureToUser(Long userId, Blob picture);
}