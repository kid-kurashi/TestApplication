package ru.dwdm.testapplication.presentation.model.factory;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import ru.dwdm.testapplication.presentation.view_model.MainViewModel;
import ru.dwdm.testapplication.presentation.view_model.PreviewViewModel;

public class ModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final String TAG;

    private final Application application;

    public ModelFactory(Application application) {
        this.application = application;
        TAG = "ModelFactory: create";
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == MainViewModel.class) {
            return (T) new MainViewModel(application);
        }
        if (modelClass == PreviewViewModel.class) {
            return (T) new PreviewViewModel(application);
        }
        return (T) new AndroidViewModel(application);
    }
}
