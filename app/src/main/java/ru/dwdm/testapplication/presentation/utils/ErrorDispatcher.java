package ru.dwdm.testapplication.presentation.utils;

import android.support.design.widget.TextInputLayout;
import android.widget.Toast;

import java.util.HashMap;

import ru.dwdm.testapplication.R;
import ru.dwdm.testapplication.domain.object.ValidationObject;
import ru.dwdm.testapplication.presentation.model.MainModel;
import ru.dwdm.testapplication.presentation.view.MainActivity;

public class ErrorDispatcher {

    private MainActivity mainActivity;

    public void dispatch(MainModel model,
                         HashMap<ValidationObject, TextInputLayout> layoutsMap,
                         MainActivity mainActivity) {

        this.mainActivity = mainActivity;
        HashMap<ValidationObject, Boolean> errors = model.getErrors();

        for (ValidationObject key : layoutsMap.keySet()) {

            if (errors.get(key) != null && !errors.get(key)) {

                if (key == ValidationObject.EMAIL)
                    layoutsMap.get(key).setError(getStr(R.string.email_error));

                if (key == ValidationObject.PHONE)
                    layoutsMap.get(key).setError(getStr(R.string.phone_error));

                if (key == ValidationObject.PASS)
                    layoutsMap.get(key).setError(getStr(R.string.password_error));

            } else {
                layoutsMap.get(key).setErrorEnabled(false);
            }
        }

        if (errors.get(ValidationObject.PHOTO) != null && !errors.get(ValidationObject.PHOTO)) {
            Toast.makeText(mainActivity, getStr(R.string.photo_error), Toast.LENGTH_LONG).show();
        }
    }

    private String getStr(int ResId) {
        return mainActivity.getString(ResId);
    }
}
