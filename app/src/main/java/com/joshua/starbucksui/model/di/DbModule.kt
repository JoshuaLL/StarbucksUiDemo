package com.joshua.starbucksui.model.di

import android.content.Context
import androidx.room.Room
import com.joshua.starbucksui.model.db.AppDB
import com.joshua.starbucksui.model.db.AppDB.Companion.DB_NAME
import com.joshua.starbucksui.model.db.AppDao
import com.joshua.starbucksui.model.db.AppDbRepository
import com.joshua.starbucksui.model.db.StartingDB
import org.koin.dsl.module

val dbModule = module {
    single { provideAppDatabase(get()) }
    single { provideAppDao(get()) }
    single { provideDBRepository(get()) }
}

fun provideAppDatabase(context: Context): AppDB {
    return Room.databaseBuilder(context, AppDB::class.java, DB_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .addCallback(StartingDB(context))
            .build()
}

fun provideAppDao(db: AppDB)= db.appDao()

fun provideDBRepository(dao: AppDao)= AppDbRepository(dao)

