/*
 * Created by Force Porquillo on 7/2/20 1:45 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/2/20 1:45 PM
 */

package com.force.codes.project.app.app.di.module;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.force.codes.project.app.data_layer.resources.database.AppDatabase;
import com.force.codes.project.app.data_layer.resources.database.LiveDataDao;
import com.force.codes.project.app.data_layer.resources.database.MapDao;
import com.force.codes.project.app.data_layer.resources.database.NewsDao;
import com.force.codes.project.app.data_layer.resources.database.WorldwideDao;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class DatabaseModule {
  static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
    }
  };

  @Singleton
  @Provides
  static AppDatabase providesLocalDatabase(Application application) {
    return Room.databaseBuilder(application, AppDatabase.class,
        "AppDatabase.db").fallbackToDestructiveMigration()
        .addCallback(roomCallback).build();
  }

  @Singleton
  @Provides
  static MapDao provideMapDao(AppDatabase database) {
    return database.mapDao();
  }

  @Singleton
  @Provides
  static WorldwideDao provideWorldwideDao(AppDatabase database) {
    return database.worldwideDao();
  }

  @Singleton
  @Provides
  static LiveDataDao provideLiveDataDao(AppDatabase database) {
    return database.liveDataDao();
  }

  @Singleton
  @Provides
  static NewsDao provideNewsDao(AppDatabase database) {
    return database.newsDao();
  }
}
