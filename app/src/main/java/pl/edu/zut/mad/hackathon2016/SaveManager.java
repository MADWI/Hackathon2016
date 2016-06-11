package pl.edu.zut.mad.hackathon2016;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveManager {
    private static final String PREFS_NAME = "prefs_name";
    private static final String IS_LOC_CHOOSE = "is_location_choose";

    private Context context;

    public SaveManager(Context context) {
        this.context = context;
    }

    public boolean isLocalizationChoose() {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(IS_LOC_CHOOSE, false);
    }

    public void setLocalizationChoose(boolean state) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(IS_LOC_CHOOSE, state);
        editor.apply();
    }
}
