package ru.dwdm.testapplication.presentation.view;

import android.annotation.SuppressLint;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;

import java.util.Locale;

import ru.dwdm.testapplication.App;
import ru.dwdm.testapplication.presentation.view_model.BaseViewModel;

@SuppressLint("Registered")
public abstract class BaseActivity<B extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {

    protected App app;
    protected B binding;
    protected VM viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (App) getApplication();
        initBinding();
        initViewModel();
        if (viewModel != null) {
            getLifecycle().addObserver(viewModel);
            attachBinding();
        }
        hackFixHintsForMeizu();
    }


    protected App getApp() {
        return app;
    }

    protected abstract void hackFixHintsForMeizu();

    protected abstract void initBinding();

    protected abstract void initViewModel();

    protected abstract void attachBinding();
}
