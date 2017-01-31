package codes.mutiny.moviedb.network;

import codes.mutiny.moviedb.models.MoviesInfo;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Nikola Curilović on 9.6.2016..
 */
public interface ApiService {

    @GET("3/movie/{categories}")
    Observable<MoviesInfo> getMoviesInfo(@Path("categories") String categories, @Query("page") int page, @Query("api_key") String apiKey);

}
