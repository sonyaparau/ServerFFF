package com.mobile.freeforfun.business.service;


import com.google.common.hash.Hashing;
import com.mobile.freeforfun.business.dto.UserDto;
import com.mobile.freeforfun.business.exceptions.BusinessException;
import com.mobile.freeforfun.business.mapper.UserMapper;
import com.mobile.freeforfun.persistence.repo.UserRepository;
import com.mobile.freeforfun.persistence.model.User;
import org.hibernate.hql.spi.id.AbstractMultiTableBulkIdStrategyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;


@Service
@Transactional
public class UserService implements IUserService{

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto login(String username, String password) throws BusinessException {
        User user;
        String hashedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        user = userRepository.findByUsernameAndPassword(username, hashedPassword);
        if(user != null)
            return userMapper.toDto(user);
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
}