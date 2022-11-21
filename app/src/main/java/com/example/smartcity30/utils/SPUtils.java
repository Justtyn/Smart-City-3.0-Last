package com.example.smartcity30.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtils {
    private static final String PREFERENCE_NAME = "config";
    private static SharedPreferences sharedPreferences;
    public static final String IpAddress = "IpAddress";
    public static final String IpPort = "IpPort";
    public static final String isFirstEnter = "isFirstEnter";
    public static final String userName = "userName";
    public static final String passWord = "passWord";
    public static final String TOKEN = "TOKEN";


    public static void putInt(Context context, String key, int value) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public static int getInt(Context context, String key, int value) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getInt(key, value);
    }

    public static void putString(Context context, String key, String value) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key, String value) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getString(key, value);
    }

    public static void putBoolean(Context context, String key, Boolean value) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static Boolean getBoolean(Context context, String key, Boolean value) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(key, value);
    }

    public static void remove(Context context, String key) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().remove(key).apply();
    }

}
