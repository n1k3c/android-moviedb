package codes.mutiny.moviedb.mvp.interactors;

import android.os.Handler;
import android.os.Looper;

import codes.mutiny.moviedb.mvp.listeners.Listener;
import timber.log.Timber;

/**
 * Created by nikola on 6/17/16.
 */
public abstract class BaseInteractor<Params, Result> {

    protected Handler handler = new Handler(Looper.getMainLooper());

    protected Result data;

    protected Thread thread;

    protected void execute(final Listener<Result> listener, final Params... params) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    data = transaction(params);
                    postToMainThread(listener, true);
                } catch (Exception e) {
                    Timber.e(e, e.getMessage());
                    postToMainThread(listener, false);
                }
            }
        });
        thread.start();
    }

    private void postToMainThread(final Listener<Result> listener, final boolean isSuccessful) {
        if (listener != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (isSuccessful) {
                        onSuccess(listener);
                    } else {
                        onError(listener);
                    }
                }
            });
        }
    }

    protected void onError(Listener<Result> listener) {
        listener.onFailure("Database call failed.");
    }

    protected void onSuccess(Listener<Result> listener) {
        listener.onSuccess(data);
    }

    public void cancel() {
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
        }
    }

    public void reset() {
        //do nothing
    }

    @SuppressWarnings("unchecked")
    protected abstract Result transaction(Params... params) throws Exception;
}
