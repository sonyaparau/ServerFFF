package com.mobile.freeforfun.business.validators;

import com.mobile.freeforfun.business.exceptions.BusinessException;

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

    public static void validateChangedPassword(String password) throws BusinessException{
        if(!validatePassword(password)){
            throw new BusinessException("Password must have more than 3 characters.", "fff- validation002");
        }
    }
}