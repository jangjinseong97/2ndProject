package com.green.jobdone.common;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PicUrlMaker {
    private final MyFileUtils myFileUtils;

    public static String makePicUrl(long businessId, String picName) {
        return String.format("/pic/business/%d/%s", businessId, picName);
    }

    public static String makePicUrl(long cafeId, long menuId, String picName) {
        return String.format("/pic/cafe/%d/menu/%d/%s", cafeId, menuId, picName);
    }

    public static String makePicUrlLogo(long businessId, String picName) {
        return String.format("/business/%d/logo/%s", businessId, picName);
    }


}
