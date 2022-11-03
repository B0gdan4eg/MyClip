package com.shcherbakov_bogdan.myclip.ui.fragments.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shcherbakov_bogdan.myclip.databinding.FragmentReportBinding

class ReportFragment : Fragment() {

    private lateinit var binding: FragmentReportBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReportBinding.inflate(inflater, container, false)


        return binding.root
    }
}