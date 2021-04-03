package com.joshua.starbucksui.ui.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.joshua.starbucksui.R
import com.joshua.starbucksui.databinding.FragmentChatBinding

class ChatFragment : Fragment(R.layout.fragment_chat) {
    private lateinit var binding:FragmentChatBinding

    private val chatViewModel: ChatViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatBinding.bind(view)

        chatViewModel.text.observe(viewLifecycleOwner){
            binding.tvContent.text = it
        }
    }
}