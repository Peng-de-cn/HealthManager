package com.example.healthmanager.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.healthmanager.data.database.dao.MyMedicineDao
import com.example.healthmanager.data.database.entity.Medicine

@Database(entities = [Medicine::class], version = 1, exportSchema = false)
abstract class MyMedicineDatabase: RoomDatabase()  {
    abstract fun myMedicineDao(): MyMedicineDao

    companion object {

        private var instance: MyMedicineDatabase? = null

        @Synchronized
        fun instance(context: Context): MyMedicineDatabase {
            if (instance == null) {
                instance = Room
                    .databaseBuilder(context.applicationContext, MyMedicineDatabase::class.java, "my_medicine")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }
}