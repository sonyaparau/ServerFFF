package com.mobile.freeforfun.business.restControllers;

import com.mobile.freeforfun.business.dto.UserDto;
import com.mobile.freeforfun.business.exceptions.BusinessException;
import com.mobile.freeforfun.business.service.UserService;
import com.mobile.freeforfun.business.utils.ApiEndpoints;
import com.mobile.freeforfun.business.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;
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
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity loginUser(@PathVariable("username") String username,
                                    @PathVariable("password") String password){
        try{
            UserValidator.validateUserLogin(username, password);
            userService.login(username, password);
            return new ResponseEntity<>("Successfully login!", HttpStatus.OK);
        } catch(BusinessException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
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
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity changePassword(@PathVariable("username") String username,
                                    @PathVariable("newPassword") String newPassword){
        try{
            UserValidator.validateChangedPassword(newPassword);
            userService.changePassword(username, newPassword);
            return new ResponseEntity<>("Password was successfully changed!", HttpStatus.OK);
        } catch(BusinessException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}