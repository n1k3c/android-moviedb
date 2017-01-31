package codes.mutiny.moviedb;

import android.app.Application;

import codes.mutiny.moviedb.dagger.components.AppComponent;
import codes.mutiny.moviedb.dagger.components.DaggerAppComponent;
import timber.log.Timber;

/**
 * Created by Nikola Curilović on 3.6.2016..
 */
public class TheApplication extends Application {

    private static TheApplication instance;

    protected AppComponent appComponent;

    public static TheApplication getInstance() {
        return instance;
    }

    protected static void setInstance(TheApplication instance) {
        TheApplication.instance = instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        init();
    }

    private void init() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        appComponent = DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
