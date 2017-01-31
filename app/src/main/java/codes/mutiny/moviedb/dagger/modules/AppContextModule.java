package codes.mutiny.moviedb.dagger.modules;

import android.content.Context;
import android.content.res.Resources;

import codes.mutiny.moviedb.TheApplication;
import codes.mutiny.moviedb.interfaces.PreferenceStore;
import codes.mutiny.moviedb.utilis.SharedPrefsHelper;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Nikola Curilović on 9.6.2016..
 */
@Module
public class AppContextModule {

    @Provides
    public Context provideAppContext() {
        return TheApplication.getInstance();
    }

    @Provides
    public Resources provideResources(Context context) {
        return context.getResources();
    }

    @Provides
    public PreferenceStore providePreferenceStore(Context context) {
        return new SharedPrefsHelper(context);
    }

}
