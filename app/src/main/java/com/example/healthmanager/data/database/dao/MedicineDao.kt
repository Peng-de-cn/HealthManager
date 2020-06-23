package com.example.healthmanager.data.database.dao

import androidx.room.*
import com.example.healthmanager.data.database.entity.Medicine

@Dao
interface MedicineDao {

    @Insert
    fun insert(medicine: Medicine)

    @Insert
    fun insertAll(medicine: List<Medicine>)

    @Query("select * from Medicine")
    fun allmedicine(): List<Medicine>

    @Query("select * from Medicine ORDER BY name")
    fun allmedicineOrderByName(): List<Medicine>

    @Query("SELECT * FROM Medicine WHERE name LIKE '%' || :name || '%'")
    fun medicineByName(name: String): List<Medicine>

    @Delete
    fun delete(medicine: Medicine)

    @Query("DELETE FROM Medicine")
    fun deleteAll()

    @Query("select count(*) from Medicine")
    fun count(): Int

    @Update
    fun updateMedicine(medicine: Medicine)
}