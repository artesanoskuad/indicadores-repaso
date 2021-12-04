package com.artesanoskuad.indicadoresrepaso.ui

import androidx.recyclerview.widget.RecyclerView
import com.artesanoskuad.indicadoresrepaso.data.model.Indicador
import com.artesanoskuad.indicadoresrepaso.databinding.ItemIndicadoresBinding

class IndicadoresViewHolder(
    private val binding: ItemIndicadoresBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(indicador: Indicador){
        with(binding){
            tvCodigo.text = indicador.codigo
            tvNombre.text = indicador.nombre
            tvUnidadMedida.text = indicador.unidadMedida
            tvFecha.text = indicador.fecha
            tvValor.text = indicador.valor
        }
    }
}