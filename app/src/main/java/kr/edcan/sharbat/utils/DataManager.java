package kr.edcan.sharbat.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.util.Pair;
import android.util.Log;

/**
 * Created by Chad on 7/16/16.
 */


public class DataManager {
    /* Data Keys */
    public static final String AUTO_FILTER = "auto_filter";
    public static final String AUTO_DELETE = "auto_delete";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;
    public DataManager instance;

    public DataManager() {
    }

    public void initializeManager(Context c) {
        this.context = c;
        preferences = context.getSharedPreferences("Sharbat", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void save(String key, String data) {
        editor.putString(key, data);
        editor.apply();
    }
    public void save(String key, boolean data) {
        editor.putBoolean(key, data);
        editor.apply();
    }

    public void removeAllData() {
        editor.clear();
        editor.apply();
    }

    public String getString(String key) {
        return preferences.getString(key, "");
    }

    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public long getLong(String key) {
        return preferences.getLong(key, 0);
    }

}
