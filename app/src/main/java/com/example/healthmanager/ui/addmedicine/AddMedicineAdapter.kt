package com.example.healthmanager.ui.addmedicine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.healthmanager.R
import com.example.healthmanager.data.database.entity.Medicine
import com.example.healthmanager.databinding.ItemMedicineNameBinding

class AddMedicineAdapter(private val medicines: List<Medicine>, private val listener: AddMedicineRecyclerViewClickListener): RecyclerView.Adapter<AddMedicineAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMedicineNameBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_medicine_name, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = medicines.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.medicine = medicines[position]
        holder.binding.cardMedicineName.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.binding.cardMedicineName, medicines[position])
        }
    }


    inner class ViewHolder(val binding: ItemMedicineNameBinding) : RecyclerView.ViewHolder(binding.root)
}