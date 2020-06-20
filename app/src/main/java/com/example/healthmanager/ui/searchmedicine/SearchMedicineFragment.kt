package com.example.healthmanager.ui.searchmedicine

import android.content.Intent
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
import com.example.healthmanager.databinding.FragmentSearchmedicineBinding
import com.example.healthmanager.ui.addmedicine.AddMedicineActivity
import kotlinx.android.synthetic.main.fragment_searchmedicine.*

class SearchMedicineFragment : Fragment(), RecyclerViewClickListener {

    private lateinit var binding: FragmentSearchmedicineBinding
    private lateinit var viewModel: SearchMedicineViewModel
    private lateinit var factory: SearchMedicineViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_searchmedicine, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val repository = MedicineRepository(MedicineDatabase.instance(requireContext()))

        factory = SearchMedicineViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(SearchMedicineViewModel::class.java)

        viewModel.medicinesLiveData.observe(viewLifecycleOwner, Observer { medicines ->
            recyclerview_medicine_name.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = SearchMedicineAdapter(medicines, this)
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
        val intent = Intent(requireContext(), AddMedicineActivity::class.java)
        intent.putExtra(AddMedicineActivity.EXTRA_MEDICINE, medicine)
        startActivity(intent)
    }

}