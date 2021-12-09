package com.artesanoskuad.indicadoresrepaso.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artesanoskuad.indicadoresrepaso.databinding.ItemIndicadoresBinding
import com.artesanoskuad.indicadoresrepaso.domain.Indicador

class IndicadoresAdapter(
    private val indicadores: List<Indicador>
) : RecyclerView.Adapter<IndicadoresViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndicadoresViewHolder {
        val binding =
            ItemIndicadoresBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IndicadoresViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IndicadoresViewHolder, position: Int) {
        holder.bind(indicadores[position])
    }

    override fun getItemCount() = indicadores.size

}