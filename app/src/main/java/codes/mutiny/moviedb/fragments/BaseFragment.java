package codes.mutiny.moviedb.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import codes.mutiny.moviedb.R;
import codes.mutiny.moviedb.TheApplication;
import codes.mutiny.moviedb.activities.BaseActivity;
import codes.mutiny.moviedb.dagger.components.AppComponent;
import codes.mutiny.moviedb.interfaces.ActionBarResourceProvider;
import codes.mutiny.moviedb.mvp.BaseView;
import timber.log.Timber;

/**
 * Created by Nikola Curilović on 10.6.2016..
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies(TheApplication.getInstance().getAppComponent());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentViewResource(), container, false);
        setBindButterKnife(view);

        return view;
    }

    @LayoutRes
    protected abstract int getContentViewResource();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void setBindButterKnife(View view) {
        ButterKnife.bind(this, view);
    }

    protected void setActionBarTitle(String title) {
        if (getActivity() != null && getActivity() instanceof ActionBarResourceProvider) {
            ((ActionBarResourceProvider) getActivity()).setTitle(title);
        }
    }

    protected void setActionBarIcon(int icon) {
        if (getActivity() != null && getActivity() instanceof ActionBarResourceProvider) {
            ((ActionBarResourceProvider) getActivity()).setIcon(icon);
        }
    }

    protected abstract void injectDependencies(AppComponent appComponent);

    @Override
    public void showProgress() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showProgress();
        } else {
            Timber.d(getString(R.string.extends_base_activity));
        }
    }

    @Override
    public void showProgress(@StringRes int description) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showProgress(description);
        } else {
            Timber.d(getString(R.string.extends_base_activity));
        }
    }

    @Override
    public void hideProgress() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).hideProgress();
        } else {
            Timber.d(getString(R.string.extends_base_activity));
        }
    }

    @Override
    public void showMessage(String description) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showMessage(description);
        } else {
            Timber.d(getString(R.string.extends_base_activity));
        }
    }

    @Override
    public void showMessage(String message, String description) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showMessage(message, description);
        } else {
            Timber.d(getString(R.string.extends_base_activity));
        }
    }

    @Override
    public void showMessage(String message, String description, boolean cancelable) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showMessage(message, description, cancelable);
        } else {
            Timber.d(getString(R.string.extends_base_activity));
        }
    }

    @Override
    public void showMessage(String message, String description, DialogInterface.OnClickListener listener, boolean cancelable) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showMessage(message, description, listener, cancelable);
        } else {
            Timber.d(getString(R.string.extends_base_activity));
        }
    }

    @Override
    public void showError(String description) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showError(description);
        } else {
            Timber.d(getString(R.string.extends_base_activity));
        }
    }
}