package com.joshua.starbucksui.ui.scan

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.joshua.starbucksui.R
import com.joshua.starbucksui.databinding.FragmentScanBinding

class ScanFragment : Fragment(R.layout.fragment_scan) {

    private lateinit var binding : FragmentScanBinding
    private val scanViewModel: ScanViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding =FragmentScanBinding.bind(view)
        scanViewModel.text.observe(viewLifecycleOwner){
            binding.tvContent.text = it
        }
    }
}