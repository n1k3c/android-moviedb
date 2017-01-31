package codes.mutiny.moviedb.dagger.components;

import javax.inject.Singleton;

import codes.mutiny.moviedb.dagger.modules.ApiModule;
import codes.mutiny.moviedb.dagger.modules.AppContextModule;
import codes.mutiny.moviedb.dagger.modules.HostModule;
import codes.mutiny.moviedb.dagger.modules.MainModule;
import codes.mutiny.moviedb.interfaces.PreferenceStore;
import codes.mutiny.moviedb.network.ApiService;
import dagger.Component;

/**
 * Created by Nikola Curilović on 9.6.2016..
 */
@Component(modules = {
        AppContextModule.class,
        ApiModule.class,
        HostModule.class,
})
@Singleton
public interface AppComponent {

    MainComponent plus(MainModule module);

    ApiService apiService();

    PreferenceStore preferenceStore();
}
