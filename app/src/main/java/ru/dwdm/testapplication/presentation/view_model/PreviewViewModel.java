package ru.dwdm.testapplication.presentation.view_model;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.dwdm.testapplication.R;
import ru.dwdm.testapplication.domain.command.DatabaseHelper;
import ru.dwdm.testapplication.presentation.model.PreviewModel;

public class PreviewViewModel extends BaseViewModel<PreviewModel> {

    private DatabaseHelper databaseHelper;
    private Disposable getInfoDisposable;

    public PreviewViewModel(@NonNull Application application) {
        super(application);
        databaseHelper = app.getDatabaseHelper();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void disposeSubscribers() {
        if (getInfoDisposable != null && !getInfoDisposable.isDisposed())
            getInfoDisposable.dispose();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void getData() {
        getInfoDisposable = databaseHelper
                .readModel()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(model -> getLiveModel().postValue(model), this::onError);
    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    protected void initData() {
        getLiveModel().postValue(new PreviewModel());
    }

    public String getImagePath() {
        return getModel().getImagePath();
    }

    public String getEmailBody() {
        return "Email: " + getModel().getEmail() + "\n\r" + "Phone: " + getModel().getPhone() + "\n\r";
    }

    public String getEmailSubject() {
        return app.getString(R.string.app_name) + ": " + "данные анкеты";
    }

    public String[] getEmailRecipients() {
        return new String[]{""};
    }
}
