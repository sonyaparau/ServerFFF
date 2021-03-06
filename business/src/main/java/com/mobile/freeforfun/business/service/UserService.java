package com.mobile.freeforfun.business.service;

import com.mobile.freeforfun.business.dto.UserDto;
import com.mobile.freeforfun.business.dto.UserDtoWithPicture;
import com.mobile.freeforfun.business.exceptions.BusinessException;
import com.mobile.freeforfun.persistence.model.User;

import java.sql.Blob;

public interface UserService {
    UserDto login(String username, String password) throws BusinessException;
    Integer deletedAccount(String username) throws BusinessException;
    String changePassword(String username, String oldPassword, String newPassword) throws BusinessException;
    UserDto saveUser(User user) throws BusinessException;
    UserDto updateUser(User user) throws BusinessException;
    String forgotPassword(String username);
    UserDtoWithPicture uploadPictureToUser(String username, Blob picture);
}
