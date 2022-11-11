package com.maria.pastelhub.prefference;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class Pref {

    // shared preferences keys
    private static final int ModePrivate = 0;

    private static final String TOKEN = "TOKEN";
    private static final String LOGIN_STATUS = "LOGIN_STATUS";
    private static final String FCM_TOKEN = "FCM_TOKEN";
    private static final String MUSICVAL = "MUSICVAL";
    public static final String REVIEW_RATING = "REVIEW_RATING";
    public static final String REVIEW_DESC = "REVIEW_DESC";
    private static final String SPLASH_SCREEN_LOADING = "SPLASH_SCREEN_LOADING";
    private static final String PREFERENCES_NAME = "SharedPrefManager";

    private SharedPreferences sharedPreferences;

    /**
     * constructor
     *
     * @param context
     */
    public Pref(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    /**
     * method to set string prefs
     *
     * @param token
     * @param fcmtoken
     */
    public void setLogin(String token, String fcmtoken, int login_status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN, token);
        editor.putString(FCM_TOKEN, fcmtoken);
        editor.putInt(LOGIN_STATUS, 1);
        editor.apply();
    }

    public void setPreference(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void setPreference(String key, float value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public void setPreference(String key, Object value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(value);
        editor.putString(key, json);
        editor.apply();
    }

    public String getPreference(String key) {
        return sharedPreferences.getString(key, null);
    }

    public float getPreferenceFloat(String key) {
        return sharedPreferences.getFloat(key, (float) 0);
    }

    /**
     * method to set string prefs
     *
     * @param volume
     */
    public void setMusicVal(String volume) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MUSICVAL, volume);
        editor.apply();
    }

    public String getMusicval() {
        return sharedPreferences.getString(MUSICVAL, "1");//null
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN, null);
    }

    public String getFcmToken() {
        return sharedPreferences.getString(FCM_TOKEN, null);
    }

    public int getLoginStatus() {
        return sharedPreferences.getInt(LOGIN_STATUS, 0);
    }

    /**
     * method to clear all prefs
     */
    public void clearPrefs() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void setSplashScreen(String val) {
        sharedPreferences.edit().putString(SPLASH_SCREEN_LOADING, val).apply();
    }

    public String getSplashScreen() {
        return sharedPreferences.getString(SPLASH_SCREEN_LOADING, "0");
    }

    public void setLesson(String val) {
        sharedPreferences.edit().putString("lesson", val).apply();
    }

    public String getLesson() {
        return sharedPreferences.getString("lesson", "0");
    }

}

