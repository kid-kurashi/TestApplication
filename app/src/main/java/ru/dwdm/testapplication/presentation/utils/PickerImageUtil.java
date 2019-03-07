package ru.dwdm.testapplication.presentation.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import ru.dwdm.testapplication.R;
import ru.dwdm.testapplication.domain.TempImage;

public class PickerImageUtil {

    public static final int NONE_REQUEST_CODE = -1;

    private final Activity activity;
    private final String authority;

    private int cameraRequestCode = NONE_REQUEST_CODE;
    private int galleryRequestCode = NONE_REQUEST_CODE;

    public static final String TAG = PickerImageUtil.class.getSimpleName() + ".TAG_FRAGMENT_PICK";

    public static final String[] ARRAY_PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};

    private TempImage tempImage;

    public PickerImageUtil(Activity activity,
                           String authority) {
        this.activity = activity;
        this.authority = authority;
    }

    public PickerImageUtil setCameraRequest(int cameraRequest) {
        this.cameraRequestCode = cameraRequest;
        return this;
    }

    public PickerImageUtil setGalleryRequest(int galleryRequest) {
        this.galleryRequestCode = galleryRequest;
        return this;
    }

    public void openCamera() throws IOException {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            tempImage = new TempImage(activity.getApplicationContext());
            File photoFile = tempImage.getFile();
            Uri uri = FileProvider.getUriForFile(activity.getApplicationContext(), authority, photoFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            activity.startActivityForResult(Intent.createChooser(intent, activity.getString(R.string.source_picture)), cameraRequestCode);
        }
    }

    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setType("image/*");
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(Intent.createChooser(intent, activity.getString(R.string.source_picture)), galleryRequestCode);
        }
    }

    public boolean checkPermissions() {
        for (String arrayPermission : ARRAY_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(activity, arrayPermission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public void requestPermissions() {
        int PERMISSIONS_REQUEST = 200;
        ActivityCompat.requestPermissions(activity, ARRAY_PERMISSIONS, PERMISSIONS_REQUEST);
    }

    public File getFile(int requestCode, Intent intent) {
        File file = null;
        if (requestCode == galleryRequestCode && galleryRequestCode != NONE_REQUEST_CODE) {
            if (intent != null) {
                Uri selectedImageURI = intent.getData();
                try {
                    file = new File(getRealPathFromURI(selectedImageURI));
                } catch (Exception e) {
                    Toast.makeText(activity, activity.getString(R.string.not_on_local_storage), Toast.LENGTH_LONG).show();
                }
            }
        }
        if (requestCode == cameraRequestCode && cameraRequestCode != NONE_REQUEST_CODE) {
            file = tempImage.getFile();
        }
        return file;
    }

    public String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
}
