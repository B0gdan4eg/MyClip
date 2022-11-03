package com.shcherbakov_bogdan.myclip.ui.fragments.inbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.shcherbakov_bogdan.myclip.data.sms.TransactionFromSms
import com.shcherbakov_bogdan.myclip.databinding.FragmentInboxBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class InboxFragment : DaggerFragment() {

    private lateinit var binding: FragmentInboxBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: InboxViewModel by viewModels {
        viewModelFactory
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInboxBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInboxBinding.bind(view)

        val adapter = InboxListAdapter(viewModel)
        binding.recyclerViewInbox.layoutManager = LinearLayoutManager(activity)
        binding.recyclerViewInbox.adapter = adapter
        binding.recyclerViewInbox.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        viewModel.smsTransactions.observe(viewLifecycleOwner, Observer<List<TransactionFromSms>> {
            adapter.refreshList(it)
        })
    }
}