package com.example.healthmanager.ui.addmedicine

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableBoolean
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.example.healthmanager.R
import com.example.healthmanager.data.database.MedicineDatabase
import com.example.healthmanager.data.database.entity.Medicine
import com.example.healthmanager.data.repository.MedicineRepository
import com.example.healthmanager.databinding.FragmentAddmedicineBinding
import com.example.healthmanager.ui.customremind.CustomRemindActivity
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_REQUEST_CODE
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_TAKINGDOSE
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_TAKINGTIME
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND1
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND2
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND3
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND4
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND5
import com.example.healthmanager.util.RotateArrow
import com.example.healthmanager.util.hideSoftKeyBoard
import kotlinx.android.synthetic.main.fragment_addmedicine.*

class AddMedicineFragment: Fragment(),
    RecyclerViewClickListener {

    private lateinit var medicine: Medicine
    private lateinit var binding: FragmentAddmedicineBinding
    private lateinit var viewModel: AddMedicineViewModel
    private lateinit var factory: AddMedicineViewModelFactory

    val medicineSelected: ObservableBoolean = ObservableBoolean(false)
    val showDetails: ObservableBoolean = ObservableBoolean(true)
    val onMedicineEditClicked = { onMedicineEditClicked() }
    val onArrowClicked = { onArrowClicked() }
    val onTakingTime1LayoutClicked = { onTakingTime1LayoutClicked() }
    val onTakingTime2LayoutClicked = { onTakingTime2LayoutClicked() }
    val onTakingTime3LayoutClicked = { onTakingTime3LayoutClicked() }
    val onTakingTime4LayoutClicked = { onTakingTime4LayoutClicked() }
    val onTakingTime5LayoutClicked = { onTakingTime5LayoutClicked() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_addmedicine, container, false)
        binding.view = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val repository = MedicineRepository(MedicineDatabase.instance(requireContext()))

        factory = AddMedicineViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(AddMedicineViewModel::class.java)
        viewModel.medicinesLiveData.observe(viewLifecycleOwner, Observer { medicines ->
            recyclerview_medicine_name.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = AddMedicineAdapter(medicines, this)
            }
        })
        binding.viewmodel = viewModel

        binding.search.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.searchMedicine(s.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
    }

    override fun onRecyclerViewItemClick(view: View, medicine: Medicine) {
        hideSoftKeyBoard(requireActivity(), binding.search)
        this.medicine = medicine
        binding.model = medicine
        medicineSelected.set(true)
    }

    private fun onMedicineEditClicked() {
        medicineSelected.set(false)
    }

    private fun onArrowClicked() {
        TransitionManager.beginDelayedTransition(binding.transitionsContainer);

        if (showDetails.get()) {
            showDetails.set(false)
            RotateArrow(binding.imageArrow, false)
        } else {
            showDetails.set(true)
            RotateArrow(binding.imageArrow, true)
        }
    }


    private fun onTakingTime1LayoutClicked() {
        val intent = Intent(requireContext(), CustomRemindActivity::class.java)
        intent.putExtra(EXTRA_REQUEST_CODE, REQUEST_CODE_CUSTOMREMIND1)
        intent.putExtra(EXTRA_TAKINGTIME, medicine.takingTime1)
        intent.putExtra(EXTRA_TAKINGDOSE, medicine.takingDose1)
        startActivityForResult(intent, REQUEST_CODE_CUSTOMREMIND1)
    }

    private fun onTakingTime2LayoutClicked() {
        val intent = Intent(requireContext(), CustomRemindActivity::class.java)
        intent.putExtra(EXTRA_REQUEST_CODE, REQUEST_CODE_CUSTOMREMIND2)
        intent.putExtra(EXTRA_TAKINGTIME, medicine.takingTime2)
        intent.putExtra(EXTRA_TAKINGDOSE, medicine.takingDose2)
        startActivityForResult(intent, REQUEST_CODE_CUSTOMREMIND2)
    }

    private fun onTakingTime3LayoutClicked() {
        val intent = Intent(requireContext(), CustomRemindActivity::class.java)
        intent.putExtra(EXTRA_REQUEST_CODE, REQUEST_CODE_CUSTOMREMIND3)
        intent.putExtra(EXTRA_TAKINGTIME, medicine.takingTime3)
        intent.putExtra(EXTRA_TAKINGDOSE, medicine.takingDose3)
        startActivityForResult(intent, REQUEST_CODE_CUSTOMREMIND3)
    }

    private fun onTakingTime4LayoutClicked() {
        val intent = Intent(requireContext(), CustomRemindActivity::class.java)
        intent.putExtra(EXTRA_REQUEST_CODE, REQUEST_CODE_CUSTOMREMIND4)
        intent.putExtra(EXTRA_TAKINGTIME, medicine.takingTime4)
        intent.putExtra(EXTRA_TAKINGDOSE, medicine.takingDose4)
        startActivityForResult(intent, REQUEST_CODE_CUSTOMREMIND4)
    }

    private fun onTakingTime5LayoutClicked() {
        val intent = Intent(requireContext(), CustomRemindActivity::class.java)
        intent.putExtra(EXTRA_REQUEST_CODE, REQUEST_CODE_CUSTOMREMIND5)
        intent.putExtra(EXTRA_TAKINGTIME, medicine.takingTime5)
        intent.putExtra(EXTRA_TAKINGDOSE, medicine.takingDose5)
        startActivityForResult(intent, REQUEST_CODE_CUSTOMREMIND5)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val medicineDB = MedicineDatabase.instance(requireActivity())
        if (resultCode == REQUEST_CODE_CUSTOMREMIND1 || resultCode == REQUEST_CODE_CUSTOMREMIND2
            || resultCode == REQUEST_CODE_CUSTOMREMIND3 || resultCode == REQUEST_CODE_CUSTOMREMIND4
            || resultCode == REQUEST_CODE_CUSTOMREMIND5) {
            viewModel.updateMedicine(medicineDB, medicine, resultCode, data!!.getStringExtra(EXTRA_TAKINGTIME)!!, data.getIntExtra(EXTRA_TAKINGDOSE, -1))
            binding.model = medicine
        }
    }
}