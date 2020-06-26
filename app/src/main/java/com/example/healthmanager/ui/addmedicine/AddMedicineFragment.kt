package com.example.healthmanager.ui.addmedicine

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.content.Context
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
import com.example.healthmanager.broadcast.AlarmReceiver
import com.example.healthmanager.data.database.MedicineDatabase
import com.example.healthmanager.data.database.entity.Medicine
import com.example.healthmanager.data.repository.MedicineRepository
import com.example.healthmanager.databinding.FragmentAddmedicineBinding
import com.example.healthmanager.ui.customremind.CustomRemindActivity
import com.example.healthmanager.ui.inventory.InventoryActivity
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_CHANNEL_ID
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_CHANNEL_NAME
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_CONTENT_TEXT
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_CONTENT_TITLE
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_INVENTORY
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_INVENTORY_ORIGIN
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_MAXINUMBEROFTAKING
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_MEDICINE
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_MINNUMBEROFTAKING
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_REQUEST_CODE_ADDMEDICINE
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_REQUEST_CODE_INVENTORY
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_TAKINGDOSE
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_TAKINGTIME
import com.example.healthmanager.util.AppConstants.Companion.NOTIFICATION_CHANNEL_ID1
import com.example.healthmanager.util.AppConstants.Companion.NOTIFICATION_CHANNEL_ID2
import com.example.healthmanager.util.AppConstants.Companion.NOTIFICATION_CHANNEL_ID3
import com.example.healthmanager.util.AppConstants.Companion.NOTIFICATION_CHANNEL_ID4
import com.example.healthmanager.util.AppConstants.Companion.NOTIFICATION_CHANNEL_ID5
import com.example.healthmanager.util.AppConstants.Companion.NOTIFICATION_CHANNEL_NAME1
import com.example.healthmanager.util.AppConstants.Companion.NOTIFICATION_CHANNEL_NAME2
import com.example.healthmanager.util.AppConstants.Companion.NOTIFICATION_CHANNEL_NAME3
import com.example.healthmanager.util.AppConstants.Companion.NOTIFICATION_CHANNEL_NAME4
import com.example.healthmanager.util.AppConstants.Companion.NOTIFICATION_CHANNEL_NAME5
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND1
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND2
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND3
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND4
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND5
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_INVENTORY
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_INVENTORYLEFT
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_SETREMIND
import com.example.healthmanager.util.RotateArrow
import com.example.healthmanager.util.hideSoftKeyBoard
import kotlinx.android.synthetic.main.fragment_addmedicine.*
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

