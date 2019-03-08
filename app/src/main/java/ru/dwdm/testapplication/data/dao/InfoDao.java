package ru.dwdm.testapplication.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import ru.dwdm.testapplication.data.pojo.Info;

@Dao
public interface InfoDao {

    @Query("SELECT * FROM info WHERE id = :id")
    Info getInfo(long id);

    @Insert
    void insert(Info info);

    @Update
    void update(Info info);

}
