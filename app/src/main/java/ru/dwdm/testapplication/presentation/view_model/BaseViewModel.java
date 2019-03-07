package ru.dwdm.testapplication.presentation.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import ru.dwdm.testapplication.App;
import ru.dwdm.testapplication.presentation.model.BaseModel;

public abstract class BaseViewModel<M extends BaseModel> extends AndroidViewModel implements LifecycleObserver {

    protected final App app;
    protected MutableLiveData<M> model = new MutableLiveData<>();

    public BaseViewModel(@NonNull Application application) {
        super(application);
        this.app = (App) application;
        initData();
    }

    protected abstract void initData();

    protected App getApp() {
        return app;
    }

    public MutableLiveData<M> getLiveModel() {
        return model;
    }

    public M getModel() {
        return model.getValue();
    }

    public void updateModel(M data) {
        model.postValue(data);
    }
}
