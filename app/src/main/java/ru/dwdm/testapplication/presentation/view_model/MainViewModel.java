package ru.dwdm.testapplication.presentation.view_model;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.dwdm.testapplication.domain.command.DatabaseHelper;
import ru.dwdm.testapplication.presentation.model.MainModel;
import ru.dwdm.testapplication.presentation.utils.IImageCallback;
import ru.dwdm.testapplication.presentation.utils.MainFormValidator;
import ru.dwdm.testapplication.presentation.utils.SuccessValidationCallback;

public class MainViewModel extends BaseViewModel<MainModel> {

    private DatabaseHelper databaseHelper;
    private Disposable databaseDisposable;

    public MainViewModel(@NonNull Application application) {
        super(application);
        databaseHelper = app.getDatabaseHelper();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void disposeSubscribers() {
        if (databaseDisposable != null && !databaseDisposable.isDisposed()) {
            databaseDisposable.dispose();
        }
    }

    @Override
    protected void initData() {
        getLiveModel().setValue(new MainModel());
    }

    public void onButtonValidateClick(SuccessValidationCallback callback) {
        MainFormValidator validator = new MainFormValidator();
        boolean validationResult = validator.validate(getModel());
        if (validationResult) {
            databaseDisposable = databaseHelper
                    .writeModel(getModel())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(callback::onValidationComplete, this::databaseError);
        } else {
            callback.onValidationComplete(false);
        }
    }

    private void databaseError(Throwable throwable) {
        throwable.printStackTrace();
    }

    public void onEmailChanged(String text) {
        getModel().setEmail(text);
    }

    public void onPhoneChanged(String text) {
        getModel().setPhone(text);
    }

    public void onPasswordChanged(String text) {
        getModel().setPassword(text);
    }

    public void onImageSelected(String realPathFromURI, IImageCallback callback) {
        getModel().setImagePath(realPathFromURI);
        getLiveModel().postValue(getModel());
        callback.onImageReady(realPathFromURI);
    }
}
