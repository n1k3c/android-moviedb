package codes.mutiny.moviedb.network;

import retrofit2.Callback;


/**
 * Created by Nikola Curilović on 9.6.2016..
 */
public abstract class BaseCallback<Result> implements Callback<Result> {

   /* private boolean isCanceled;

    @Override
    public void onResponse(Response<Result> response, Retrofit retrofit) {
        if (isCanceled) {
            return;
        }
        if (response.isSuccessful()) {
            if (response.code() == HttpURLConnection.HTTP_NO_CONTENT) {
                onNoContent(response.body(), response);
            } else {
                onSuccess(response.body(), response);
            }
        } else {
            try {
                switch (response.code()) {
                    case HttpURLConnection.HTTP_UNAUTHORIZED:
                        onUnauthorized(response);
                        break;
                    case HttpURLConnection.HTTP_FORBIDDEN:
                        onForbidden(response);
                        break;
                    case HttpURLConnection.HTTP_NOT_FOUND:
                        onNotFound(response);
                        break;
                    default:
                        onUnknownError(response);
                }
            } catch (Exception e) {
                onUnknownError(response);
            }
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (isCanceled) {
            return;
        }
        if (t instanceof SocketTimeoutException || t instanceof UnknownHostException) {
            onNetworkError();
        } else {
            onUnknownError(null);
        }
    }

    public void cancel() {
        isCanceled = true;
    }

    public void reset() {
        isCanceled = false;
    }

    public abstract void onSuccess(Result result, Response response);

    *//**
     * Override if you want to handle this case specially.
     *//*
    protected void onNoContent(Result result, Response response) {
        onSuccess(result, response);
    }

    *//**
     * Override if you want to handle this case specially.
     *//*
    protected void onNotFound(Response<Result> response) {
        onUnknownError(response);
    }

    *//**
     * Override if you want to handle this case specially.
     *//*
    protected void onUnauthorized(Response<Result> response) {
        onUnknownError(response);
    }

    *//**
     * Override if you want to handle this case specially.
     *//*
    protected void onForbidden(Response<Result> response) {
        onUnknownError(response);
    }

    protected void onNetworkError() {
        onUnknownError(null);
    }

    public abstract void onUnknownError(@Nullable Response<Result> response);*/
}