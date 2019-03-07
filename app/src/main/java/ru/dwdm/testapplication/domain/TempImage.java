package ru.dwdm.testapplication.domain;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TempImage {

    private String JPEG_FILE_SUFFIX = ".jpg";
    private String JPEG_FILE_PREFIX = "IMG_";

    private String name;
    private File file;
    private File path;

    public String getName() {
        return file.getName();
    }
    public File getFile(){
        return file;
    }

    public TempImage(Context context) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        name = JPEG_FILE_PREFIX + timeStamp + JPEG_FILE_SUFFIX;
        path = context.getCacheDir();
        path.mkdirs();
        file = new File(path, name);
    }
}