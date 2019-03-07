package ru.dwdm.testapplication.presentation.utils;

import android.graphics.Bitmap;

public class ScaleBitmapUtil {

    private Bitmap bitmap;

    public ScaleBitmapUtil(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap smallBitmap() {
        float width = bitmap.getWidth();
        float height = bitmap.getHeight();
        float targetH = 300;

        float coefficient;
        if (width > height) {
            coefficient = width / height;
        } else {
            coefficient = height / width;
        }

        return Bitmap.createScaledBitmap(bitmap, (int) (width * coefficient), (int) (width * coefficient * coefficient), false);
    }
}
