package codes.mutiny.moviedb.dagger.modules;

import codes.mutiny.moviedb.mvp.Main;
import codes.mutiny.moviedb.mvp.interactors.GetMovieInteractor;
import codes.mutiny.moviedb.mvp.interactors.Interactor;
import codes.mutiny.moviedb.mvp.presenters.MainPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by nikola on 6/17/16.
 */
@Module
public class MainModule {

    Main.View view;

    public MainModule(Main.View view) {
        this.view = view;
    }

    @Provides
    Main.Presenter provideMainPresenter(MainPresenter mainPresenter) {
        return mainPresenter;
    }

    @Provides
    Main.View provideMainView() {
        return view;
    }

    @Provides
    public Interactor.GetMovie provideInteractor(GetMovieInteractor interactor) {
        return interactor;
    }
}
