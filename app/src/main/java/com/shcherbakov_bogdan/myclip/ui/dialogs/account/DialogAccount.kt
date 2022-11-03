package com.shcherbakov_bogdan.myclip.ui.dialogs.account

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.shcherbakov_bogdan.myclip.data.account.Account
import com.shcherbakov_bogdan.myclip.databinding.DialogAccountBinding
import dagger.android.support.DaggerDialogFragment
import javax.inject.Inject

class DialogAccount : DaggerDialogFragment() {

    private lateinit var binding: DialogAccountBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: AccountViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogAccountBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = DialogAccountBinding.bind(view)

        val adapter = AccountListAdapter(requireContext(), viewModel)

        binding.accountList.layoutManager = LinearLayoutManager(activity)
        binding.accountList.adapter = adapter
        binding.accountList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        viewModel.accounts.observe(viewLifecycleOwner, Observer<List<Account>>
        {
            adapter.refreshUsers(it)
        })
    }
}