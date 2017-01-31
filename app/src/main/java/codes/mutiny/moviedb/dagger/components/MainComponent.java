package codes.mutiny.moviedb.dagger.components;

import codes.mutiny.moviedb.activities.MainActivity;
import codes.mutiny.moviedb.dagger.modules.MainModule;
import dagger.Subcomponent;

/**
 * Created by nikola on 6/17/16.
 */
@Subcomponent(modules = {
        MainModule.class
})
public interface MainComponent {

    void inject(MainActivity activity);
}
