package com.joshua.starbucksui.ui.wallet

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.joshua.starbucksui.R
import com.joshua.starbucksui.databinding.FragmentWalletBinding
import com.joshua.starbucksui.model.vo.ViewType
import com.joshua.starbucksui.ui.wallet.walletlist.WalletListFragment

class WalletFragment : Fragment(R.layout.fragment_wallet) {

    private lateinit var binding: FragmentWalletBinding
    private val walletViewModel: WalletViewModel by viewModels()

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
            ViewType.TYPE_RECORD.value to { WalletListFragment(ViewType.TYPE_RECORD) },
            ViewType.TYPE_VOUCHER.value to { WalletListFragment(ViewType.TYPE_VOUCHER) },
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWalletBinding.bind(view)
        binding.apply {
            pager.adapter = TabAdapter(tabFragmentsCreators, childFragmentManager, lifecycle)

            val tabs = resources.getStringArray(R.array.wallet_tabs)
            val tabLayoutMediator = TabLayoutMediator(tabLayout, pager) { tab, position ->
                val tabView = View.inflate(requireContext(), R.layout.custom_tab, null)
                val textView = tabView?.findViewById<TextView>(R.id.tv_title)
                textView?.text = tabs[position]
                tab.customView = tabView
            }
            tabLayoutMediator.attach()
        }

    }

}