package com.example.mycould.utils;

public class IsNull {
    public static Boolean isNull(String s) {
        Boolean s_null = (s == null) || s.equals("");
        if (s_null) {
            return true;
        }
        return false;
    }
}
