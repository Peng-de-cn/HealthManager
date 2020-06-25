package com.example.healthmanager.ui.main.home

import android.content.Intent
import android.os.Bundle
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
import com.example.healthmanager.data.database.MyMedicineDatabase
import com.example.healthmanager.data.database.entity.Medicine
import com.example.healthmanager.databinding.FragmentHomeBinding
import com.example.healthmanager.ui.addmedicine.AddMedicineActivity
import com.example.healthmanager.ui.main.home.item.ItemMedicine
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_MEDICINE
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_SETREMIND
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.doAsync

class HomeFragment : Fragment(), HomeRecyclerViewClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var factory: HomeViewModelFactory

    private lateinit var medicineDB: MyMedicineDatabase

    val onFABClicked = { onFABClicked() }

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
        binding.view = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        medicineDB = MyMedicineDatabase.instance(requireActivity())

        binding.floatingActionButton.setOnClickListener {

        }

        factory = HomeViewModelFactory(medicineDB)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        viewModel.medicinesLiveData.observe(viewLifecycleOwner, Observer { medicines ->
            recyclerview_medicine.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = HomeAdapter(medicines, this)
            }
        })
        binding.viewmodel = viewModel
        L.d("set viewModel")
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadItemMedicine()
    }

    private fun onFABClicked() {
        val intent = Intent(requireActivity(), AddMedicineActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_SETREMIND)
    }

    override fun onRecyclerViewItemClick(view: View, medicine: ItemMedicine) {
        L.d("click")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == REQUEST_CODE_SETREMIND) {
            val medicine = data!!.getParcelableExtra<Medicine>(EXTRA_MEDICINE)
            L.d("done: $medicine")

            doAsync {
                medicineDB.myMedicineDao().insert(medicine)
            }
        }
    }
}