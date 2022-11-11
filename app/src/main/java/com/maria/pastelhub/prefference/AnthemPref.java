package com.maria.pastelhub.prefference;

import android.content.Context;
import android.content.SharedPreferences;

public class AnthemPref {

    private static final String PREFERENCES_NAME = "AnthemPref";

    private SharedPreferences sharedPreferences;

    public AnthemPref(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void setEnglishAnthemView(boolean state) {
        sharedPreferences.edit().putBoolean("englishAnthemView", state).apply();
    }

    public boolean getEnglishAnthemView() {
        return sharedPreferences.getBoolean("englishAnthemView", false);
    }

    public void setTamilAnthemView(boolean state) {
        sharedPreferences.edit().putBoolean("tamilAnthemView", state).apply();
    }

    public boolean getTamilAnthemView() {
        return sharedPreferences.getBoolean("tamilAnthemView", false);
    }

    public void setEnglishAnthemCount(int count) {
        sharedPreferences.edit().putInt("englishAnthem", count).apply();
    }

    public int getEnglishAnthemCount() {
        return sharedPreferences.getInt("englishAnthem", 0);
    }

    public void setTamilAnthemCount(int count) {
        sharedPreferences.edit().putInt("tamilAnthem", count).apply();
    }

    public int getTamilAnthemCount() {
        return sharedPreferences.getInt("tamilAnthem", 0);
    }
}
