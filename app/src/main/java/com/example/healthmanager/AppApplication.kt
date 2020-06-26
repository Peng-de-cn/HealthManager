package com.example.healthmanager

import android.app.Application
import com.example.healthmanager.data.database.MedicineDatabase
import com.example.healthmanager.data.database.entity.Medicine
import org.jetbrains.anko.doAsync

class AppApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val medicineDB = MedicineDatabase.instance(applicationContext)

        doAsync {
            if (medicineDB.medicineDao().count() == 0) {
                L.d("Insert Data")
                medicineDB.medicineDao().insert(
                    Medicine(
                        name = "阿司匹林",
                        maxiNumberOfTaking = 3,
                        minNumberOfTaking = 1,
                        timeOfTaking = "3",
                        takingTime1 = "07:00",
                        takingTime2 = "13:00",
                        takingTime3 = "19:00",
                        takingDose1 = 1,
                        takingDose2 = 1,
                        takingDose3 = 1,
                        date = "",
                        inventory = 20,
                        inventoryLeft = 10,
                        details = "成人口服量: 解热、镇痛，一次0.3～0.6g，一日3次，必要时每4小时1次。抗风湿，一日3～5g（急性风湿热可用到7～8g），分4次口服。抑制血小板聚集，尚无明确用量，多数主张应用小剂量，如50～150mg，每24小时1次。"
                    )
                )
                medicineDB.medicineDao().insert(
                    Medicine(
                        name = "拜糖平/阿卡波糖片",
                        maxiNumberOfTaking = 4,
                        minNumberOfTaking = 1,
                        timeOfTaking = "3",
                        takingTime1 = "06:00",
                        takingTime2 = "12:00",
                        takingTime3 = "18:00",
                        takingDose1 = 1,
                        takingDose2 = 1,
                        takingDose3 = 1,
                        date = "",
                        inventory = 20,
                        inventoryLeft = 10,
                        details = "治疗糖尿病，每片50mg，一日三次，一次一片至四片，用餐前整片吞服或与前几口食物咀嚼使用"
                    )
                )
            }
        }
    }
}