package com.green.jobdone.common;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PicUrlMaker {
    private final MyFileUtils myFileUtils;

    public static String makePicUrl(long businessId, String picName) {
        return String.format("/pic/business/%d/%s", businessId, picName);
    }
    public static String makePicUrlPortfolio(long businessId, long portfolioId, String picName) {
        return String.format("/pic/business/%d/portfolio/%d/pics/%s", businessId,portfolioId, picName);
    }
    public static String makePicUrlBusiness(long businessId, String picName) {
        return String.format("/pic/business/%d/pics/%s", businessId, picName);
    }


    public static String makePicUserUrl(long userId, String picName) {
        return String.format("/pic/user/%d/%s", userId, picName);
    }

    public static String makePicUrlLogo(long businessId, String picName) {
        return String.format("/pic/business/%d/logo/%s", businessId, picName);
    }
    public static String makePicUrlPaper(long businessId, String picName) {
        return String.format("/pic/business/%d/paper/%s", businessId, picName);
    }

    public static String makePicUrlWithUrl(String url){
        return String.format("/pic/%s", url);
    }


    public static String makePicUrlChat(long roomId, long chatId, String picName ) {
        return String.format("/pic/room/%d/chat/%d/%s", roomId, chatId, picName);
    }

}
