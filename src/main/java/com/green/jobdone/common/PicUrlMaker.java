package com.green.jobdone.common;

public class PicUrlMaker {
    public static String makePicUrl(long businessId, String picName) {
        return String.format("/pic/business/%d/%s", businessId, picName);
    }

    public static String makePicUrl(long cafeId, long menuId, String picName) {
        return String.format("/pic/cafe/%d/menu/%d/%s", cafeId, menuId, picName);
    }

    public static String makePicUrlChat(long roomId, long chatId, String picName ) {
        return String.format("/pic/room/%d/chat/%d/%s", roomId, chatId, picName);
    }

}
