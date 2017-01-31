package codes.mutiny.moviedb.utilis;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;

import codes.mutiny.moviedb.interfaces.PreferenceStore;

/**
 * Created by Nikola Curilović on 9.6.2016..
 */
public class SharedPrefsHelper implements PreferenceStore {

    private Context context;

    @Inject
    public SharedPrefsHelper(Context context) {
        this.context = context.getApplicationContext();
    }

    private SharedPreferences getSharedPrefs() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}
