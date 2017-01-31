package codes.mutiny.moviedb.mvp;

import android.content.DialogInterface;
import android.support.annotation.StringRes;

/**
 * Created by Nikola Curilović on 10.6.2016..
 */
public interface BaseView {

    void showProgress();

    void showProgress(@StringRes int description);

    void hideProgress();

    void showMessage(String description);

    void showMessage(String message, String description);

    void showMessage(String message, String description, boolean cancelable);

    void showMessage(String message, String description, DialogInterface.OnClickListener listener, boolean cancelable);

    void showError(String description);

}
