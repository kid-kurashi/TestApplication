package ru.dwdm.testapplication.presentation.model;

public class MainModel extends BaseModel {

    private String imagePath = "";
    private String email = "";
    private String phone = "";
    private String password = "";

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

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
