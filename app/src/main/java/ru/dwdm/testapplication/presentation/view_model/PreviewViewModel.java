package ru.dwdm.testapplication.presentation.view_model;

import android.app.Application;
import android.support.annotation.NonNull;

import ru.dwdm.testapplication.presentation.model.PreviewModel;

public class PreviewViewModel extends BaseViewModel<PreviewModel> {

    public PreviewViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void initData() {
        getLiveModel().postValue(new PreviewModel());
    }

}
