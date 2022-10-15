package com.shcherbakov_bogdan.myclip.ui.fragments.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shcherbakov_bogdan.myclip.R
import com.shcherbakov_bogdan.myclip.databinding.FragmentCurrencyBinding

class CurrencyFragment : Fragment() {

    private lateinit var binding: FragmentCurrencyBinding

    companion object {
        fun newInstance() = CurrencyFragment()
    }

    private lateinit var viewModel: CurrencyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currency, container, false)
    }
}