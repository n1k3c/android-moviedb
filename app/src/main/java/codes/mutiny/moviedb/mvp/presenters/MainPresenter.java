package codes.mutiny.moviedb.mvp.presenters;

import android.content.Context;

import javax.inject.Inject;

import codes.mutiny.moviedb.models.MoviesInfo;
import codes.mutiny.moviedb.mvp.Main;
import codes.mutiny.moviedb.mvp.interactors.Interactor;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by nikola on 6/17/16.
 */
public class MainPresenter implements Main.Presenter {

    private final Context context;

    private Main.View view;

    private Interactor.GetMovie getMovieInteractor;

    @Inject
    public MainPresenter(Context context, Interactor.GetMovie getMovieInteractor, Main.View view) {
        this.view = view;
        this.context = context;
        this.getMovieInteractor = getMovieInteractor;
    }

    @Override
    public void loadData() {
        getMovieInteractor.getStuff()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MoviesInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        // cast to retrofit.HttpException to get the response code
                        if (e instanceof HttpException) {
                            HttpException response = (HttpException) e;
                            int code = response.code();
                        }
                    }

                    @Override
                    public void onNext(MoviesInfo moviesInfo) {

                        view.setData(moviesInfo.movieList.get(0).title);
                    }
                });
    }

}
