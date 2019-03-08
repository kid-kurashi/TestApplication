package ru.dwdm.testapplication.presentation.model;

import android.util.Log;

public class PreviewModel extends BaseModel {

    private String imagePath = "";
    private String email = "";
    private String phone = "";
    private String password = "";

    public PreviewModel() {
    }

    public PreviewModel(String imagePath, String email, String phone, String password) {
        this.imagePath = imagePath;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        if (!phone.isEmpty()) {
            Log.e("PHONE", phone);
            return phone.substring(0, 2) +
                    " (" +
                    phone.substring(2, 5) +
                    ") " +
                    phone.substring(5, 8) +
                    " " +
                    phone.substring(8, 10) +
                    " " +
                    phone.substring(10, 12);
        }
        return "";
    }

    public String getPassword() {
        return password;
    }
}
