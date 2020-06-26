package com.example.healthmanager.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.healthmanager.R
import com.example.healthmanager.databinding.ItemMedicineBinding
import com.example.healthmanager.ui.main.home.item.ItemMedicine
import java.util.*

class HomeAdapter(private val medicines: List<ItemMedicine>, private val listener: HomeRecyclerViewClickListener): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val binding: ItemMedicineBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_medicine, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = medicines.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.medicine = medicines[position]

        if (isTakingTimePassed(medicines[position].takingTime!!)) {
            holder.binding.layoutTakingtime.setBackgroundColor(ContextCompat.getColor(holder.binding.root.context, R.color.colorPrimaryDark))
        } else {
            holder.binding.layoutTakingtime.setBackgroundColor(ContextCompat.getColor(holder.binding.root.context, R.color.global_color_blue_primary_dark))
        }
        holder.binding.cardMedicine.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.binding.cardMedicine, medicines[position])
        }
    }

    private fun isTakingTimePassed(takingTime: String): Boolean {
        val time = takingTime.split(":".toRegex()).toTypedArray()
        val hour = time[0].toInt()
        val minute = time[1].toInt()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()

        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val selectTime = calendar.timeInMillis

        return System.currentTimeMillis() > selectTime
    }

    inner class ViewHolder(val binding: ItemMedicineBinding) : RecyclerView.ViewHolder(binding.root)
}