package com.shcherbakov_bogdan.myclip.ui.fragments.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.shcherbakov_bogdan.myclip.R
import com.shcherbakov_bogdan.myclip.databinding.FragmentCurrencyBinding
import com.shcherbakov_bogdan.myclip.utils.Rates
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CurrencyFragment : DaggerFragment() {

    private lateinit var binding: FragmentCurrencyBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CurrencyViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyBinding.inflate(inflater, container, false)

        setEditTextObserver(binding.textByn.editText)
        setEditTextObserver(binding.textUsd.editText)
        setEditTextObserver(binding.textEur.editText)
        setEditTextObserver(binding.textRub.editText)
        setEditTextObserver(binding.textUah.editText)

        return binding.root
    }

    //Получаем позицию поля для связи с вьюмоделью
    private fun getEditPosition(editText: EditText?): Rates {
        return when (editText?.id) {
            R.id.edit_usd -> Rates.USD
            R.id.edit_eur -> Rates.EUR
            R.id.edit_rub -> Rates.RUB
            R.id.edit_uah -> Rates.UAH
            R.id.edit_byn -> Rates.BYN
            else -> Rates.ERROR
        }
    }

    //Подписываемся на изменения количества валюты в поле
    private fun setEditTextObserver(editText: EditText?) {
        when (editText?.id) {
            R.id.edit_byn -> viewModel.bynAmount.observe(viewLifecycleOwner) {
                editText.setText(it.toString())
            }
            R.id.edit_usd -> viewModel.usdAmount.observe(viewLifecycleOwner) {
                editText.setText(it.toString())
            }
            R.id.edit_eur -> viewModel.eurAmount.observe(viewLifecycleOwner) {
                editText.setText(it.toString())
            }
            R.id.edit_rub -> viewModel.rubAmount.observe(viewLifecycleOwner) {
                editText.setText(it.toString())
            }
            R.id.edit_uah -> viewModel.uahAmount.observe(viewLifecycleOwner) {
                editText.setText(it.toString())
            }
        }
        setEditChangeListener(editText)
    }

    //Устанавливаем слушатель изменения текста в каждом поле
    private fun setEditChangeListener(editText: EditText?) {
        editText?.doAfterTextChanged { text ->
            if(editText.isFocused){
                try {
                    val amount = text.toString()
                    if (amount.length == 2 && amount[0] == '0') {
                        editText.setText(amount.substring(1))
                        editText.setSelection(1)
                    }
                    viewModel.updateOtherFields(amount.toDouble(), getEditPosition(editText))
                } catch (exception: NumberFormatException) {
                    editText.append("0")
                }
            }
        }
    }
}