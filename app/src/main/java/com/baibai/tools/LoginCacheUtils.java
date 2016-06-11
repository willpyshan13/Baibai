package com.baibai.tools;

/**
 * Created by will on 16/6/10.
 */
public class LoginCacheUtils {
    private static final String TAG = "LoginCacheUtils";
    public static String TOKEN = "";
    public static String username;
    public static String nickName;

    public static boolean isLogin() {
        if (TOKEN.equals(""))
            return false;
        else
            return true;
    }
}
