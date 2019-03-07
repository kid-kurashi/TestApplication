package ru.dwdm.testapplication.presentation.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class BitmapDecodeUtil {

    private String path;

    public BitmapDecodeUtil(String path) {
        this.path = path;
    }

    public Bitmap smallBitmap() {
        Bitmap bitmap = BitmapFactory.decodeFile(path);

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        float coefficient;
        coefficient = width / 300f;

        float newWidth, newHeight;
        newWidth = coefficient * width;
        newHeight = coefficient * height;

        float scaleWidth = newWidth / width;
        float scaleHeight = newHeight / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(
                bitmap, 0, 0, width, height, matrix, false);
        bitmap.recycle();

        return resizedBitmap;
    }
}
