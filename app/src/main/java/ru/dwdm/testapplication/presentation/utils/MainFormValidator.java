package ru.dwdm.testapplication.presentation.utils;

import android.util.Log;

import java.util.HashMap;

import ru.dwdm.testapplication.domain.object.ValidationObject;
import ru.dwdm.testapplication.domain.command.Validator;
import ru.dwdm.testapplication.domain.command.ValidatorFactory;
import ru.dwdm.testapplication.presentation.model.MainModel;

public class MainFormValidator {

    public boolean validate(MainModel mainModel) {

        HashMap<ValidationObject, String> values = new HashMap<>();

        values.put(ValidationObject.EMAIL, mainModel.getEmail());
        values.put(ValidationObject.PASS, mainModel.getPassword());
        values.put(ValidationObject.PHONE, mainModel.getPhone());
        values.put(ValidationObject.PHOTO, mainModel.getImagePath());

        for (ValidationObject key : values.keySet()) {
            Validator<String> validator = new ValidatorFactory().get(key);
            Log.e("Validation", "value" + "[" + key + "]" + " = " + values.get(key) + "; result = " + validator.validate(values.get(key)));
            mainModel.getErrors().put(key, new ValidatorFactory().get(key).validate(values.get(key)));
        }

        return !mainModel.getErrors().containsValue(false);
    }
}
