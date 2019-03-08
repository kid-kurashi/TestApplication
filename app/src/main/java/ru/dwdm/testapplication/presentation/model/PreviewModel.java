package ru.dwdm.testapplication.presentation.model;

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
        return phone;
    }

    public String getPassword() {
        return password;
    }
}
