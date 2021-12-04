package com.artesanoskuad.indicadoresrepaso.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.artesanoskuad.indicadoresrepaso.R
import com.artesanoskuad.indicadoresrepaso.databinding.FragmentIndicadoresBinding


class IndicadoresFragment : Fragment() {

    private var rawBinding: FragmentIndicadoresBinding? = null
    private val binding get() = rawBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rawBinding = FragmentIndicadoresBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvIndicadores.layoutManager = LinearLayoutManager(context)
    }

}