package com.joshua.starbucksui.ui.phone

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.joshua.starbucksui.R
import com.joshua.starbucksui.databinding.FragmentPhoneBinding

class PhoneFragment : Fragment(R.layout.fragment_phone) {
    private lateinit var binding: FragmentPhoneBinding

    private val phoneViewModel: PhoneViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhoneBinding.bind(view)
        phoneViewModel.text.observe(viewLifecycleOwner) {
            binding.tvContent.text = it
        }

    }
}