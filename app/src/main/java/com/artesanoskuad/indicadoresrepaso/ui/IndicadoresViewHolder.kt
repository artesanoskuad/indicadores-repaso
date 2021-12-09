package com.artesanoskuad.indicadoresrepaso.ui

import androidx.recyclerview.widget.RecyclerView
import com.artesanoskuad.indicadoresrepaso.databinding.ItemIndicadoresBinding
import com.artesanoskuad.indicadoresrepaso.domain.Indicador

class IndicadoresViewHolder(
    private val binding: ItemIndicadoresBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(indicadorRetrofit: Indicador){
        with(binding){
            tvCodigo.text = indicadorRetrofit.codigo
            tvNombre.text = indicadorRetrofit.nombre
            tvUnidadMedida.text = indicadorRetrofit.unidadMedida
            tvFecha.text = indicadorRetrofit.fecha
            tvValor.text = indicadorRetrofit.valor
        }
    }
}