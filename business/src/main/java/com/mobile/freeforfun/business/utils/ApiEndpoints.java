package com.mobile.freeforfun.business.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiEndpoints {

    //USER-Related Paths
    public static final String APPLICATION_NAME = "/free_for_fun";
    public static final String LOGIN = "/login/{username}/{password}";
    public static final String DELETE_ACCOUNT = "/deleteAccount/{username}";
    public static final String CHANGE_PASSWORD = "/changePassword/{username}/{newPassword}";
    public static final String REGISTER_NOW = "/register";
    public static final String UPDATE_USER_PROFILE="/update";
    public static final String UPLOAD_USER_PICTURE="/upload/{userId}";
    public static final String FORGOT_PASSWORD = "/forgot_password/";

}