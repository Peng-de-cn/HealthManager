package com.example.healthmanager.ui.addmedicine

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.transition.TransitionManager
import com.example.healthmanager.data.database.entity.Medicine
import com.example.healthmanager.util.RotateArrow

class AddMedicineViewModel(
    private val medicine: Medicine,
    private val transitionsContainer: ConstraintLayout,
    private val imageArrow: ImageView
): ViewModel() {

    val showDetails: ObservableBoolean = ObservableBoolean(true)
    val onArrowClicked = { onArrowClicked() }

    private fun onArrowClicked() {
        TransitionManager.beginDelayedTransition(transitionsContainer);

        if (showDetails.get()) {
            showDetails.set(false)
            RotateArrow(imageArrow, false)
        } else {
            showDetails.set(true)
            RotateArrow(imageArrow, true)
        }
    }
}