package ru.dwdm.testapplication.data;

public class Info {

    private String imageUri;
    private String email;
    private String phone;
    private String password;

    public Info(String imageUri, String email, String phone, String password) {
        this.imageUri = imageUri;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getImageUri() {
        return imageUri;
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
