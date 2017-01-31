package codes.mutiny.moviedb.mvp.listeners;

/**
 * Created by nikola on 6/17/16.
 */
public interface Listener<Result> {

    void onSuccess(Result result);

    void onFailure(String message);
}
