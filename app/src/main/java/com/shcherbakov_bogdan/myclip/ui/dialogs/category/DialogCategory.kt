package com.shcherbakov_bogdan.myclip.ui.dialogs.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.shcherbakov_bogdan.myclip.databinding.DialogCategoryBinding

class DialogCategory : DialogFragment() {

    private lateinit var binding: DialogCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogCategoryBinding.inflate(inflater, container, false)



        return binding.root
    }
}