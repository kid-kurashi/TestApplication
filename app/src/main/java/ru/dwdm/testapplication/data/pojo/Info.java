package ru.dwdm.testapplication.data.pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Info {

    @PrimaryKey
    public long id = 1;

    public String imagePath;
    public String email;
    public String phone;
    public String password;
}
