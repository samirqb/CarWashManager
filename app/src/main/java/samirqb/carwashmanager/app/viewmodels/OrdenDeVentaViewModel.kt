package samirqb.carwashmanager.app.viewmodels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import samirqb.carwashmanager.app.MyApplication
import samirqb.carwashmanager.app.viewmodels.uistates.OrdenDeVentaUiState
import samirqb.lib.helpers.FechaYHora
import samirqb.lib.helpers.SumaValoresDeItemsDeUnaLista
import samirqb.lib.ventas.entities.DetalleOrdenProductoEntity
import samirqb.lib.ventas.entities.DetalleOrdenServicioEntity
import samirqb.lib.ventas.entities.OrdenDeVentaEntity
import samirqb.lib.ventas.uc.CrearNuevaOrdenDeVentaUseCase
import samirqb.lib.ventas.uc.ListarTodasLasOrdenesDeVentasUseCase
import samirqb.lib.ventas.uc.ObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase
import samirqb.lib.ventas.uc.ObtenerOrdenDeVentaMasRecienteUseCase

class OrdenDeVentaViewModel(
    private val mCrearNuevaOrdenDeVentaUseCase: CrearNuevaOrdenDeVentaUseCase,
    private val mListarTodasLasOrdenesDeVentasUseCase: ListarTodasLasOrdenesDeVentasUseCase,
    private val mObtenerOrdenDeVentaMasRecienteUseCase: ObtenerOrdenDeVentaMasRecienteUseCase,
    private val mObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase: ObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase,
) : ViewModel() {

    private var mSumaValoresDeItemsDeUnaLista = SumaValoresDeItemsDeUnaLista()

    private val NOMBRE_CLASE = "OrdenDeVentaViewModel"

    private val _uiState = MutableStateFlow(OrdenDeVentaUiState())
    val uiState: StateFlow<OrdenDeVentaUiState> = _uiState.asStateFlow()

    init {
        listarTodasLasOrdenesDeVentasUC()
        listarTodasLosServiciosAgregadosALaOrden()
        calcularIdDeNuevaOrdenDeVenta()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun actualizarFechaYHora() {

        var mFechaYHora = FechaYHora()
        mFechaYHora.now()

        _uiState.update {
            it.copy(
                fecha_y_hora = mFechaYHora.getDateTime(),
                //fecha = mFechaYHora.getDate(),
                //hora = mFechaYHora.getTime(),
            )
        }
    }

    fun calcularIdDeNuevaOrdenDeVenta() {

        val NOMBRE_FUN = "calcularIdDeNuevaOrdenDeVenta"

        viewModelScope.launch(Dispatchers.IO) {
            try {
                mObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase().collect {
                    var numero_calculado = 0

                    if (it == 0) {
                        numero_calculado = 1
                    } else {
                        numero_calculado = (it + 1)
                    }
                    _uiState.update {
                        it.copy(
                            numero_de_nueva_orden_de_venta = numero_calculado
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("_xTAG", "Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun crearNuevaOrdenDeVentaUC(mEntity: OrdenDeVentaEntity) {

        val NOMBRE_FUN = "crearNuevaOrdenDeVentaUC"

        viewModelScope.launch(Dispatchers.IO) {

            try {
                mCrearNuevaOrdenDeVentaUseCase(mEntity)
            } catch (e: Exception) {
                Log.e("_xTAG", "Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun agregarServicioAOrdenDeVentaSoloEnUiState(mEntity: DetalleOrdenServicioEntity) {

        val NOMBRE_FUN = "agregarEnUiStateServicioAOrdenDeVenta"

        viewModelScope.launch(Dispatchers.IO) {

            try {
                _uiState.update {

                    var lista = it.todos_los_servicios_agregados_a_la_orden

                    lista.add(mEntity)

                    it.copy(
                        todos_los_servicios_agregados_a_la_orden = lista
                    )
                }
            } catch (e: Exception) {
                Log.e("_xTAG", "Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun agregarPresiosDeServicioAOrdenDeVentaSoloEnUiState(precio_servicio: Float) {

        val NOMBRE_FUN = "agregarEnUiStatePresiosDeServicioAOrdenDeVenta"

        viewModelScope.launch(Dispatchers.IO) {

            try {
                _uiState.update {

                    var lista = it.todos_los_precios_de_servicios_agregados_a_la_orden

                    lista.add(precio_servicio)

                    it.copy(
                        todos_los_precios_de_servicios_agregados_a_la_orden = lista
                    )
                }
            } catch (e: Exception) {
                Log.e("_xTAG", "Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun sumarTodosLosPreciosDeListaDeServiciosAgregados() {
        viewModelScope.launch(Dispatchers.IO) {

            _uiState.update {
                if (it.todos_los_precios_de_servicios_agregados_a_la_orden.isEmpty()) {
                    it.copy(
                        suma_precios_servicios_agregados_a_orden = mutableFloatStateOf(0f)
                    )
                } else {
                    it.copy(
                        suma_precios_servicios_agregados_a_orden = mutableFloatStateOf(
                            mSumaValoresDeItemsDeUnaLista.sumar(
                                it.todos_los_precios_de_servicios_agregados_a_la_orden
                            )
                        )
                    )
                }
            }
        }
    }

    fun listarTodasLosServiciosAgregadosALaOrden() {

        val NOMBRE_FUN = "listarTodasLosServiciosAgregadosALaOrden"

        viewModelScope.launch(Dispatchers.IO) {

            try {
                _uiState.replayCache
            } catch (e: Exception) {
                Log.e("_xTAG", "Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }


    fun listarTodasLasOrdenesDeVentasUC() {

        val NOMBRE_FUN = "listarTodasLasOrdenesDeVentasUC"

        viewModelScope.launch(Dispatchers.IO) {
            try {
                mListarTodasLasOrdenesDeVentasUseCase().collect {
                    val lista = it.toMutableList()

                    _uiState.update {
                        it.copy(todas_las_ordenes_de_venta = lista)
                    }
                }
            } catch (e: Exception) {
                Log.e("_xTAG", "Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun limpiarDatosUiState() {

        viewModelScope.launch(Dispatchers.IO) {

            _uiState.update {
                it.copy(
                    numero_de_nueva_orden_de_venta = 0,
                    fecha_y_hora = "",
                    lista_operarios_relacionados_con_orden_venta = mutableStateListOf(),
                    todas_las_ordenes_de_venta = mutableStateListOf(),
                    todas_las_ordenes_de_venta_pagadas = mutableStateListOf(),
                    todas_las_ordenes_de_venta_sin_pagar = mutableStateListOf(),
                    todos_los_servicios_agregados_a_la_orden = mutableStateListOf(),
                    todos_los_precios_de_servicios_agregados_a_la_orden = mutableStateListOf(0f),
                    suma_precios_servicios_agregados_a_orden = mutableFloatStateOf(0f),
                    suma_precios_productos_agregados_a_orden = mutableFloatStateOf(0f),
                    todos_los_productos_agregados_a_la_orden = mutableStateListOf(),
                )
            }
        }
    }


    /** ViewModelFactori **/
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // val savedStateHandle = createSavedStateHandle()
                val mCrearNuevaOrdenDeVentaUseCase =
                    (this[APPLICATION_KEY] as MyApplication).mCrearNuevaOrdenDeVentaUseCase
                val mListarTodasLasOrdenesDeVentasUseCase =
                    (this[APPLICATION_KEY] as MyApplication).mListarTodasLasOrdenesDeVentasUseCase
                val mObtenerOrdenDeVentaMasRecienteUseCase =
                    (this[APPLICATION_KEY] as MyApplication).mObtenerOrdenDeVentaMasRecienteUseCase
                val mObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase =
                    (this[APPLICATION_KEY] as MyApplication).mObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase
                OrdenDeVentaViewModel(
                    mCrearNuevaOrdenDeVentaUseCase = mCrearNuevaOrdenDeVentaUseCase,
                    mListarTodasLasOrdenesDeVentasUseCase = mListarTodasLasOrdenesDeVentasUseCase,
                    mObtenerOrdenDeVentaMasRecienteUseCase = mObtenerOrdenDeVentaMasRecienteUseCase,
                    mObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase = mObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase
                )
            }
        }
    }
}