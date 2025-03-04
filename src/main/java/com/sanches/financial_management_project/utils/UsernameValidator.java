package com.sanches.financial_management_project.utils;

public class UsernameValidator {

    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_.]+$";

    public static boolean isValidUsername(String username) {
        return username.matches(USERNAME_PATTERN);
    }
}
