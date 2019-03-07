package ru.dwdm.testapplication.presentation.utils;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TextInputEditText;

import java.util.Locale;

public class Fixes {

    public void hackFixHintsForMeizu(TextInputEditText... editTexts) {
        String manufacturer = Build.MANUFACTURER.toUpperCase(Locale.US);
        if (manufacturer.contains("MEIZU")) {
            for (TextInputEditText editText : editTexts) {
                editText.setHintTextColor(Color.TRANSPARENT);
                editText.setHint(editText.getHint());
            }
        }
    }
}
