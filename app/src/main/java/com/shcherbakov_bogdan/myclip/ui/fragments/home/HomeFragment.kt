package com.shcherbakov_bogdan.myclip.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shcherbakov_bogdan.myclip.R
import com.shcherbakov_bogdan.myclip.data.transactions.Transactions
import com.shcherbakov_bogdan.myclip.databinding.FragmentHomeBinding
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : DaggerFragment() {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeListAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTransactions()
            .observe(
                this.viewLifecycleOwner
            ) { list -> if (list.isNotEmpty()) initList(list) }
    }

    private fun initList(transactions: List<Transactions>) {
        val adapter = HomeListAdapter(transactions)
        recyclerView.adapter = adapter
    }
}