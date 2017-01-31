package codes.mutiny.moviedb.dagger.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import codes.mutiny.moviedb.BuildConfig;
import codes.mutiny.moviedb.TheApplication;
import codes.mutiny.moviedb.interfaces.PreferenceStore;
import codes.mutiny.moviedb.network.ApiService;
import codes.mutiny.moviedb.utilis.SharedPrefsHelper;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by Nikola Curilović on 9.6.2016..
 */
@Module
public class ApiModule {

    private static PreferenceStore preferenceStore;

    private HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            Timber.tag("OkHttp").d(message);
        }
    });

    private Interceptor authorizationInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            if (preferenceStore == null) {
                preferenceStore = new SharedPrefsHelper(TheApplication.getInstance());
            }
            return null;
        }
    };

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .create();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Integer networkTimeoutSeconds) {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(networkTimeoutSeconds, TimeUnit.SECONDS);
        client.setReadTimeout(networkTimeoutSeconds, TimeUnit.SECONDS);
        client.networkInterceptors().add(authorizationInterceptor);
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.networkInterceptors().add(httpLoggingInterceptor);
        }
        return client;
    }

    @Provides
    @Singleton
    public ApiService provideApiService(OkHttpClient client, String baseUrl, Gson gson) {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        Retrofit retrofit = builder.build();
        return retrofit.create(ApiService.class);
    }

}
