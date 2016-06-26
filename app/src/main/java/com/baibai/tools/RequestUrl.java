package com.baibai.tools;

/**
 * Created by will on 16/6/10.
 */
public class RequestUrl {
    private static final String TAG = "RequestUrl";

    private static final String BASE_REQUSET_PATH = "http://211.149.207.251:8080/eMallRM/webservice/";

    public static final String USERREGISTER = BASE_REQUSET_PATH + "user/register";
    public static final String USERLOGIN = BASE_REQUSET_PATH + "user/login";
    public static final String COMPLETEINFO = BASE_REQUSET_PATH + "user/completeInfo?token=";
    public static final String SYSTEMGETPHONECODE = BASE_REQUSET_PATH + "system/getPhoneCode";
    public static final String GETBANNERLIST = BASE_REQUSET_PATH +"goods/getBannerList";
    public static final String GETCLAZZLIST = BASE_REQUSET_PATH +"goods/getClazzList";
    public static final String GETGOODSPAGE = BASE_REQUSET_PATH +"goods/getGoodsPage";
    public static final String GETGOODSDETAIL = BASE_REQUSET_PATH +"goods/getGoodsDetail";
    public static final String GETADDRLIST = BASE_REQUSET_PATH +"address/getAddrList?token=";
    public static final String ADDADDR = BASE_REQUSET_PATH +"address/addAddr?token=";
    public static final String DELADDR = BASE_REQUSET_PATH +"address/delAddr?token=";

    public static final String GETNEARESTLIST = BASE_REQUSET_PATH +"market/getNearestList";
    public static final String GETEQUIPLIST = BASE_REQUSET_PATH +"market/getEquipList";
    public static final String GETMARKETLIST = BASE_REQUSET_PATH +"discover/getMarketList";
    public static final String GETGOODSCLAZZLIST = BASE_REQUSET_PATH +"discover/getGoodsClazzList";

    public static final String GETATTENDPAGE = BASE_REQUSET_PATH +"attend/getAttendPage?token=";

    public static final String FORGETPWD = BASE_REQUSET_PATH + "user/forgetPwd";
    public static final String CHANGEPWD = BASE_REQUSET_PATH + "user/changePwd?token=";
    public static final String FEEDBACK = BASE_REQUSET_PATH + "user/feedback?token=";
    public static final String UPLOADAVATAR = BASE_REQUSET_PATH + "user/uploadAvatar?token=";

    public static final String GETUSERSCORE = BASE_REQUSET_PATH + "score/getUserScore?token=";



}
