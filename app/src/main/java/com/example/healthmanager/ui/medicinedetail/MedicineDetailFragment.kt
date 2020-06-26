package com.example.healthmanager.ui.medicinedetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.healthmanager.R
import com.example.healthmanager.data.database.MyMedicineDatabase
import com.example.healthmanager.databinding.FragmentMedicinedetailBinding
import com.example.healthmanager.ui.customremind.CustomRemindActivity
import com.example.healthmanager.ui.main.home.item.ItemMedicine
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_ITEMMEDICINE
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_MAXINUMBEROFTAKING
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_MEDICINE
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_MINNUMBEROFTAKING
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_REQUEST_CODE_ADDMEDICINE
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_TAKINGDOSE
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_TAKINGTIME
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND1
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND2
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND3
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND4
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND5
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_MEDICINEDETAIL

class MedicineDetailFragment : Fragment() {

    private lateinit var itemMedicine: ItemMedicine
    private lateinit var binding: FragmentMedicinedetailBinding
    private lateinit var viewModel: MedicineDetailViewModel
    private lateinit var factory: MedicineDetailViewModelFactory

    val onEditClicked = { onEditClicked() }
    val onDeleteClicked = { onDeleteClicked() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        itemMedicine = requireArguments().getParcelable(EXTRA_ITEMMEDICINE)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_medicinedetail, container, false)
        binding.view = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.itemmedicine = itemMedicine
        binding.medicine = itemMedicine.medicine

        factory = MedicineDetailViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MedicineDetailViewModel::class.java)
    }

    private fun onEditClicked() {
        val medicine = itemMedicine.medicine
        when (itemMedicine.takingCount) {
            1 -> {
                val intent = Intent(requireContext(), CustomRemindActivity::class.java)
                intent.putExtra(EXTRA_REQUEST_CODE_ADDMEDICINE, REQUEST_CODE_CUSTOMREMIND1)
                intent.putExtra(EXTRA_TAKINGTIME, medicine.takingTime1)
                intent.putExtra(EXTRA_TAKINGDOSE, medicine.takingDose1)
                intent.putExtra(EXTRA_MAXINUMBEROFTAKING, medicine.maxiNumberOfTaking)
                intent.putExtra(EXTRA_MINNUMBEROFTAKING, medicine.minNumberOfTaking)
                startActivityForResult(intent, REQUEST_CODE_CUSTOMREMIND1)
            }
            2 -> {
                val intent = Intent(requireContext(), CustomRemindActivity::class.java)
                intent.putExtra(EXTRA_REQUEST_CODE_ADDMEDICINE, REQUEST_CODE_CUSTOMREMIND2)
                intent.putExtra(EXTRA_TAKINGTIME, medicine.takingTime2)
                intent.putExtra(EXTRA_TAKINGDOSE, medicine.takingDose2)
                intent.putExtra(EXTRA_MAXINUMBEROFTAKING, medicine.maxiNumberOfTaking)
                intent.putExtra(EXTRA_MINNUMBEROFTAKING, medicine.minNumberOfTaking)
                startActivityForResult(intent, REQUEST_CODE_CUSTOMREMIND2)
            }
            3 -> {
                val intent = Intent(requireContext(), CustomRemindActivity::class.java)
                intent.putExtra(EXTRA_REQUEST_CODE_ADDMEDICINE, REQUEST_CODE_CUSTOMREMIND3)
                intent.putExtra(EXTRA_TAKINGTIME, medicine.takingTime3)
                intent.putExtra(EXTRA_TAKINGDOSE, medicine.takingDose3)
                intent.putExtra(EXTRA_MAXINUMBEROFTAKING, medicine.maxiNumberOfTaking)
                intent.putExtra(EXTRA_MINNUMBEROFTAKING, medicine.minNumberOfTaking)
                startActivityForResult(intent, REQUEST_CODE_CUSTOMREMIND3)
            }
            4 -> {
                val intent = Intent(requireContext(), CustomRemindActivity::class.java)
                intent.putExtra(EXTRA_REQUEST_CODE_ADDMEDICINE, REQUEST_CODE_CUSTOMREMIND4)
                intent.putExtra(EXTRA_TAKINGTIME, medicine.takingTime4)
                intent.putExtra(EXTRA_TAKINGDOSE, medicine.takingDose4)
                intent.putExtra(EXTRA_MAXINUMBEROFTAKING, medicine.maxiNumberOfTaking)
                intent.putExtra(EXTRA_MINNUMBEROFTAKING, medicine.minNumberOfTaking)
                startActivityForResult(intent, REQUEST_CODE_CUSTOMREMIND4)
            }
            5 -> {
                val intent = Intent(requireContext(), CustomRemindActivity::class.java)
                intent.putExtra(EXTRA_REQUEST_CODE_ADDMEDICINE, REQUEST_CODE_CUSTOMREMIND5)
                intent.putExtra(EXTRA_TAKINGTIME, medicine.takingTime5)
                intent.putExtra(EXTRA_TAKINGDOSE, medicine.takingDose5)
                intent.putExtra(EXTRA_MAXINUMBEROFTAKING, medicine.maxiNumberOfTaking)
                intent.putExtra(EXTRA_MINNUMBEROFTAKING, medicine.minNumberOfTaking)
                startActivityForResult(intent, REQUEST_CODE_CUSTOMREMIND5)
            }
        }

    }

    private fun onDeleteClicked() {
        val medicine = itemMedicine.medicine
        when (itemMedicine.takingCount) {
            1 -> {
                medicine.takingTime1 = ""
                medicine.takingDose1 = 0
            }
            2 -> {
                medicine.takingTime2 = ""
                medicine.takingDose2 = 0
            }
            3 -> {
                medicine.takingTime3 = ""
                medicine.takingDose3 = 0
            }
            4 -> {
                medicine.takingTime4 = ""
                medicine.takingDose4 = 0
            }
            5 -> {
                medicine.takingTime5 = ""
                medicine.takingDose5 = 0
            }
        }

        val intent = Intent()
        intent.putExtra(EXTRA_MEDICINE, medicine)
        requireActivity().setResult(REQUEST_CODE_MEDICINEDETAIL, intent)
        requireActivity().finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val myMedicineDB = MyMedicineDatabase.instance(requireActivity())
        if (resultCode == REQUEST_CODE_CUSTOMREMIND1 || resultCode == REQUEST_CODE_CUSTOMREMIND2
            || resultCode == REQUEST_CODE_CUSTOMREMIND3 || resultCode == REQUEST_CODE_CUSTOMREMIND4
            || resultCode == REQUEST_CODE_CUSTOMREMIND5) {
            val takingTime = data!!.getStringExtra(EXTRA_TAKINGTIME)!!
            val takingDose = data.getIntExtra(EXTRA_TAKINGDOSE, -1)
            viewModel.updateMedicine(myMedicineDB, itemMedicine.medicine, resultCode, takingTime, takingDose)
            itemMedicine.takingTime = takingTime
            itemMedicine.takingDose = takingDose
            binding.itemmedicine = itemMedicine
        }
    }
}