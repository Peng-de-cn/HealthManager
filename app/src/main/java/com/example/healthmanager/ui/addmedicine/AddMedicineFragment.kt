package com.example.healthmanager.ui.addmedicine

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthmanager.L
import com.example.healthmanager.R
import com.example.healthmanager.data.database.MedicineDatabase
import com.example.healthmanager.data.database.entity.Medicine
import com.example.healthmanager.data.repository.MedicineRepository
import com.example.healthmanager.databinding.FragmentAddmedicineBinding
import kotlinx.android.synthetic.main.fragment_addmedicine.*

class AddMedicineFragment : Fragment(), RecyclerViewClickListener {

    private lateinit var binding: FragmentAddmedicineBinding
    private lateinit var viewModel: AddMedicineViewModel
    private lateinit var factory: AddMedicineViewModelFactory

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
        L.d("click")
    }

}