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

        float coefficient;
        coefficient = width / 300;

        width *= coefficient;
        height *= coefficient;

        return Bitmap.createScaledBitmap(bitmap, (int) width, (int) height, false);
    }
}
