package com.artesanoskuad.indicadoresrepaso.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artesanoskuad.indicadoresrepaso.data.model.Indicador
import com.artesanoskuad.indicadoresrepaso.databinding.ItemIndicadoresBinding

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