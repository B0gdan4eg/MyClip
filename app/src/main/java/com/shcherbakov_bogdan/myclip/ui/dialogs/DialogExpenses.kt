package com.shcherbakov_bogdan.myclip.ui.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.shcherbakov_bogdan.myclip.R
import com.shcherbakov_bogdan.myclip.databinding.TransactionExpensesDialogBinding
import com.shcherbakov_bogdan.myclip.utils.Const
import com.shcherbakov_bogdan.myclip.utils.Const.Companion.DATE_FORMAT
import com.shcherbakov_bogdan.myclip.utils.getDate
import kotlinx.android.synthetic.*


class DialogExpenses : DialogFragment() {

    private lateinit var binding: TransactionExpensesDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TransactionExpensesDialogBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
        datePicker.addOnPositiveButtonClickListener {
            binding.datePicker.text = datePicker.selection?.let { it1 -> getDate(it1, DATE_FORMAT) }
        }

        val navController = NavHostFragment.findNavController(this)

        binding.accountTextView.setOnClickListener {
            navController.navigate(R.id.dialogAccount)
        }

        binding.categoryTextView.setOnClickListener {
            navController.navigate(R.id.dialogCategory)
        }

        binding.datePicker.setOnClickListener{
            datePicker.show(childFragmentManager,"Date")
        }

        return binding.root
    }
}
