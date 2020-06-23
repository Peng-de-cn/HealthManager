package com.example.healthmanager.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.healthmanager.L
import com.example.healthmanager.R
import com.example.healthmanager.data.database.entity.Medicine
import com.example.healthmanager.databinding.FragmentHomeBinding
import com.example.healthmanager.ui.addmedicine.AddMedicineActivity
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_MEDICINE
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_SETREMIND

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(binding.root.context, AddMedicineActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_SETREMIND)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == REQUEST_CODE_SETREMIND) {
            val medicine = data!!.getParcelableExtra<Medicine>(EXTRA_MEDICINE)
            L.d("done: $medicine")
        }
}

}