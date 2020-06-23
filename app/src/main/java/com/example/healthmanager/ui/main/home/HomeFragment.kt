package com.example.healthmanager.ui.main.home

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.healthmanager.L
import com.example.healthmanager.R
import com.example.healthmanager.broadcast.AlarmReceiver
import com.example.healthmanager.data.database.entity.Medicine
import com.example.healthmanager.databinding.FragmentHomeBinding
import com.example.healthmanager.ui.addmedicine.AddMedicineActivity
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_CHANNEL_ID
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_CHANNEL_NAME
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_MEDICINE
import com.example.healthmanager.util.AppConstants.Companion.NOTIFICATION_CHANNEL_ID1
import com.example.healthmanager.util.AppConstants.Companion.NOTIFICATION_CHANNEL_ID2
import com.example.healthmanager.util.AppConstants.Companion.NOTIFICATION_CHANNEL_NAME1
import com.example.healthmanager.util.AppConstants.Companion.NOTIFICATION_CHANNEL_NAME2
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_SETREMIND
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
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

    private fun startRemind1(){
        val systemTime = System.currentTimeMillis()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = systemTime

        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 20)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
//        val selectTime = calendar.timeInMillis
//
//        // 如果当前时间大于设置的时间，那么就从第二天的设定时间开始
//        if(systemTime > selectTime) {
//            calendar.add(Calendar.DAY_OF_MONTH, 1)
//        }

        val intent = Intent(requireActivity(), AlarmReceiver::class.java)
        intent.putExtra(EXTRA_CHANNEL_ID, NOTIFICATION_CHANNEL_ID1)
        intent.putExtra(EXTRA_CHANNEL_NAME, NOTIFICATION_CHANNEL_NAME1)
        val pi = PendingIntent.getBroadcast(requireActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val am = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        L.d("time: ${calendar.timeInMillis}")
        am.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pi)
    }

    private fun startRemind2(){
        val systemTime = System.currentTimeMillis()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = systemTime

        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 21)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val selectTime = calendar.timeInMillis

        // 如果当前时间大于设置的时间，那么就从第二天的设定时间开始
        if(systemTime > selectTime) {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        val intent = Intent(requireActivity(), AlarmReceiver::class.java)
        intent.putExtra(EXTRA_CHANNEL_ID, NOTIFICATION_CHANNEL_ID2)
        intent.putExtra(EXTRA_CHANNEL_NAME, NOTIFICATION_CHANNEL_NAME2)
        val pi = PendingIntent.getBroadcast(requireActivity(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val am = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        L.d("time: $selectTime")
        am.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pi)
    }

}