class AddMedicineFragment : Fragment(),
    AddMedicineRecyclerViewClickListener {

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
    val onInventoryClicked = { onInventoryClicked() }
    val onInventoryLeftClicked = { onInventoryLeftClicked() }
    val onDateLayoutClicked = { onDateLayoutClicked() }
    val onDoneClicked = { onDoneClicked() }

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

        binding.search.addTextChangedListener(object : TextWatcher {
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
        val data = Date()
        val sdf: Format = SimpleDateFormat("MM-dd")
        medicine.date = sdf.format(data)
        this.medicine = medicine
        binding.model = this.medicine
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
        intent.putExtra(EXTRA_REQUEST_CODE_ADDMEDICINE, REQUEST_CODE_CUSTOMREMIND1)
        intent.putExtra(EXTRA_TAKINGTIME, medicine.takingTime1)
        intent.putExtra(EXTRA_TAKINGDOSE, medicine.takingDose1)
        intent.putExtra(EXTRA_MAXINUMBEROFTAKING, medicine.maxiNumberOfTaking)
        intent.putExtra(EXTRA_MINNUMBEROFTAKING, medicine.minNumberOfTaking)
        startActivityForResult(intent, REQUEST_CODE_CUSTOMREMIND1)
    }

    private fun onTakingTime2LayoutClicked() {
        val intent = Intent(requireContext(), CustomRemindActivity::class.java)
        intent.putExtra(EXTRA_REQUEST_CODE_ADDMEDICINE, REQUEST_CODE_CUSTOMREMIND2)
        intent.putExtra(EXTRA_TAKINGTIME, medicine.takingTime2)
        intent.putExtra(EXTRA_TAKINGDOSE, medicine.takingDose2)
        intent.putExtra(EXTRA_MAXINUMBEROFTAKING, medicine.maxiNumberOfTaking)
        intent.putExtra(EXTRA_MINNUMBEROFTAKING, medicine.minNumberOfTaking)
        startActivityForResult(intent, REQUEST_CODE_CUSTOMREMIND2)
    }

    private fun onTakingTime3LayoutClicked() {
        val intent = Intent(requireContext(), CustomRemindActivity::class.java)
        intent.putExtra(EXTRA_REQUEST_CODE_ADDMEDICINE, REQUEST_CODE_CUSTOMREMIND3)
        intent.putExtra(EXTRA_TAKINGTIME, medicine.takingTime3)
        intent.putExtra(EXTRA_TAKINGDOSE, medicine.takingDose3)
        intent.putExtra(EXTRA_MAXINUMBEROFTAKING, medicine.maxiNumberOfTaking)
        intent.putExtra(EXTRA_MINNUMBEROFTAKING, medicine.minNumberOfTaking)
        startActivityForResult(intent, REQUEST_CODE_CUSTOMREMIND3)
    }

    private fun onTakingTime4LayoutClicked() {
        val intent = Intent(requireContext(), CustomRemindActivity::class.java)
        intent.putExtra(EXTRA_REQUEST_CODE_ADDMEDICINE, REQUEST_CODE_CUSTOMREMIND4)
        intent.putExtra(EXTRA_TAKINGTIME, medicine.takingTime4)
        intent.putExtra(EXTRA_TAKINGDOSE, medicine.takingDose4)
        intent.putExtra(EXTRA_MAXINUMBEROFTAKING, medicine.maxiNumberOfTaking)
        intent.putExtra(EXTRA_MINNUMBEROFTAKING, medicine.minNumberOfTaking)
        startActivityForResult(intent, REQUEST_CODE_CUSTOMREMIND4)
    }

    private fun onTakingTime5LayoutClicked() {
        val intent = Intent(requireContext(), CustomRemindActivity::class.java)
        intent.putExtra(EXTRA_REQUEST_CODE_ADDMEDICINE, REQUEST_CODE_CUSTOMREMIND5)
        intent.putExtra(EXTRA_TAKINGTIME, medicine.takingTime5)
        intent.putExtra(EXTRA_TAKINGDOSE, medicine.takingDose5)
        intent.putExtra(EXTRA_MAXINUMBEROFTAKING, medicine.maxiNumberOfTaking)
        intent.putExtra(EXTRA_MINNUMBEROFTAKING, medicine.minNumberOfTaking)
        startActivityForResult(intent, REQUEST_CODE_CUSTOMREMIND5)
    }

    private fun onDateLayoutClicked() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            requireActivity(),
            DatePickerDialog.OnDateSetListener { _, _, month, dayOfMonth ->
                val date = String.format("%02d-%02d", month + 1, dayOfMonth)
                medicine.date = date
                binding.model = medicine
            },
            year,
            month,
            day
        )
        dpd.datePicker.minDate = c.time.time
        dpd.show()
    }

    private fun onInventoryClicked() {
        val intent = Intent(requireContext(), InventoryActivity::class.java)
        intent.putExtra(EXTRA_REQUEST_CODE_INVENTORY, REQUEST_CODE_INVENTORY)
        intent.putExtra(EXTRA_INVENTORY, medicine.inventory)
        startActivityForResult(intent, REQUEST_CODE_INVENTORY)
    }

    private fun onInventoryLeftClicked() {
        val intent = Intent(requireContext(), InventoryActivity::class.java)
        intent.putExtra(EXTRA_REQUEST_CODE_INVENTORY, REQUEST_CODE_INVENTORYLEFT)
        intent.putExtra(EXTRA_INVENTORY, medicine.inventoryLeft)
        intent.putExtra(EXTRA_INVENTORY_ORIGIN, medicine.inventory)
        startActivityForResult(intent, REQUEST_CODE_INVENTORYLEFT)
    }

    private fun onDoneClicked() {
        setRemind()
        val intent = Intent()
        intent.putExtra(EXTRA_MEDICINE, medicine)
        requireActivity().setResult(REQUEST_CODE_SETREMIND, intent)
        requireActivity().finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val medicineDB = MedicineDatabase.instance(requireActivity())
        if (resultCode == REQUEST_CODE_CUSTOMREMIND1 || resultCode == REQUEST_CODE_CUSTOMREMIND2
            || resultCode == REQUEST_CODE_CUSTOMREMIND3 || resultCode == REQUEST_CODE_CUSTOMREMIND4
            || resultCode == REQUEST_CODE_CUSTOMREMIND5
        ) {
            viewModel.updateMedicine(
                medicineDB,
                medicine,
                resultCode,
                data!!.getStringExtra(EXTRA_TAKINGTIME)!!,
                data.getIntExtra(EXTRA_TAKINGDOSE, -1)
            )
            binding.model = medicine
        } else if (resultCode == REQUEST_CODE_INVENTORY) {
            viewModel.updateMedicine(
                medicineDB,
                medicine,
                resultCode,
                data!!.getIntExtra(EXTRA_INVENTORY, -1),
                -1
            )
            binding.model = medicine
        } else if (resultCode == REQUEST_CODE_INVENTORYLEFT) {
            viewModel.updateMedicine(
                medicineDB,
                medicine,
                resultCode,
                -1,
                data!!.getIntExtra(EXTRA_INVENTORY, -1)
            )
            binding.model = medicine
        }
    }

    private fun setRemind() {
        if (medicine.takingTime1!!.isNotEmpty()) {
            val time = medicine.takingTime1!!.split(":".toRegex()).toTypedArray()
            val hour = time[0].toInt()
            val minute = time[1].toInt()
            setAlarm(
                REQUEST_CODE_CUSTOMREMIND1,
                hour,
                minute,
                NOTIFICATION_CHANNEL_ID1,
                NOTIFICATION_CHANNEL_NAME1,
                medicine.name!!,
                getString(R.string.label_take, medicine.takingDose1!!.toString())
            )
        }
        if (medicine.takingTime2!!.isNotEmpty()) {
            val time = medicine.takingTime2!!.split(":".toRegex()).toTypedArray()
            val hour = time[0].toInt()
            val minute = time[1].toInt()
            setAlarm(
                REQUEST_CODE_CUSTOMREMIND2,
                hour,
                minute,
                NOTIFICATION_CHANNEL_ID2,
                NOTIFICATION_CHANNEL_NAME2,
                medicine.name!!,
                getString(R.string.label_take, medicine.takingDose2!!.toString())
            )
        }
        if (medicine.takingTime3!!.isNotEmpty()) {
            val time = medicine.takingTime3!!.split(":".toRegex()).toTypedArray()
            val hour = time[0].toInt()
            val minute = time[1].toInt()
            setAlarm(
                REQUEST_CODE_CUSTOMREMIND3,
                hour,
                minute,
                NOTIFICATION_CHANNEL_ID3,
                NOTIFICATION_CHANNEL_NAME3,
                medicine.name!!,
                getString(R.string.label_take, medicine.takingDose3!!.toString())
            )
        }
        if (medicine.takingTime4!!.isNotEmpty()) {
            val time = medicine.takingTime4!!.split(":".toRegex()).toTypedArray()
            val hour = time[0].toInt()
            val minute = time[1].toInt()
            setAlarm(
                REQUEST_CODE_CUSTOMREMIND4,
                hour,
                minute,
                NOTIFICATION_CHANNEL_ID4,
                NOTIFICATION_CHANNEL_NAME4,
                medicine.name!!,
                getString(R.string.label_take, medicine.takingDose4!!.toString())
            )
        }
        if (medicine.takingTime5!!.isNotEmpty()) {
            val time = medicine.takingTime5!!.split(":".toRegex()).toTypedArray()
            val hour = time[0].toInt()
            val minute = time[1].toInt()
            setAlarm(
                REQUEST_CODE_CUSTOMREMIND5,
                hour,
                minute,
                NOTIFICATION_CHANNEL_ID5,
                NOTIFICATION_CHANNEL_NAME5,
                medicine.name!!,
                getString(R.string.label_take, medicine.takingDose5!!.toString())
            )
        }
    }

    private fun setAlarm(
        requestCode: Int,
        hour: Int,
        minute: Int,
        channelId: String,
        channelName: String,
        contentTitle: String,
        contentText: String
    ) {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()

        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val selectTime = calendar.timeInMillis

        // 如果当前时间大于设置的时间，那么就从第二天的设定时间开始
        if (System.currentTimeMillis() > selectTime) {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        val intent = Intent(requireActivity(), AlarmReceiver::class.java)
        intent.putExtra(EXTRA_CHANNEL_ID, channelId)
        intent.putExtra(EXTRA_CHANNEL_NAME, channelName)
        intent.putExtra(EXTRA_CONTENT_TITLE, contentTitle)
        intent.putExtra(EXTRA_CONTENT_TEXT, contentText)
        val pi = PendingIntent.getBroadcast(
            requireActivity(),
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val am = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        am.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pi)
    }
}