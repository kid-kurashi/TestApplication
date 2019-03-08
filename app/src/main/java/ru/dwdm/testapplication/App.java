package ru.dwdm.testapplication;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import ru.dwdm.testapplication.data.database.InfoDatabase;
import ru.dwdm.testapplication.domain.command.DatabaseHelper;

public class App extends Application {

    private InfoDatabase infoDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        infoDatabase = Room.databaseBuilder(getApplicationContext(), InfoDatabase.class, "database")
                .setJournalMode(RoomDatabase.JournalMode.AUTOMATIC)
                .build();
    }

    public DatabaseHelper getDatabaseHelper() {
        return new DatabaseHelper(infoDatabase.infoDao());
    }
}
