package com.mobile.freeforfun.business.service;
import com.google.common.hash.Hashing;
import com.mobile.freeforfun.business.dto.UserDto;
import com.mobile.freeforfun.business.dto.UserDtoWithPicture;
import com.mobile.freeforfun.business.exceptions.BusinessException;
import com.mobile.freeforfun.business.mapper.UserMapper;
import com.mobile.freeforfun.business.mapper.UserMapperImpl;
import com.mobile.freeforfun.persistence.repo.UserRepository;
import com.mobile.freeforfun.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;


@Service
@Transactional
public class UserService implements IUserService{

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapperImpl userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto login(String username, String password) throws BusinessException {
        String hashedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        User user = userRepository.findByUsernameAndPassword(username, hashedPassword);
        UserDto userDto;
        if(user != null){
            userDto =  userMapper.toDto(user);
            return userDto;
        }
        else
            throw new BusinessException("Invalid username or password!","fff-001");
    }

    @Override
    public Integer deletedAccount(String username) throws BusinessException{
        Integer deleted;
        deleted = userRepository.deleteByUsername(username);
        if(deleted != 0)
            return deleted;
        else
            throw new BusinessException("Account with this username does not exist. Account could't" +
                    " be deleted!","fff-002");
    }

    @Override
    public UserDto changePassword(String username, String newPassword) throws BusinessException {
        User userToUpdate = userRepository.findByUsername(username);
        if(userToUpdate != null) {
            String hashedNewPassword = Hashing.sha256().hashString(newPassword, StandardCharsets.UTF_8).toString();
            userToUpdate.setPassword(hashedNewPassword);
            userRepository.save(userToUpdate);
            return userMapper.toDto(userToUpdate);
        }
        else
            throw new BusinessException("Invalid username. Password could not be changed!","fff-002");
    }

    @Override
    public UserDto saveUser(User user) throws BusinessException {

        if(userRepository.findByUsername(user.getUsername())!= null)
            throw new BusinessException("Username already exists in the DB","fff-003");

        if(userRepository.findByEmail(user.getEmail()) != null)
            throw new BusinessException("Email already exists in the DB","fff-004");

        String hashedNewPassword = Hashing.sha256().hashString(user.getPassword(), StandardCharsets.UTF_8).toString();
        user.setPassword(hashedNewPassword);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateUser(User user){
        User userById = userRepository.getOne(user.getId());
        userById.setEmail(user.getEmail());
        String hashedNewPassword = Hashing.sha256().hashString(user.getPassword(), StandardCharsets.UTF_8).toString();
        userById.setPassword(hashedNewPassword);
        userById.setMobileNumber(user.getMobileNumber());
        userById.setLastName(user.getLastName());
        userById.setFirstName(user.getFirstName());
        userById.setUsername(user.getUsername());
        userRepository.save(userById);
        return userMapper.toDto(userById);
    }

    @Override
    public UserDtoWithPicture uploadPictureToUser(Long userId, Blob picture) {
        User userById = userRepository.getOne(userId);
        userById.setPicture(picture);
        userRepository.save(userById);
        return userMapper.toDtoPicture(userById);
    }
}
