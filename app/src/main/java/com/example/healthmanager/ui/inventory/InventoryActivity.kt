package com.example.healthmanager.ui.inventory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.healthmanager.R
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_INVENTORY
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_INVENTORY_ORIGIN
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_REQUEST_CODE_INVENTORY

class InventoryActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_inventory)

        val bundle = Bundle()
        bundle.putInt(EXTRA_REQUEST_CODE_INVENTORY, intent.getIntExtra(EXTRA_REQUEST_CODE_INVENTORY, -1))
        bundle.putInt(EXTRA_INVENTORY, intent.getIntExtra(EXTRA_INVENTORY, -1))
        bundle.putInt(EXTRA_INVENTORY_ORIGIN, intent.getIntExtra(EXTRA_INVENTORY_ORIGIN, -1))

        val fragment = InventoryFragment()
        fragment.arguments = bundle

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
        }
    }
}