package com.example.healthmanager.ui.inventory

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.healthmanager.L
import com.example.healthmanager.R
import com.example.healthmanager.databinding.FragmentInventoryBinding
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_INVENTORY
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_INVENTORY_ORIGIN
import com.example.healthmanager.util.AppConstants.Companion.EXTRA_REQUEST_CODE_INVENTORY
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_INVENTORY
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_INVENTORYLEFT

class InventoryFragment: Fragment() {

    var requestCode: Int = -1
    private var inventory: Int = -1
    private var inventoryOrigin: Int = -1
    private lateinit var binding: FragmentInventoryBinding
    private lateinit var viewModel: InventoryViewModel
    private lateinit var factory: InventoryViewModelFactory

    val onPlusClicked = { onPlusClicked() }
    val onMinusClicked = { onMinusClicked() }
    val onSetClicked = { onSetClicked() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestCode = requireArguments().getInt(EXTRA_REQUEST_CODE_INVENTORY)
        inventory = requireArguments().getInt(EXTRA_INVENTORY)
        inventoryOrigin = requireArguments().getInt(EXTRA_INVENTORY_ORIGIN)

        L.d("requestCode: $requestCode")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_inventory, container, false)
        binding.view = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        factory = InventoryViewModelFactory(inventory)
        viewModel = ViewModelProvider(this, factory).get(InventoryViewModel::class.java)
        binding.viewmodel = viewModel
    }

    private fun onPlusClicked() {
        if (inventoryOrigin != -1) {
            inventory += 1
            if (inventory < inventoryOrigin) {
                inventory += 1
            } else {
                Toast.makeText(requireActivity(),getString(R.string.toast_inventoryerror),Toast.LENGTH_SHORT).show()
                return
            }
        } else {
            inventory += 1
        }

        viewModel.setInventory(inventory)
    }

    private fun onMinusClicked() {
        if (inventory > 2) {
            inventory -= 1
        }
        viewModel.setInventory(inventory)
    }

    private fun onSetClicked() {
        if (inventoryOrigin != -1 && viewModel.inventoryString.get().toString().toInt() > inventoryOrigin) {
            Toast.makeText(requireActivity(),getString(R.string.toast_inventoryerror),Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent()
        intent.putExtra(EXTRA_INVENTORY, viewModel.inventoryString.get().toString().toInt())
        when(requestCode) {
            REQUEST_CODE_INVENTORY -> {
                requireActivity().setResult(REQUEST_CODE_INVENTORY, intent)
            }
            REQUEST_CODE_INVENTORYLEFT -> {
                requireActivity().setResult(REQUEST_CODE_INVENTORYLEFT, intent)
            }
        }

        requireActivity().finish()
    }

}