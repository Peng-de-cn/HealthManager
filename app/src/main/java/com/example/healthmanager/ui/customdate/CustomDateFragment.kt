package com.example.healthmanager.ui.customdate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.healthmanager.R
import com.example.healthmanager.data.database.entity.Medicine
import com.example.healthmanager.databinding.FragmentCustomdateBinding

class CustomDateFragment: Fragment() {

    private lateinit var medicine: Medicine
    private lateinit var binding: FragmentCustomdateBinding
    private lateinit var viewModel: CustomDateViewModel
    private lateinit var factory: CustomDateViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_customdate, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        factory = CustomDateViewModelFactory(medicine)
        viewModel = ViewModelProvider(this, factory).get(CustomDateViewModel::class.java)
        binding.viewmodel = viewModel
        binding.model = medicine
    }
}