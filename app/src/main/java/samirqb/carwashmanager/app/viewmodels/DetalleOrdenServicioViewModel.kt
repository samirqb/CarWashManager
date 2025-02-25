package samirqb.carwashmanager.app.viewmodels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import samirqb.carwashmanager.app.viewmodels.uistates.DetalleOrdenServicioUiState
import samirqb.lib.helpers.FechaYHora
import samirqb.lib.helpers.SumaValoresDeItemsDeUnaLista
import samirqb.lib.ventas.entities.DetalleOrdenServicioEntity
import samirqb.lib.ventas.uc.InsertarVariosDetallesDeOrdenServiciosUseCase
import samirqb.lib.ventas.uc.ListarLosServiciosAgregadoAUnaOrdenVentaUseCase

class DetalleOrdenServicioViewModel(
    private val mInsertarVariosDetallesDeOrdenServiciosUseCase: InsertarVariosDetallesDeOrdenServiciosUseCase,
    private val mListarLosServiciosAgregadosAUnaOrdenVentaUseCase: ListarLosServiciosAgregadoAUnaOrdenVentaUseCase,
): ViewModel() {

    private val NOMBRE_CLASE = "DetalleOrdenServicioViewModel"

    private var mSumaValoresDeItemsDeUnaLista = SumaValoresDeItemsDeUnaLista()

    private val _uiState = MutableStateFlow(DetalleOrdenServicioUiState())
    val uiState: StateFlow<DetalleOrdenServicioUiState> = _uiState.asStateFlow()



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

    fun insertarListaDeDetallesAOrdenDeServiciosUC(mEntities: List<DetalleOrdenServicioEntity>){

        val NOMBRE_FUN = "insertarListaDeDetallesAOrdenDeServiciosUC"

        mEntities.forEach{
            Log.i("_xTAG","id_registro_pk:${it.id_registro_pk}")
            Log.i("_xTAG","id_orden_venta_fk:${it.id_orden_venta_fk}")
            Log.i("_xTAG","id_orden_pago_nomina_fk:${it.id_orden_pago_nomina_fk}")
            Log.i("_xTAG","id_precio_y_servicio_fk:${it.id_precio_y_servicio_fk}")
            Log.i("_xTAG","id_operario_fk:${it.id_operario_fk}")
            Log.i("_xTAG","servicio_culminado:${it.servicio_culminado}")
            Log.i("_xTAG","fecha_hora_creacion:${it.fecha_hora_creacion}")
            Log.i("_xTAG","********************************************")
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                mInsertarVariosDetallesDeOrdenServiciosUseCase(mEntities)
            } catch (e: Exception) {
                Log.e("_xTAG", "Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN}")
                Log.e("_xTAG", "${e.message}")
                Log.e("_xTAG", "StackTrace:")
                e.stackTrace.forEach {
                    Log.e("_xTAG", "-> fileName: ${ it.fileName }")
                    Log.e("_xTAG", "-> className: ${ it.className }")
                    Log.e("_xTAG", "-> lineNumber: ${ it.lineNumber }")
                    Log.e("_xTAG", "-> methodName: ${ it.methodName }")
                    Log.e("_xTAG", "-> isNativeMethod: ${ it.isNativeMethod }")
                }
            }
        }
    }


    // MOVER A VIEWMODEL -> DetalleOrdenServicioViewModel
    fun agregarServicioAOrdenDeVentaSoloEnUiState(mEntity: DetalleOrdenServicioEntity) {

        val NOMBRE_FUN = "agregarEnUiStateServicioAOrdenDeVenta"

        viewModelScope.launch(Dispatchers.IO) {

            try {
                _uiState.update {

                    var lista = it.nuevos_servicios_por_agregar_a_la_orden

                    lista.add(mEntity)

                    it.copy(
                        nuevos_servicios_por_agregar_a_la_orden = lista
                    )
                }
            } catch (e: Exception) {
                Log.e("_xTAG", "Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun agregarPresiosDeServicioAOrdenDeVentaSoloEnUiState(precio_servicio: Float) {

        val NOMBRE_FUN = "agregarPresiosDeServicioAOrdenDeVentaSoloEnUiState"

        viewModelScope.launch(Dispatchers.IO) {

            try {
                _uiState.update {

                    var lista = it.nuevos_precios_de_servicios_por_agregar_a_la_orden

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

    fun sumarTodosLosPreciosDeListaDeServiciosAgregados(lista_precios: List<Float>) {
        viewModelScope.launch(Dispatchers.IO) {

            var lista_solo_precios = mutableListOf<Float>()

            _uiState.update {
                it.copy(
                    suma_precios_servicios_agregados_a_orden = mutableFloatStateOf(
                        mSumaValoresDeItemsDeUnaLista.sumar(
                            lista_solo_precios
                        )
                    )
                )
            }
        }
    }

    fun sumarTodosLosPreciosDeListaDeServiciosPorAgregar(lista_precios: List<Float>) {
        viewModelScope.launch(Dispatchers.IO) {

            _uiState.update {
                it.copy(
                    suma_precios_servicios_por_agregar_a_orden = mutableFloatStateOf(
                        mSumaValoresDeItemsDeUnaLista.sumar(
                            lista_precios
                        )
                    )
                )
            }
        }
    }

    // MOVER A VIEWMODEL -> DetalleOrdenServicioViewModel
    fun listarTodasLosServiciosAgregadosALaOrden(id_orden_venta: Int) {

        val NOMBRE_FUN = "listarTodasLosServiciosAgregadosALaOrden"

        //todos_los_servicios_agregados_a_la_orden
        viewModelScope.launch(Dispatchers.IO) {

            try {
                mListarLosServiciosAgregadosAUnaOrdenVentaUseCase(id_orden_venta = id_orden_venta).collect{

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

    fun limpiarDatosUiState() {

        viewModelScope.launch(Dispatchers.IO) {

            _uiState.update {
                it.copy(
                    fecha_y_hora = "",
                    nuevos_servicios_por_agregar_a_la_orden = mutableStateListOf(),
                    todos_los_servicios_agregados_a_la_orden = mutableStateListOf(),
                    todos_los_precios_de_servicios_agregados_a_la_orden = mutableStateListOf(0f),
                    suma_precios_servicios_agregados_a_orden = mutableFloatStateOf(0f),
                    suma_precios_servicios_por_agregar_a_orden = mutableFloatStateOf(0f),
                )
            }
        }
    }

    /** ViewModelFactori **/
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // val savedStateHandle = createSavedStateHandle()
                val mInsertarVariosDetallesDeOrdenServiciosUseCase =
                    (this[APPLICATION_KEY] as MyApplication).mInsertarVariosDetallesDeOrdenServiciosUseCase
                val mListarLosServiciosDeOrdenVentaUseCase =
                    (this[APPLICATION_KEY] as MyApplication).mListarLosServiciosAgregadoAUnaOrdenVentaUseCase
                DetalleOrdenServicioViewModel(
                    mInsertarVariosDetallesDeOrdenServiciosUseCase = mInsertarVariosDetallesDeOrdenServiciosUseCase,
                    mListarLosServiciosAgregadosAUnaOrdenVentaUseCase = mListarLosServiciosDeOrdenVentaUseCase,
                )
            }
        }
    }
}
