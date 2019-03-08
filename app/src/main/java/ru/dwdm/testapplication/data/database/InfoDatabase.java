package ru.dwdm.testapplication.data.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.dwdm.testapplication.data.pojo.Info;
import ru.dwdm.testapplication.data.dao.InfoDao;

@Database(entities = {Info.class}, version = 1)
public abstract class InfoDatabase extends RoomDatabase {
    public abstract InfoDao infoDao();
}
