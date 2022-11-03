package com.shcherbakov_bogdan.myclip.ui.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.shcherbakov_bogdan.myclip.R
import com.shcherbakov_bogdan.myclip.databinding.TransactionExpensesDialogBinding
import com.shcherbakov_bogdan.myclip.utils.Const.Companion.AMOUNT_PATTERN
import com.shcherbakov_bogdan.myclip.utils.Const.Companion.DATE_FORMAT
import com.shcherbakov_bogdan.myclip.utils.Const.Companion.DATE_FORMAT_WEEK
import com.shcherbakov_bogdan.myclip.utils.getDate
import dagger.android.support.DaggerDialogFragment
import java.util.*
import javax.inject.Inject


class DialogExpenses : DaggerDialogFragment() {

    private lateinit var binding: TransactionExpensesDialogBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ExpensesViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TransactionExpensesDialogBinding.inflate(inflater, container, false)
        binding.datePicker.text = Calendar.getInstance().time.time.let { it1 ->
            getDate(it1, DATE_FORMAT)
        }
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
        datePicker.addOnPositiveButtonClickListener {
            binding.datePicker.text =
                datePicker.selection?.let { it1 -> getDate(it1, DATE_FORMAT) }
        }


        val navController = NavHostFragment.findNavController(this)

        binding.saveButton.setOnClickListener {
            val description = binding.descriptionEditText.text
            val amount = binding.amountEditText.text.toString()
            if (amount.matches(AMOUNT_PATTERN.toRegex())) {
                viewModel.saveTransaction(
                    datePicker.selection?.let { it -> getDate(it, DATE_FORMAT_WEEK) }
                        .toString(),
                    amount.toDouble(),
                    datePicker.selection.toString(),
                    description.toString()
                )
                navController.navigate(R.id.navigation_home)
            }
            Toast.makeText(context, "Amount doesn't match", Toast.LENGTH_SHORT)
                .show()
        }


        binding.accountTextView.setOnClickListener {
            navController.navigate(R.id.dialogAccount)
        }

        binding.categoryTextView.setOnClickListener {
            navController.navigate(R.id.dialogCategory)
        }

        binding.datePicker.setOnClickListener {
            datePicker.show(childFragmentManager, "Date")
        }

        return binding.root
    }
}