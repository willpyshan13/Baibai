package com.baibai.tools;

/**
 * Created by will on 16/6/10.
 */
public class RequestRul {
    private static final String TAG = "RequestRul";

    private static final String BASE_REQUSET_PATH = "http://211.149.207.251:8080/eMallRM/webservice/";

    public static final String USERREGISTER = BASE_REQUSET_PATH + "user/register";
    public static final String USERLOGIN = BASE_REQUSET_PATH + "user/login";
    public static final String COMPLETEINFO = BASE_REQUSET_PATH + "user/completeInfo?token=";
    public static final String SYSTEMGETPHONECODE = BASE_REQUSET_PATH + "system/getPhoneCode";
    public static final String GETBANNERLIST = BASE_REQUSET_PATH +"goods/getBannerList";
    public static final String GETCLAZZLIST = BASE_REQUSET_PATH +"goods/getClazzList";
    public static final String GETGOODSPAGE = BASE_REQUSET_PATH +"goods/getGoodsPage";

    public static final String FORGETPWD = BASE_REQUSET_PATH + "user/forgetPwd";
    public static final String CHANGEPWD = BASE_REQUSET_PATH + "user/changePwd?token=";
    public static final String FEEDBACK = BASE_REQUSET_PATH + "user/feedback?token=";
    public static final String UPLOADAVATAR = BASE_REQUSET_PATH + "user/uploadAvatar?token=";

    public static final String GETUSERSCORE = BASE_REQUSET_PATH + "score/getUserScore?token=";



}
