package com.mobile.freeforfun.business.restControllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mobile.freeforfun.business.dto.UserDto;
import com.mobile.freeforfun.business.exceptions.BusinessException;
import com.mobile.freeforfun.business.service.UserService;
import com.mobile.freeforfun.business.utils.ApiEndpoints;
import com.mobile.freeforfun.business.validators.UserValidator;
import com.mobile.freeforfun.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin
@RequestMapping(ApiEndpoints.APPLICATION_NAME)
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = ApiEndpoints.LOGIN,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity loginUser(@PathVariable("username") String username,
                                    @PathVariable("password") String password){
        Gson gson = new GsonBuilder().create();
        try{
            UserValidator.validateUserLogin(username, password);
            UserDto loggedUser = userService.login(username, password);
            String response = gson.toJson(loggedUser);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(BusinessException exception){
            String response = gson.toJson(exception.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    @PostMapping(value = ApiEndpoints.DELETE_ACCOUNT,
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity deleteUserAccount(@PathVariable("username") String username){
        try{
            userService.deletedAccount(username);
            return new ResponseEntity<>("Account was successfully deleted!", HttpStatus.OK);
        } catch(BusinessException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(value = ApiEndpoints.CHANGE_PASSWORD,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity changePassword(@PathVariable("username") String username,
                                    @PathVariable("oldPassword") String oldPassword,
                                    @PathVariable("newPassword") String newPassword){
        try{
            UserValidator.validateChangedPassword(oldPassword);
            UserValidator.validateChangedPassword(newPassword);
            String result = userService.changePassword(username, oldPassword, newPassword);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(BusinessException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.CREATED);
        }
    }
    @PostMapping(value = ApiEndpoints.REGISTER_NOW,
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody User user){
        try{
            UserValidator.validateRegister(user);
            userService.saveUser(user);
            return new ResponseEntity<>("User successfully registered!", HttpStatus.OK);
        } catch(BusinessException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
    @PostMapping(value = ApiEndpoints.UPDATE_USER_PROFILE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody User user){
        try{
            UserValidator.validateRegister(user);
            userService.updateUser(user);
            return new ResponseEntity<>("User profile successfully updated!", HttpStatus.OK);
        } catch(BusinessException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(value = ApiEndpoints.FORGOT_PASSWORD,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity forgotPassword(@PathVariable("email") String email){
        try{
            UserValidator.validateForgotPasword(email);
            String forgotPasswordMessage = userService.forgotPassword(email);
            return new ResponseEntity<>(forgotPasswordMessage, HttpStatus.OK);
        } catch(BusinessException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.CREATED);
        }
    }
}
