package com.joshua.starbucksui.ui.wallet.walletlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.joshua.starbucksui.databinding.ItemAdBinding
import com.joshua.starbucksui.databinding.ItemRecordBinding
import com.joshua.starbucksui.databinding.ItemVoucherBinding
import com.joshua.starbucksui.model.vo.*
import com.joshua.starbucksui.ui.wallet.WalletFragment
import com.joshua.starbucksui.ui.wallet.WalletFragmentDirections

class WalletListAdapter : PagingDataAdapter<RecordItem, RecyclerView.ViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ViewType.TYPE_VOUCHER.value ->{
                val binding = ItemVoucherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                VoucherViewHolder(binding)
            }
            ViewType.TYPE_AD.value ->{
                val binding = ItemAdBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                AdViewHolder(binding)
            }
            else -> {
                val binding = ItemRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                RecordViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.type?.value ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {item->
            when(holder){
                is AdViewHolder ->  {
                    holder.tvAdContent.text = item.content
                    holder.tvAdVendor.text = item.vendor
                }
                is RecordViewHolder -> {
                    holder.tvToken.text = item.token
                    holder.tvTokenXnc.text = item.tokenXnc
                    holder.tvRewardInteraction.text = item.interaction
                    holder.tvRewardSocial.text = item.social
                    holder.tvRevenue.text = item.revenue
                    holder.tvMoreDetail.setOnClickListener {
                        holder.itemView.findNavController().navigate(
                            WalletFragmentDirections.actionWalletFragmentToDetailFragment(item)
                        )
                    }
                }
                is VoucherViewHolder -> {
                    holder.tvVoucherContent.text = item.content
                    holder.tvVoucherTime.text = item.time
                }
            }
        }

    }

    class RecordViewHolder(binding:  ItemRecordBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvToken: TextView = binding.tvToken
        val tvTokenXnc: TextView = binding.tvTokenXnc
        val tvRewardInteraction: TextView = binding.tvRewardInteraction
        val tvRewardSocial: TextView = binding.tvRewardSocial
        val tvRevenue: TextView = binding.tvRevenue
        val tvMoreDetail: TextView = binding.tvMoreDetail
    }

    class VoucherViewHolder(binding: ItemVoucherBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvVoucherContent: TextView = binding.tvVoucherContent
        val tvVoucherTime: TextView = binding.tvVoucherTime
    }

    class AdViewHolder(binding: ItemAdBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvAdContent: TextView = binding.tvAdContent
        val tvAdVendor: TextView = binding.tvAdVendor
    }
}

private class ItemDiffCallback : DiffUtil.ItemCallback<RecordItem>() {
    override fun areItemsTheSame(oldItem: RecordItem, newItem: RecordItem): Boolean {
        return oldItem.id == newItem.id

    }
    override fun areContentsTheSame(oldItem: RecordItem, newItem: RecordItem): Boolean {
        return oldItem == newItem
    }
}