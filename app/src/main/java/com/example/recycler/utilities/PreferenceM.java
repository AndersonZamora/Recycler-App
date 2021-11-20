package com.example.recycler.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceM implements preferenceManagerI{

    private final SharedPreferences sharedPreferences;

    public PreferenceM(Context mContext) {
        sharedPreferences = mContext.getSharedPreferences(ConstantsUtility.KEY_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void putBoolean(String key, Boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    @Override
    public boolean getBoolean(String key) {
        return false;
    }

    @Override
    public void putString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    @Override
    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }

    @Override
    public void clearPreference() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
