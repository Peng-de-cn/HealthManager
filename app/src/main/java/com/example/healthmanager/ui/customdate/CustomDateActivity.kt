package com.example.healthmanager.ui.customdate

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.healthmanager.R
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_DATE

class CustomDateActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView(R.layout.activity_customdate)

        val bundle = Bundle()
        bundle.putString(EXTRA_DATE, intent.getStringExtra(EXTRA_DATE))

        val fragment = CustomDateFragment()
        fragment.arguments = bundle

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
        }
    }
}