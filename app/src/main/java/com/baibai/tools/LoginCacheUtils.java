package com.baibai.tools;

/**
 * Created by will on 16/6/10.
 */
public class LoginCacheUtils {
    private static final String TAG = "LoginCacheUtils";
    public static String TOKEN = "";
    public static String userPhone;
    public static String nickName;
    public static String userGender;
    public static String userScore;

    public static boolean isLogin() {
        if (TOKEN.equals(""))
            return false;
        else
            return true;
    }

    public static String getGender() {
        if (userGender.equals("1")) {
            return "男";
        } else {
            return "女";
        }
    }
}
