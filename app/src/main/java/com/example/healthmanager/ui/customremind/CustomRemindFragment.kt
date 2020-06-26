package com.example.healthmanager.ui.customremind

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.healthmanager.R
import com.example.healthmanager.databinding.FragmentCustomremindBinding
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_MAXINUMBEROFTAKING
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_MINNUMBEROFTAKING
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_REQUEST_CODE_ADDMEDICINE
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_TAKINGDOSE
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_TAKINGTIME
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND1
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND2
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND3
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND4
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND5


class CustomRemindFragment: Fragment() {

    private var requestCode: Int = -1
    private lateinit var takingTime: String
    private var takingDose: Int = -1
    private var maxiNumberOfTaking = 1
    private var minNumberOfTaking = 1
    private lateinit var binding: FragmentCustomremindBinding
    private lateinit var viewModel: CustomRemindViewModel
    private lateinit var factory: CustomRemindViewModelFactory

    val onPlusClicked = { onPlusClicked() }
    val onMinusClicked = { onMinusClicked() }
    val onSetClicked = { onSetClicked() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestCode = requireArguments().getInt(EXTRA_REQUEST_CODE_ADDMEDICINE)
        takingTime = requireArguments().getString(EXTRA_TAKINGTIME)!!
        takingDose = requireArguments().getInt(EXTRA_TAKINGDOSE)
        maxiNumberOfTaking = requireArguments().getInt(EXTRA_MAXINUMBEROFTAKING)
        minNumberOfTaking = requireArguments().getInt(EXTRA_MINNUMBEROFTAKING)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_customremind, container, false)
        binding.view = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        factory = CustomRemindViewModelFactory(takingDose)
        viewModel = ViewModelProvider(this, factory).get(CustomRemindViewModel::class.java)
        binding.viewmodel = viewModel

        binding.timePicker.setIs24HourView(true)
        val time = takingTime.split(":".toRegex()).toTypedArray()
        val hour = time[0].toInt()
        val minute = time[1].toInt()
        binding.timePicker.hour = hour
        binding.timePicker.minute = minute
    }

    private fun onPlusClicked() {
        if (takingDose < maxiNumberOfTaking) {
            takingDose += 1
        } else {
            Toast.makeText(requireActivity(),getString(R.string.toast_maxiTake, maxiNumberOfTaking.toString()),Toast.LENGTH_SHORT).show()
        }
        viewModel.setTakingDose(takingDose)
    }

    private fun onMinusClicked() {
        if (takingDose > minNumberOfTaking) {
            takingDose -= 1
        } else {
            Toast.makeText(requireActivity(),getString(R.string.toast_minTake, minNumberOfTaking.toString()),Toast.LENGTH_SHORT).show()
        }
        viewModel.setTakingDose(takingDose)
    }

    private fun onSetClicked() {
        val time = String.format("%02d:%02d", binding.timePicker.hour, binding.timePicker.minute)
        val intent = Intent()
        intent.putExtra(EXTRA_TAKINGTIME, time)
        intent.putExtra(EXTRA_TAKINGDOSE, viewModel.takingDoseString.get().toString().toInt())
        when(requestCode) {
            REQUEST_CODE_CUSTOMREMIND1 -> {
                requireActivity().setResult(REQUEST_CODE_CUSTOMREMIND1, intent)
            }
            REQUEST_CODE_CUSTOMREMIND2 -> {
                requireActivity().setResult(REQUEST_CODE_CUSTOMREMIND2, intent)
            }
            REQUEST_CODE_CUSTOMREMIND3-> {
                requireActivity().setResult(REQUEST_CODE_CUSTOMREMIND3, intent)
            }
            REQUEST_CODE_CUSTOMREMIND4 -> {
                requireActivity().setResult(REQUEST_CODE_CUSTOMREMIND4, intent)
            }
            REQUEST_CODE_CUSTOMREMIND5 -> {
                requireActivity().setResult(REQUEST_CODE_CUSTOMREMIND5, intent)
            }
        }

        requireActivity().finish()
    }
}