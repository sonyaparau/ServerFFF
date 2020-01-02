package com.mobile.freeforfun.business.service;
import com.google.common.hash.Hashing;
import com.mobile.freeforfun.business.dto.UserDto;
import com.mobile.freeforfun.business.dto.UserDtoWithPicture;
import com.mobile.freeforfun.business.exceptions.BusinessException;
import com.mobile.freeforfun.business.mapper.UserMapper;
import com.mobile.freeforfun.business.mapper.UserMapperImpl;
import com.mobile.freeforfun.business.utils.MailService;
import com.mobile.freeforfun.persistence.repo.UserRepository;
import com.mobile.freeforfun.persistence.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapperImpl userMapper) {
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
    public String changePassword(String username, String oldPassword, String newPassword) throws BusinessException {
        User userToUpdate = userRepository.findByUsername(username);
        String encryptedOldPasword = Hashing.sha256().hashString(oldPassword, StandardCharsets.UTF_8).toString();
        if(userToUpdate != null) {
            if(userToUpdate.getPassword().equals(encryptedOldPasword)) {
                String hashedNewPassword = Hashing.sha256().hashString(newPassword, StandardCharsets.UTF_8).toString();
                userToUpdate.setPassword(hashedNewPassword);
                userRepository.save(userToUpdate);
                return "Password has been successfully changed!";
            }
            else{
                return "The introduced old password is not correct! Password could not be changed!";
            }
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
        userById.setPassword(user.getPassword());
        userById.setMobileNumber(user.getMobileNumber());
        userById.setLastName(user.getLastName());
        userById.setFirstName(user.getFirstName());
        userById.setUsername(user.getUsername());
        userRepository.save(userById);
        return userMapper.toDto(userById);
    }

    @Override
    public String forgotPassword(String email) {
        User user = userRepository.findByEmail(email);
        StringBuilder message = new StringBuilder();
        int length = 6;
        boolean useLetters = true;
        boolean useNumbers = false;
        String newPassword = RandomStringUtils.random(length, useLetters, useNumbers);
        if (user != null) {
            String encryptedNewPassword = Hashing.sha256().hashString(newPassword, StandardCharsets.UTF_8).toString();
            String subject = "Recover your FreeForFun? password";
            message.append("Hello, ");
            message.append(user.getFirstName());
            message.append(" ");
            message.append(user.getLastName());
            message.append("! A new password has been generated for your account. ");
            message.append("Please try to log in now with the following credentials: \n");
            message.append("Username: ");
            message.append(user.getUsername());
            message.append("\n");
            message.append("Password: ");
            message.append(newPassword);
            message.append("\n");
            message.append("We recommend you to log in and to change immediately" +
                    " the new generated password for security reasons. If there are further problems" +
                    " don't hesitate to write us on freeForFunMobile@gmail.com.\n\n");
            message.append("Kind regards,\n");
            message.append("Free For Fun Team");
            try {
                MailService.sendMail(user.getEmail(), subject, message.toString());
                user.setPassword(encryptedNewPassword);
                userRepository.save(user);
                return "Email with the new password has been successfully sent!";
            } catch (MessagingException e) {
                return "Sorry, a problem has occured. Email has not been sent! Try again!";
            }
        }
        return "The email you entered is not subscribed to the application!";
    }
    public UserDtoWithPicture uploadPictureToUser(String username, Blob picture) throws BusinessException {
        User userById = userRepository.findByUsername(username);
        if(userById != null){
            userById.setPicture(picture);
            userRepository.save(userById);
            return userMapper.toDtoPicture(userById);
        }
        else
            throw new BusinessException("User with this username does not exists in the DB","fff-004");
    }
}
