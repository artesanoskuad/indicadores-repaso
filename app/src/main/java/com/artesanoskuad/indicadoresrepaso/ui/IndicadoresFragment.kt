package com.artesanoskuad.indicadoresrepaso.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.artesanoskuad.indicadoresrepaso.data.IndicadoresRepository
import com.artesanoskuad.indicadoresrepaso.data.model.Indicador
import com.artesanoskuad.indicadoresrepaso.data.remote.RetrofitClient
import com.artesanoskuad.indicadoresrepaso.databinding.FragmentIndicadoresBinding
import com.artesanoskuad.indicadoresrepaso.presentation.IndicadoresViewModel
import com.artesanoskuad.indicadoresrepaso.presentation.IndicadoresViewModelFactory
import com.artesanoskuad.indicadoresrepaso.presentation.IndicadoresViewState
import com.artesanoskuad.indicadoresrepaso.presentation.IndicadoresViewState.MostrarIndicadoresViewState
import com.artesanoskuad.indicadoresrepaso.presentation.IndicadoresViewState.NoSeObtieneRespuestaViewState


class IndicadoresFragment : Fragment() {

    private var rawBinding: FragmentIndicadoresBinding? = null
    private val binding get() = rawBinding!!

    private val indicadoresApi = RetrofitClient.crearApiIndicadores()
    private val repository = IndicadoresRepository(indicadoresApi)
    private val indicadoresViewModelFactory = IndicadoresViewModelFactory(repository)
    private val indicadoresViewModel: IndicadoresViewModel by activityViewModels {
        indicadoresViewModelFactory
    }

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
        setupViewModel()
    }

    private fun setupRecyclerView() {
        binding.rvIndicadores.layoutManager = LinearLayoutManager(context)
    }

    private fun handleUI(state: IndicadoresViewState) {
        when (state) {
            is NoSeObtieneRespuestaViewState -> muestraVentanaSinRespuesta()
            is MostrarIndicadoresViewState -> muestraIndicadores(
                state.author,
                state.fecha,
                state.version,
                state.indicadores
            )
        }
    }

    private fun muestraIndicadores(
        author: String,
        fecha: String,
        version: String,
        indicadores: List<Indicador>
    ) {
        with(binding) {
            tvAuthor.text = author
            tvFecha.text = fecha
            tvVersion.text = version
            rvIndicadores.adapter = IndicadoresAdapter(indicadores)
        }
    }

    private fun muestraVentanaSinRespuesta() {
        Toast.makeText(context, "No se obtiene respuesta del servidor", Toast.LENGTH_SHORT).show()
    }

    private fun setupViewModel() {
        indicadoresViewModel.state().observe(viewLifecycleOwner) {
            it?.let { safeState ->
                handleUI(safeState)
            }
        }
        indicadoresViewModel.obtenerIndicadores()
    }

}