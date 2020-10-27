package com.cvte.taobaounion.utils;

/**
 * Created by user on 2020/10/26.
 */

public class UrlUtils {
    public static String createHomePagerUrl(int materialId, int page){
        return "discovery/"+materialId+"/"+page;
    }

    public static String getCoverPath(String pict_url) {
        return "https:"+pict_url;
    }
}
