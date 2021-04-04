package com.joshua.starbucksui.ui.wallet.walletlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.joshua.starbucksui.R
import com.joshua.starbucksui.databinding.FragmentWalletListBinding
import com.joshua.starbucksui.model.vo.ViewIntent
import com.joshua.starbucksui.model.vo.ViewState
import com.joshua.starbucksui.model.vo.ViewType
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WalletListFragment(private val type: ViewType) : Fragment(R.layout.fragment_wallet_list) {

    private lateinit var binding: FragmentWalletListBinding
    private val viewModel: WalletListViewModel by viewModels()
    private lateinit var adapter:WalletListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWalletListBinding.bind(view)
        binding.apply {
            adapter = WalletListAdapter()
            rvList.adapter = adapter
        }

        lifecycleScope.launch {
            viewModel.fetchIntent.send(
                when(type){
                    ViewType.TYPE_RECORD ->ViewIntent.FetchRecordData
                    else ->ViewIntent.FetchVoucherData
                }

            )
        }
        observeViewModel()

    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is ViewState.Idle -> {}
                    is ViewState.Loading -> {}

                    is ViewState.RecordItems -> {
                        adapter.submitData(it.records)
                    }
                    is ViewState.Error -> {}
                }
            }
        }
    }

}