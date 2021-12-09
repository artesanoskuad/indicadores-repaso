package com.artesanoskuad.indicadoresrepaso.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.artesanoskuad.indicadoresrepaso.data.IndicadoresDataRepository
import com.artesanoskuad.indicadoresrepaso.data.db.IndicadoresDao
import com.artesanoskuad.indicadoresrepaso.data.db.IndicadoresDatabase
import com.artesanoskuad.indicadoresrepaso.data.remote.IndicadoresApi
import com.artesanoskuad.indicadoresrepaso.data.remote.RetrofitClient
import com.artesanoskuad.indicadoresrepaso.databinding.FragmentIndicadoresBinding
import com.artesanoskuad.indicadoresrepaso.domain.Indicadores
import com.artesanoskuad.indicadoresrepaso.domain.IndicadoresRepository
import com.artesanoskuad.indicadoresrepaso.domain.ObtenerIndicadoresUseCase
import com.artesanoskuad.indicadoresrepaso.presentation.IndicadoresViewModel
import com.artesanoskuad.indicadoresrepaso.presentation.IndicadoresViewModelFactory
import com.artesanoskuad.indicadoresrepaso.presentation.IndicadoresViewState
import com.artesanoskuad.indicadoresrepaso.presentation.IndicadoresViewState.MostrarIndicadoresViewState
import com.artesanoskuad.indicadoresrepaso.presentation.IndicadoresViewState.NoSeObtieneRespuestaViewState


class IndicadoresFragment : Fragment() {

    private var rawBinding: FragmentIndicadoresBinding? = null
    private val binding get() = rawBinding!!

    private lateinit var indicadoresDao: IndicadoresDao
    private lateinit var indicadoresApi: IndicadoresApi

    lateinit var repository: IndicadoresRepository
    lateinit var obtenerIndicadoresUseCase: ObtenerIndicadoresUseCase
    lateinit var indicadoresViewModelFactory: IndicadoresViewModelFactory
    lateinit var indicadoresViewModel: IndicadoresViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rawBinding = FragmentIndicadoresBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDependencies()
        setupRecyclerView()
        setupViewModel()
    }

    private fun initDependencies() {
        indicadoresDao =
            IndicadoresDatabase.getDatabase(requireActivity().applicationContext).indicadoresDao
        indicadoresApi = RetrofitClient.crearApiIndicadores()
        repository = IndicadoresDataRepository(indicadoresApi, indicadoresDao)
        obtenerIndicadoresUseCase = ObtenerIndicadoresUseCase(repository)
        indicadoresViewModelFactory = IndicadoresViewModelFactory(obtenerIndicadoresUseCase)
        indicadoresViewModel = ViewModelProvider(this, indicadoresViewModelFactory)[IndicadoresViewModel::class.java]
    }

    private fun setupRecyclerView() {
        binding.rvIndicadores.layoutManager = LinearLayoutManager(context)
    }

    private fun handleUI(state: IndicadoresViewState) {
        when (state) {
            is NoSeObtieneRespuestaViewState -> muestraVentanaSinRespuesta()
            is MostrarIndicadoresViewState -> muestraIndicadores(
                state.indicadores
            )
        }
    }

    private fun muestraIndicadores(
        indicadores: Indicadores
    ) {
        with(binding) {
            tvAuthor.text = indicadores.autor
            tvFecha.text = indicadores.fecha
            tvVersion.text = indicadores.version
            rvIndicadores.adapter = IndicadoresAdapter(indicadores.indicadores)
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