package samirqb.carwashmanager.app.viewmodels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
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
import samirqb.lib.ventas.entities.OrdenDeVentaEntity
import samirqb.lib.ventas.uc.BuscarOrdenDeVentaPorIdUseCase
import samirqb.lib.ventas.uc.CrearNuevaOrdenDeVentaUseCase
import samirqb.lib.ventas.uc.ListarTodasLasOrdenesDeVentasPorVigenciaUseCase
import samirqb.lib.ventas.uc.ListarTodasLasOrdenesDeVentasUseCase
import samirqb.lib.ventas.uc.ObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase

class OrdenDeVentaViewModel(
    private val mCrearNuevaOrdenDeVentaUseCase: CrearNuevaOrdenDeVentaUseCase,
    private val mListarTodasLasOrdenesDeVentasUseCase: ListarTodasLasOrdenesDeVentasUseCase,
    private val mListarTodasLasOrdenesDeVentasPorVigenciaUseCase: ListarTodasLasOrdenesDeVentasPorVigenciaUseCase,
    private val mObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase: ObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase,
    private val mBuscarOrdenDeVentaPorIdUseCase: BuscarOrdenDeVentaPorIdUseCase,
) : ViewModel() {

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

                    var numero_calculado = it + 1

                    _uiState.update {
                        it.copy(
                            numero_calculado_para_nueva_orden_de_venta = numero_calculado
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("_xTAG", "Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun idDeOrdenDeVentaSeleccionada(id: Int) {

        val NOMBRE_FUN = "idDeOrdenDeVenta"

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.update {
                    it.copy(
                        numero_de_orden_de_venta_seleccionada = id
                    )
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
                    fecha_y_hora = "",
                    numero_de_orden_de_venta_seleccionada = 0,
                    numero_calculado_para_nueva_orden_de_venta = 0,
                    todas_las_ordenes_de_venta = mutableStateListOf(),
                    todas_las_ordenes_de_venta_vigentes = mutableStateListOf(),
                    todas_las_ordenes_de_venta_no_vigente = mutableStateListOf(),
                    resultado_busqueda_orden_de_venta = mutableStateOf(null),
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
                val mBuscarOrdenDeVentaPorIdUseCase =
                    (this[APPLICATION_KEY] as MyApplication).mBuscarOrdenDeVentaPorIdUseCase
                val mListarTodasLasOrdenesDeVentasPorVigenciaUseCase =
                    (this[APPLICATION_KEY] as MyApplication).mListarTodasLasOrdenesDeVentasPorVigenciaUseCase

                OrdenDeVentaViewModel(
                    mCrearNuevaOrdenDeVentaUseCase = mCrearNuevaOrdenDeVentaUseCase,
                    mListarTodasLasOrdenesDeVentasUseCase = mListarTodasLasOrdenesDeVentasUseCase,
                    mListarTodasLasOrdenesDeVentasPorVigenciaUseCase = mListarTodasLasOrdenesDeVentasPorVigenciaUseCase,

                    mObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase = mObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase,
                    mBuscarOrdenDeVentaPorIdUseCase = mBuscarOrdenDeVentaPorIdUseCase
                )
            }
        }
    }
}