package com.example.todo.di

import android.app.Application
import androidx.room.Room
import com.example.todo.common.Const
import com.example.todo.data.database.AppDatabaseService
import com.example.todo.data.database.DatabaseService
import com.example.todo.data.database.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideTodoDatabase(
        application: Application,
        @DbName dbName: String
    ): TodoDatabase {
        return Room.databaseBuilder(
            application,
            TodoDatabase::class.java,
            dbName
        ).build()
    }

    @DbName
    @Provides
    fun provideDbName(): String = Const.DB_NAME

    @Provides
    @Singleton
    fun provideDatabaseService(todoDatabase: TodoDatabase): DatabaseService {
        return AppDatabaseService(todoDatabase)
    }
}