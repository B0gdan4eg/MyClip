package com.shcherbakov_bogdan.myclip.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.shcherbakov_bogdan.myclip.R
import com.shcherbakov_bogdan.myclip.data.transactions.Transactions
import com.shcherbakov_bogdan.myclip.databinding.FragmentHomeBinding
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            context,
            R.anim.rotate_open_anim
        )
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            context,
            R.anim.rotate_close_anim
        )
    }
    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            context,
            R.anim.from_bottom_anim
        )
    }
    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            context,
            R.anim.to_bottom_anim
        )
    }

    private var clicked = false

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.fabMain.setOnClickListener {
            onAddButtonClicked()
        }
        binding.fabFirst.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.DialogExpenses,
                null
            )
        )
        binding.fabSecond.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.DialogIncome,
                null
            )
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val adapter = HomeListAdapter(viewModel)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter

        viewModel.transactions.observe(viewLifecycleOwner, Observer<List<Transactions>> {
            adapter.refreshUsers(it)
        })
    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            fab_first.startAnimation(fromBottom)
            fab_second.startAnimation(fromBottom)
            fab_main.startAnimation(rotateOpen)
        } else {
            fab_first.startAnimation(toBottom)
            fab_second.startAnimation(toBottom)
            fab_main.startAnimation(rotateClose)
        }

    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            fab_first.visibility = View.VISIBLE
            fab_second.visibility = View.VISIBLE
        } else {
            fab_first.visibility = View.INVISIBLE
            fab_second.visibility = View.INVISIBLE
        }
    }

    private fun setClickable(clicked: Boolean) {
        if (clicked) {
            fab_first.isClickable = false
            fab_second.isClickable = false
        } else {
            fab_first.isClickable = true
            fab_second.isClickable = true
        }
    }
}
