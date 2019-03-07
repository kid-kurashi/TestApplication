package ru.dwdm.testapplication.presentation.utils;

import android.support.design.widget.TextInputLayout;

public class EmailTextWatcher extends BaseTextWatcher {

    public EmailTextWatcher(TextInputLayout textInputLayout, ITextCallback ITextCallback) {
        super(textInputLayout, ITextCallback);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        super.onTextChanged(s, start, before, count);
    }
}
