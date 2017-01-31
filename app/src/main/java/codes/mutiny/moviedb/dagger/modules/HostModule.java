package codes.mutiny.moviedb.dagger.modules;


import javax.inject.Singleton;

import codes.mutiny.moviedb.BuildConfig;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Nikola Curilović on 10.6.2016..
 */
@Module
public class HostModule {

    public static final int NETWORK_TIMEOUT_SECONDS = 30;

    @Provides
    @Singleton
    public Integer provideNetworkTimeout() {
        return NETWORK_TIMEOUT_SECONDS;
    }

    @Provides
    public String provideBaseUrl() {
        return BuildConfig.API_URL;
    }
}
