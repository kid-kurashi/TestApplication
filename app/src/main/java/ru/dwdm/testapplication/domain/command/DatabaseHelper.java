package ru.dwdm.testapplication.domain.command;


import android.database.sqlite.SQLiteConstraintException;

import io.reactivex.Observable;
import ru.dwdm.testapplication.data.dao.InfoDao;
import ru.dwdm.testapplication.data.pojo.Info;
import ru.dwdm.testapplication.presentation.model.MainModel;
import ru.dwdm.testapplication.presentation.model.PreviewModel;

public class DatabaseHelper {

    private final InfoDao infoDao;

    public DatabaseHelper(InfoDao infoDao) {
        this.infoDao = infoDao;
    }

    public Observable<Boolean> writeModel(MainModel model) {
        return Observable.fromCallable(() -> {

            Info info = new Info();
            info.id = 1;
            info.email = model.getEmail();
            info.imagePath = model.getImagePath();
            info.password = model.getPassword();
            info.phone = model.getPhone();

            try {
                infoDao.insert(info);
            } catch (SQLiteConstraintException e) {
                infoDao.update(info);
            }
            return true;
        });
    }

    public Observable<PreviewModel> readModel() {
        Observable<Info> subject = Observable.fromCallable(() -> infoDao.getInfo(1));

        return subject
                .map(info -> new PreviewModel(
                        info.imagePath,
                        info.email,
                        info.phone,
                        info.password
                ));
    }
}
