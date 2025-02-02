package com.green.jobdone.common;

public class PicUrlMaker {
    public static String makePicUrl(long businessId, String picName) {
        return String.format("/pic/business/%d/%s", businessId, picName);
    }


    public static String makePicUserUrl(long userId, String picName) {
        return String.format("/pic/user/%d/%s", userId, picName);
    }



    public static String makePicUrlLogo(long businessId, String picName) {
        return String.format("/business/%d/logo/%s", businessId, picName);
    }

}
