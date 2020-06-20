package com.example.healthmanager.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.healthmanager.data.database.dao.MedicineDao
import com.example.healthmanager.data.database.entity.Medicine

@Database(entities = [Medicine::class], version = 1, exportSchema = false)
abstract class MedicineDatabase : RoomDatabase() {
    abstract fun medicineDao(): MedicineDao

    companion object {

        private var instance: MedicineDatabase? = null

        @Synchronized
        fun instance(context: Context): MedicineDatabase {
            if (instance == null) {
                instance = Room
                    .databaseBuilder(context.applicationContext, MedicineDatabase::class.java, "medicine")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }
}