package codes.mutiny.moviedb.mvp.interactors;

import javax.inject.Inject;

import codes.mutiny.moviedb.models.MoviesInfo;
import codes.mutiny.moviedb.network.ApiService;
import rx.Observable;

/**
 * Created by nikola on 6/17/16.
 */
public class GetMovieInteractor implements Interactor.GetMovie {

    @Inject
    ApiService apiService;

    @Inject
    public GetMovieInteractor() {

    }

    @Override
    public Observable<MoviesInfo> getStuff() {
        return apiService.getMoviesInfo("popular", 1, "your_api_key (it's not safe here; put key in build.gradle! :)");
    }

}
