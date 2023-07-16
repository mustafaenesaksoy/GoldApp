package com.enesaksoy.goldapp.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Model::class], version = 1)
@TypeConverters(TypeConverter::class)
abstract class GoldDatabase : RoomDatabase(){

    abstract fun getDao() : GoldDao
}