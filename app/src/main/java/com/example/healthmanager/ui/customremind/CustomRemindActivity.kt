package com.example.healthmanager.ui.customremind

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.healthmanager.R
import com.example.healthmanager.util.AppConstants
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_MAXINUMBEROFTAKING
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_MINNUMBEROFTAKING
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_TAKINGDOSE
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_TAKINGTIME

class CustomRemindActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_customremind)

        val bundle = Bundle()
        bundle.putInt(AppConstants.EXTRA_REQUEST_CODE_ADDMEDICINE, intent.getIntExtra(AppConstants.EXTRA_REQUEST_CODE_ADDMEDICINE, -1))
        bundle.putString(EXTRA_TAKINGTIME, intent.getStringExtra(EXTRA_TAKINGTIME))
        bundle.putInt(EXTRA_TAKINGDOSE, intent.getIntExtra(EXTRA_TAKINGDOSE, -1))
        bundle.putInt(EXTRA_MAXINUMBEROFTAKING, intent.getIntExtra(EXTRA_MAXINUMBEROFTAKING, -1))
        bundle.putInt(EXTRA_MINNUMBEROFTAKING, intent.getIntExtra(EXTRA_MINNUMBEROFTAKING, 1))

        val fragment = CustomRemindFragment()
        fragment.arguments = bundle

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
        }
    }
}