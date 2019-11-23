package com.mobile.freeforfun.business.validators;

import com.mobile.freeforfun.business.exceptions.BusinessException;
import com.mobile.freeforfun.persistence.model.User;

public class UserValidator {

    /**
     * Sa contina doar litere mici/mari,cifre de la 1-9, _ si .
     * */
    static boolean validateUsername(String username){
        return username.matches("[a-zA-Z0-9-_.]*");
    }

    static boolean validateNoSpace(String username){
        return !username.contains("\\s");
    }

    static boolean validateEmail(String email){
        return email.matches("^[a-zA-Z0-9-_.]*(@gmail|@yahoo)\\.com$");
    }
    static boolean validateROPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^(004|\\+4)?07[0-9]{8}$");
    }
    static boolean validateName(String name){
        return name.matches("^[A-Z][a-zA-Z]{0,30}[- ]?" +
                "[a-zA-Z]{0,30}[a-z]$");
    }
    /**
     * Sa contina mai mult de 3 caractere.
     * */
    static boolean validatePassword(String password){
        return password.length() > 3;
    }

    public static void validateUserLogin(String username, String password) throws BusinessException {
        if (!validateUsername(username)){
            throw new BusinessException("Username should only contain letters, digits, _ and .", "fff - validation001");
        }
        if(!validatePassword(password)){
            throw new BusinessException("Password must have more than 3 characters.", "fff- validation002");
        }
        if(!validateNoSpace(username)){
            throw new BusinessException("Username cannot contain any space.", "fff- validation003");
        }
    }

    public static void validateRegister(User user) throws BusinessException{
        if (!validateUsername(user.getUsername())){
            throw new BusinessException("Username should only contain letters, digits, _ and .", "fff - validation001");
        }
        if(!validatePassword(user.getPassword())){
            throw new BusinessException("Password must have more than 3 characters.", "fff- validation002");
        }
        if(!validateNoSpace(user.getUsername())){
            throw new BusinessException("Username cannot contain any space.", "fff- validation003");
        }
        if(!validateEmail(user.getEmail())){
            throw  new BusinessException("Email is not valid.","fff-validation004");
        }
        if(!validateROPhoneNumber(user.getMobileNumber())){
            throw new BusinessException("Ro Phone Number is not valid.","fff-validation005");
        }
        if(!validateName(user.getFirstName())){
            throw new BusinessException("First name is not valid","fff-validation006");
        }
        if(!validateName(user.getLastName())){
            throw new BusinessException("Last name is not valid","fff-validation007");
        }

    }
    public static void validateChangedPassword(String password) throws BusinessException{
        if(!validatePassword(password)){
            throw new BusinessException("Password must have more than 3 characters.", "fff- validation002");
        }
    }

    public static void validateForgotPasword(String email) throws BusinessException {
        if (!validateEmail(email)) {
            throw new BusinessException("Email is not valid.", "fff-validation004");
        }
    }
}
