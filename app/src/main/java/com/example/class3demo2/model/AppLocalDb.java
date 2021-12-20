package com.example.class3demo2.model;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1)
abstract class AppLocalDbRepository extends RoomDatabase {
    public abstract StudentDao studentDao();
}
public class AppLocalDb{
//    static public AppLocalDbRepository db =
//            Room.databaseBuilder(MyApplication.context,
//                    AppLocalDbRepository.class,
//                    "dbFileName.db")
//                    .fallbackToDestructiveMigration()
//                    .build();
}

