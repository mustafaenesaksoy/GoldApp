package com.enesaksoy.goldapp.appmodule

import android.content.Context
import androidx.room.Room
import com.enesaksoy.goldapp.repo.GoldRepository
import com.enesaksoy.goldapp.repo.GoldRepositoryImpl
import com.enesaksoy.goldapp.roomdb.GoldDao
import com.enesaksoy.goldapp.roomdb.GoldDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun injectDatabase(@ApplicationContext context : Context) = Room.databaseBuilder(context,
        GoldDatabase::class.java,"GoldDb"
        ).build()


    @Provides
    @Singleton
    fun injectDao(database: GoldDatabase) = database.getDao()

    @Provides
    @Singleton
    fun injectRepo(dao : GoldDao) : GoldRepository{
        return GoldRepositoryImpl(dao)
    }
}