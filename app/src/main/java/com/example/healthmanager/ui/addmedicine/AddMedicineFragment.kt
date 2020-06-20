package com.example.healthmanager.ui.addmedicine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.healthmanager.L
import com.example.healthmanager.R
import com.example.healthmanager.data.database.entity.Medicine
import com.example.healthmanager.databinding.FragmentAddmedicineBinding

class AddMedicineFragment: Fragment() {

    private lateinit var medicine: Medicine
    private lateinit var binding: FragmentAddmedicineBinding
    private lateinit var viewModel: AddMedicineViewModel
    private lateinit var factory: AddMedicineViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        medicine = requireArguments().getParcelable<Medicine>(AddMedicineActivity.EXTRA_MEDICINE) as Medicine
        L.d("AddMedicineFragment: $medicine")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_addmedicine, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        factory = AddMedicineViewModelFactory(medicine, binding.transitionsContainer, binding.imageArrow)
        viewModel = ViewModelProvider(this, factory).get(AddMedicineViewModel::class.java)
        binding.viewmodel = viewModel
        binding.model = medicine
    }
}