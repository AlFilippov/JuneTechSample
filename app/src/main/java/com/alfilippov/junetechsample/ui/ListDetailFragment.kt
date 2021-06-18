package com.alfilippov.junetechsample.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.alfilippov.junetechsample.R

class ListDetailFragment : Fragment(R.layout.fragment_detail) {
    private val safeArgs: ListDetailFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView: TextView = view.findViewById(R.id.title_number)
        textView.text = "Number ${safeArgs.numberUser}"
    }
}