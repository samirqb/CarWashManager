package samirqb.carwashmanager.app.viewmodels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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
import samirqb.lib.ventas.entities.DetalleOrdenServicioEntity
import samirqb.lib.ventas.entities.OrdenDeVentaEntity
import samirqb.lib.ventas.uc.BuscarOrdenDeVentaPorIdUseCase
import samirqb.lib.ventas.uc.CrearNuevaOrdenDeVentaUseCase
import samirqb.lib.ventas.uc.ListarLosServiciosAgregadoAUnaOrdenVentaUseCase
import samirqb.lib.ventas.uc.ListarTodasLasOrdenesDeVentasPorVigenciaUseCase
import samirqb.lib.ventas.uc.ListarTodasLasOrdenesDeVentasUseCase
import samirqb.lib.ventas.uc.ObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase

class OrdenDeVentaViewModel(
    private val mCrearNuevaOrdenDeVentaUseCase: CrearNuevaOrdenDeVentaUseCase,
    private val mListarTodasLasOrdenesDeVentasUseCase: ListarTodasLasOrdenesDeVentasUseCase,
    private val mListarTodasLasOrdenesDeVentasPorVigenciaUseCase: ListarTodasLasOrdenesDeVentasPorVigenciaUseCase,
    private val mListarLosServiciosAgregadoAUnaOrdenVentaUseCase: ListarLosServiciosAgregadoAUnaOrdenVentaUseCase,
    private val mObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase: ObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase,
    private val mBuscarOrdenDeVentaPorIdUseCase: BuscarOrdenDeVentaPorIdUseCase,
) : ViewModel() {

    private var mSumaValoresDeItemsDeUnaLista = SumaValoresDeItemsDeUnaLista()

    private val NOMBRE_CLASE = "OrdenDeVentaViewModel"

    private val _uiState = MutableStateFlow(OrdenDeVentaUiState())
    val uiState: StateFlow<OrdenDeVentaUiState> = _uiState.asStateFlow()

    init {
        listarTodasLasOrdenesDeVentasUC()
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

    fun listarTodasLosServiciosAgregadosALaOrden(id_orden_venta: Int) {

        val NOMBRE_FUN = "listarTodasLosServiciosAgregadosALaOrden"

        //todos_los_servicios_agregados_a_la_orden
        viewModelScope.launch(Dispatchers.IO) {

            try {
                mListarLosServiciosAgregadoAUnaOrdenVentaUseCase(id_orden_venta = id_orden_venta).collect{

                    val lista = it.toMutableList()

                    _uiState.update {
                        it.copy(
                            todos_los_servicios_agregados_a_la_orden = lista
                        )
                    }

                }
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

    fun listarTodasLasOrdenesDeVentasVigentesUC() {

        val NOMBRE_FUN = "listarTodasLasOrdenesDeVentasVigentesUC"

        val orden_vigente = true

        viewModelScope.launch(Dispatchers.IO) {
            try {
                mListarTodasLasOrdenesDeVentasPorVigenciaUseCase(orden_vigente).collect {
                    val lista = it.toMutableList()

                    _uiState.update {
                        it.copy(todas_las_ordenes_de_venta_vigentes = lista)
                    }
                }
            } catch (e: Exception) {
                Log.e("_xTAG", "Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun buscarOrdenDeVentaPorIdUseCase(id: Int){

        val NOMBRE_FUN = "buscarOrdenDeVentaPorIdUseCase"

        viewModelScope.launch(Dispatchers.IO) {
            try {
                mBuscarOrdenDeVentaPorIdUseCase(id).collect{

                    val resultado = it

                    _uiState.update {
                        it.copy(
                            resultado_busqueda_orden_de_venta = mutableStateOf(resultado)
                        )
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
                    todas_las_ordenes_de_venta_no_vigente = mutableStateListOf(),
                    todas_las_ordenes_de_venta_vigentes = mutableStateListOf(),
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
                val mListarLosServiciosDeOrdenVentaUseCase =
                    (this[APPLICATION_KEY] as MyApplication).mListarLosServiciosAgregadoAUnaOrdenVentaUseCase
                val mBuscarOrdenDeVentaPorIdUseCase =
                    (this[APPLICATION_KEY] as MyApplication).mBuscarOrdenDeVentaPorIdUseCase
                val mListarTodasLasOrdenesDeVentasPorVigenciaUseCase =
                    (this[APPLICATION_KEY] as MyApplication).mListarTodasLasOrdenesDeVentasPorVigenciaUseCase
                OrdenDeVentaViewModel(
                    mCrearNuevaOrdenDeVentaUseCase = mCrearNuevaOrdenDeVentaUseCase,
                    mListarTodasLasOrdenesDeVentasUseCase = mListarTodasLasOrdenesDeVentasUseCase,
                    mListarTodasLasOrdenesDeVentasPorVigenciaUseCase = mListarTodasLasOrdenesDeVentasPorVigenciaUseCase,
                    mListarLosServiciosAgregadoAUnaOrdenVentaUseCase = mListarLosServiciosDeOrdenVentaUseCase,
                    mObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase = mObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase,
                    mBuscarOrdenDeVentaPorIdUseCase = mBuscarOrdenDeVentaPorIdUseCase
                )
            }
        }
    }
}