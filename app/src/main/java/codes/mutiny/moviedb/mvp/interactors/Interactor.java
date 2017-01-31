package codes.mutiny.moviedb.mvp.interactors;

import rx.Observable;

/**
 * Created by nikola on 05.07.16..
 */
public interface Interactor {

    interface GetMovie {

        Observable getStuff();

    }

}
