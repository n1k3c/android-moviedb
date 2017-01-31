package codes.mutiny.moviedb.activities;

import android.os.Bundle;

import javax.inject.Inject;

import codes.mutiny.moviedb.R;
import codes.mutiny.moviedb.dagger.components.AppComponent;
import codes.mutiny.moviedb.dagger.modules.MainModule;
import codes.mutiny.moviedb.mvp.Main;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements Main.View {

    @Inject
    Main.Presenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getData();
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        appComponent
                .plus(new MainModule(this))
                .inject(this);
    }

    @Override
    public void getData() {
        mainPresenter.loadData();
    }

    @Override
    public void setData(String data) {
        Timber.d("Movie title-> " + data);
    }

}
