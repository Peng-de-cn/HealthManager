package com.example.healthmanager.ui.main.medicament

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.healthmanager.L

class MedicamentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        L.d("MedicamentFragment")
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}