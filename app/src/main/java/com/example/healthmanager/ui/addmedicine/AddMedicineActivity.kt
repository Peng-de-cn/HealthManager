package com.example.healthmanager.ui.addmedicine

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.healthmanager.R
import com.example.healthmanager.data.database.entity.Medicine

class AddMedicineActivity: AppCompatActivity() {

    companion object {
        const val EXTRA_MEDICINE = "extra_medicine"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setContentView(R.layout.activity_addmedicine)

        val bundle = Bundle()
        bundle.putParcelable(EXTRA_MEDICINE, intent.getParcelableExtra<Medicine>(EXTRA_MEDICINE))
        val fragment = AddMedicineFragment()
        fragment.arguments = bundle

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            finish()
            return true
        }

        return false
    }
}