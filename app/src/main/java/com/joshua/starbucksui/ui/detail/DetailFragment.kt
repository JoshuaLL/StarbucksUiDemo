package com.joshua.starbucksui.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.joshua.starbucksui.R
import com.joshua.starbucksui.databinding.FragmentDetailBinding
import com.joshua.starbucksui.model.vo.RecordItem

class DetailFragment : Fragment(R.layout.fragment_detail) {

    companion object {
        private const val KEY_ITEM = "KEY_ITEM"
    }

    lateinit var binding:FragmentDetailBinding
    private val  detailViewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding =FragmentDetailBinding.bind(view)

        arguments?.getParcelable<RecordItem>(KEY_ITEM)?.let {
            setupUI(it)
        }

        detailViewModel.text.observe(viewLifecycleOwner) {
            binding.tvContent.text = it
        }
    }

    private fun setupUI(item:RecordItem){
        binding.tvItemDetail.text =item.toString()
    }

}