package ru.dwdm.testapplication.presentation.utils;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

public abstract class BaseTextWatcher implements TextWatcher {

    private TextInputLayout textInputLayout;
    private ITextCallback ITextCallback;

    BaseTextWatcher(TextInputLayout textInputLayout, ITextCallback ITextCallback) {
        this.textInputLayout = textInputLayout;
        this.ITextCallback = ITextCallback;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        textInputLayout.setErrorEnabled(false);
        ITextCallback.onTextChanged(s.toString());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
