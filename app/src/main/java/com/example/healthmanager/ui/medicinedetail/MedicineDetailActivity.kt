package com.example.healthmanager.ui.medicinedetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.healthmanager.R
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_ITEMMEDICINE

class MedicineDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_medicinedetail)

        val bundle = Bundle()
        bundle.putParcelable(EXTRA_ITEMMEDICINE, intent.getParcelableExtra(EXTRA_ITEMMEDICINE))

        val fragment = MedicineDetailFragment()
        fragment.arguments = bundle

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
        }
    }

}