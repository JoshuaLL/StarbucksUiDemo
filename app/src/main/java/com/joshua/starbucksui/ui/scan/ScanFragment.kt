package com.joshua.starbucksui.ui.scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.joshua.starbucksui.R

class ScanFragment : Fragment() {

    private val scanViewModel: ScanViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_scan, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        scanViewModel.text.observe(viewLifecycleOwner){
            textView.text = it
        }
        return root
    }
}