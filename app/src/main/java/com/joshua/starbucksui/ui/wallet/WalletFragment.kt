package com.joshua.starbucksui.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.joshua.starbucksui.R
import com.joshua.starbucksui.databinding.FragmentPhoneBinding
import com.joshua.starbucksui.databinding.FragmentWalletBinding

class WalletFragment : Fragment(R.layout.fragment_wallet) {

    private lateinit var binding: FragmentWalletBinding
    private val walletViewModel: WalletViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWalletBinding.bind(view)
//        binding.appBar.layoutParams.height =

    }
}