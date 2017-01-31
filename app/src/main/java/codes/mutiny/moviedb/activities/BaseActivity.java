package codes.mutiny.moviedb.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import codes.mutiny.moviedb.R;
import codes.mutiny.moviedb.TheApplication;
import codes.mutiny.moviedb.dagger.components.AppComponent;
import codes.mutiny.moviedb.fragments.BaseFragment;
import codes.mutiny.moviedb.mvp.BaseView;

/**
 * Created by Nikola Curilović on 10.6.2016..
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    public static final int NO_ITEMS = 0;

    @Nullable
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentViewResource());
        ButterKnife.bind(this);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
        injectDependencies(((TheApplication) getApplication()).getAppComponent());
    }

    @LayoutRes
    protected abstract int getContentViewResource();

    protected abstract void injectDependencies(AppComponent appComponent);


    @Override
    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(this, getString(R.string.app_name), null, true, false);
        } else if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void showProgress(@StringRes int description) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(this, getString(R.string.app_name), getString(description), true, false);
        } else if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    protected void hideKeyboard() {
        View focusedView = getCurrentFocus();
        if (focusedView != null) {
            focusedView.clearFocus();
            InputMethodManager imm = (InputMethodManager) focusedView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        }
    }

    public void showMessage(String description) {
        showMessage(getString(R.string.app_name), description, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }, false);
    }

    public void showMessage(String title, String description) {
        showMessage(title, description, false);
    }

    public void showMessage(String title, String description, boolean cancelable) {
        showMessage(title, description, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }, cancelable);
    }

    public void showMessage(String title, String description, DialogInterface.OnClickListener okListener, boolean cancelable) {
        if (!isFinishing()) {
            hideKeyboard();
            DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(cancelable);
            builder.setTitle(title)
                    .setMessage(description)
                    .setPositiveButton(android.R.string.ok, okListener);
            if (cancelable) {
                builder.setNegativeButton(android.R.string.cancel, cancelListener);
            }
            builder.show();
        }
    }

    @Override
    public void showError(String description) {
        showMessage(getString(R.string.error_title), description, false);
    }

    public void replaceFragment(@IdRes int fragmentContainerId, BaseFragment fragment, String tag) {
        replaceFragment(fragmentContainerId, fragment, tag, false);
    }

    public void replaceFragment(@IdRes int fragmentContainerId, BaseFragment fragment, String tag, boolean shouldAddToBackStack) {
        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(fragmentContainerId, fragment, tag);
        if (shouldAddToBackStack) {
            fTrans.addToBackStack(tag);
        }
        fTrans.commit();
    }

    public void addFragment(@IdRes int fragmentContainerId, String tag, Fragment fragment) {
        addFragment(fragmentContainerId, tag, fragment, false);
    }

    public void addFragment(@IdRes int fragmentContainerId, String tag, Fragment fragment, boolean shouldAddToBackStack) {
        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(fragmentContainerId, fragment, tag);
        if (shouldAddToBackStack) {
            fTrans.addToBackStack(tag);
        }
        fTrans.commit();
    }

    public void clearFragments(Bundle extras) {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

    public Fragment getFrontFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() == NO_ITEMS) {
            return null;
        }
        FragmentManager.BackStackEntry entry = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1);
        if (entry == null) {
            return null;
        }
        String fragmentTag = entry.getName();
        Fragment currentFragment = getSupportFragmentManager()
                .findFragmentByTag(fragmentTag);
        return currentFragment;
    }

    public boolean removeFrontFragment() {
        return getSupportFragmentManager().popBackStackImmediate();
    }

    protected boolean isPortrait() {
        int orientation = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
        return orientation == Surface.ROTATION_0 || orientation == Surface.ROTATION_180;
    }

    private void setActionBarTitle(@StringRes int stringRes) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(stringRes);
        }
    }

    protected void setupActionBarBack() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected void setupActionBarBackIcon(@DrawableRes int drawableId) {
        if (toolbar != null) {
            toolbar.setNavigationIcon(drawableId);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Go one step ic_back.
                    //Can handle special logic here
                    onBackPressed();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        Intent parentActivityIntent = NavUtils.getParentActivityIntent(this);
        // if a parent activity is defined via manifest this will return an intent
        if (parentActivityIntent != null) {
            parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(parentActivityIntent);
            finish();
        } else {
            super.onBackPressed();
        }
    }

}
