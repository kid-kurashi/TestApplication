package ru.dwdm.testapplication.presentation.view_model;

import android.app.Application;
import android.support.annotation.NonNull;

import ru.dwdm.testapplication.presentation.model.BaseModel;
import ru.dwdm.testapplication.presentation.model.MainModel;

public class MainViewModel extends BaseViewModel<MainModel> {

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void initData() {
        getLiveModel().postValue(new MainModel());
    }

    public void onButtonValidateClick() {

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

    public void onImageSelected(String realPathFromURI) {
        getModel().setImagePath(realPathFromURI);
        getLiveModel().postValue(getModel());
    }
}
