package com.lcw.screenbrightnesscontrol.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by acre on 2015/8/1.
 */
public class SharePerfenceUtil {
    private final static String PER_NAME = "light";
    private static SharedPreferences spf;
    private static SharePerfenceUtil util;
    private SharePerfenceUtil(Context context){
        spf = context.getSharedPreferences(PER_NAME,Context.MODE_PRIVATE);
    }
    public static SharePerfenceUtil getInstance(Context context){
        if (util ==null)
            util = new SharePerfenceUtil(context);
        return util;
    }
    public static void putInt(String name,int value){
        SharedPreferences.Editor e = spf.edit();
        e.putInt(name,value);
        e.commit();
    }
    public static int getInt(String name){
        return spf.getInt(name,1);
    }
}
