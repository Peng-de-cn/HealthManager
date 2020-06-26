package com.example.healthmanager.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.healthmanager.R
import com.example.healthmanager.databinding.ItemMedicineBinding
import com.example.healthmanager.ui.main.home.item.ItemMedicine

class HomeAdapter(private val medicines: List<ItemMedicine>, private val listener: HomeRecyclerViewClickListener): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val binding: ItemMedicineBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_medicine, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = medicines.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.medicine = medicines[position]
        holder.binding.cardMedicine.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.binding.cardMedicine, medicines[position])
        }
    }

    inner class ViewHolder(val binding: ItemMedicineBinding) : RecyclerView.ViewHolder(binding.root)
}