package samirqb.carwashmanager.app.viewmodels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import samirqb.carwashmanager.app.viewmodels.uistates.OrdenDePagoNominaUiState
import samirqb.lib.helpers.FechaYHora
import samirqb.lib.pagos.entities.OrdenPagoNominaEntity
import samirqb.lib.pagos.uc.AgregarOrdenDePagoNominaUseCase
import samirqb.lib.pagos.uc.ListarOrdenesDePagoPorOperarioIdYEstadoDePagoUseCase
import samirqb.lib.pagos.uc.ListarTodasLasOrdenesDePagoNominaPorEstadoDePagoUseCase
import samirqb.lib.pagos.uc.ListarTodasLasOrdenesDePagoNominaUseCase
import samirqb.lib.pagos.uc.ObtenerCantidadDeRegistrosDeOrdenesDePagoUseCase
import samirqb.lib.pagos.uc.ObtenerOrdenDePagoNominaMasRecienteUseCase

class OrdenDePagoNominaViewModel(
    private val mAgregarOrdenDePagoNominaUseCase: AgregarOrdenDePagoNominaUseCase,
    private val mListarTodasLasOrdenesDePagoNominaUseCase: ListarTodasLasOrdenesDePagoNominaUseCase,
    private val mListarTodasLasOrdenesDePagoNominaPorVigenciaUseCase: ListarTodasLasOrdenesDePagoNominaPorEstadoDePagoUseCase,
    private val mListarOrdenesDePagoNominaPorOperarioIdYVigenciaUseCase: ListarOrdenesDePagoPorOperarioIdYEstadoDePagoUseCase,
    private val mObtenerOrdenDePagoNominaMasRecienteUseCase: ObtenerOrdenDePagoNominaMasRecienteUseCase,
    private val mObtenerCantidadDeRegistrosDeOrdenesDePagoUseCase: ObtenerCantidadDeRegistrosDeOrdenesDePagoUseCase,
): ViewModel() {

    private val NOMBRE_CLASE = "OrdenDePagoNominaViewModel"

    private val _uiState = MutableStateFlow(OrdenDePagoNominaUiState())
    val uiState: StateFlow<OrdenDePagoNominaUiState> = _uiState.asStateFlow()

    init {
        listarTodasLasOrdenesDePagoNomina()
        listarTodasLasOrdenesDePagoNominaNoVigentes()
        listarTodasLasOrdenesDePagoNominaVigentes()
        calcularIdProximaOrdenPagoNomina()
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

    fun numeroActualDeOrdenDePagoNomina(numero: Int){

        val NOMBRE_FUN = "numeroActualDeOrdenDePagoNomina"

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.update {
                    it.copy(
                        numero_de_orden_de_pago_actual = numero
                    )
                }
            } catch (e: Exception) {
                Log.e("_xTAG", "Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun calcularIdProximaOrdenPagoNomina(){

        val NOMBRE_FUN = "calcularIdProximaOrdenPagoNomina"

        viewModelScope.launch {
            try {
                mObtenerCantidadDeRegistrosDeOrdenesDePagoUseCase().collect {

                    var numero_calculado = it + 1

                    _uiState.update {
                        it.copy(
                            numero_calculado_para_nueva_orden_de_pago = numero_calculado
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("_xTAG", "Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun agregarOrdenDePagoNomina(mEntity: OrdenPagoNominaEntity){

        val NOMBRE_FUN = "agregarOrdenDePagoNomina"

        viewModelScope.launch(Dispatchers.IO) {
            try {
                mAgregarOrdenDePagoNominaUseCase(mEntity)
            } catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }

        }
    }

    fun listarTodasLasOrdenesDePagoNomina(){

        val NOMBRE_FUN = "listarTodasLasOrdenesDePagoNomina"

        viewModelScope.launch(Dispatchers.IO) {
            try {
                mListarTodasLasOrdenesDePagoNominaUseCase().collect{

                    var lista = it

                    _uiState.update{
                        it.copy(lista_todas_las_ordenes_pago_nomina = lista.toMutableList())
                    }

                }
            } catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }

        }
    }

    fun listarTodasLasOrdenesDePagoNominaNoVigentes(){

        val NOMBRE_FUN = "listarTodasLasOrdenesDePagoNominaNoVigentes"

        val orden_vigente = false

        viewModelScope.launch(Dispatchers.IO) {
            try {
                mListarTodasLasOrdenesDePagoNominaPorVigenciaUseCase(orden_vigente).collect{

                    var lista = it

                    _uiState.update{
                        it.copy(lista_ordenes_pago_nomina_no_vigentes = lista.toMutableList())
                    }

                }
            } catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun listarTodasLasOrdenesDePagoNominaPorOperarioYNoVigentes(operario_id:String){

        val NOMBRE_FUN = "listarTodasLasOrdenesDePagoNominaPorOperarioYNoVigentes"

        var orden_vigente = false

        viewModelScope.launch(Dispatchers.IO) {
            try {
                mListarOrdenesDePagoNominaPorOperarioIdYVigenciaUseCase(operario_id,orden_vigente).collect{

                    var lista = it

                    _uiState.update{
                        it.copy(lista_ordenes_pago_nomina_por_operario_id_no_vigentes = lista.toMutableList())
                    }

                }
            } catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun listarTodasLasOrdenesDePagoNominaVigentes(){

        val NOMBRE_FUN = "listarTodasLasOrdenesDePagoNominaVigentes"

        val orden_vigente = true

        viewModelScope.launch(Dispatchers.IO) {
            try {
                mListarTodasLasOrdenesDePagoNominaPorVigenciaUseCase(orden_vigente).collect{

                    var lista = it

                    _uiState.update{
                        it.copy(lista_ordenes_pago_nomina_vigentes = lista.toMutableList())
                    }

                }
            } catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    // ---> lista_ordenes_pago_nomina_por_operario_id_vigentes
    fun listarTodasLasOrdenesDePagoNominaPorOperarioIdVigente(operario_id:String){

        val NOMBRE_FUN = "listarTodasLasOrdenesDePagoNominaPorOperarioYNoVigente"

        var orden_vigente = true

        viewModelScope.launch(Dispatchers.IO) {
            try {
                mListarOrdenesDePagoNominaPorOperarioIdYVigenciaUseCase(operario_id,orden_vigente).collect{

                    var lista = it

                    _uiState.update{
                        it.copy(
                            lista_ordenes_pago_nomina_por_operario_id_vigentes = lista.toMutableList()
                        )
                    }
                }
            } catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun limpiarDatosUiState() {

        viewModelScope.launch(Dispatchers.IO) {

            _uiState.update {
                it.copy(
                    fecha_y_hora = "",
                    numero_calculado_para_nueva_orden_de_pago = 0,
                    numero_de_orden_de_pago_actual = 0,
                    lista_todas_las_ordenes_pago_nomina = mutableStateListOf(),
                    lista_ordenes_pago_nomina_no_vigentes = mutableStateListOf(),
                    lista_ordenes_pago_nomina_por_operario_id_no_vigentes = mutableStateListOf(),
                    lista_ordenes_pago_nomina_vigentes = mutableStateListOf(),
                    lista_ordenes_pago_nomina_por_operario_id_vigentes = mutableStateListOf()
                )
            }
        }
    }


    /** ViewModelFactori **/
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // val savedStateHandle = createSavedStateHandle()
                val mAgregarOrdenDePagoNominaUseCase = (this[APPLICATION_KEY] as MyApplication).mAgregarOrdenDePagoNominaUseCase
                val mListarTodasLasOrdenesDePagoNominaUseCase = (this[APPLICATION_KEY] as MyApplication).mListarTodasLasOrdenesDePagoNominaUseCase
                val mListarTodasLasOrdenesDePagoNominaPorVigenciaUseCase = (this[APPLICATION_KEY] as MyApplication).mListarTodasLasOrdenesDePagoNominaPorVigenciaUseCase
                val mListarOrdenesDePagoNominaPorOperarioIdYVigenciaUseCase = (this[APPLICATION_KEY] as MyApplication).mListarOrdenesDePagoNominaPorOperarioIdYVigenciaUseCase
                val mObtenerOrdenDePagoNominaMasRecienteUseCase = (this[APPLICATION_KEY] as MyApplication).mObtenerOrdenDePagoNominaMasRecienteUseCase
                val mObtenerCantidadDeRegistrosDeOrdenesDePagoUseCase = (this[APPLICATION_KEY] as MyApplication).mObtenerCantidadDeRegistrosDeOrdenesDePagoUseCase

                OrdenDePagoNominaViewModel(
                    mAgregarOrdenDePagoNominaUseCase = mAgregarOrdenDePagoNominaUseCase,
                    mListarTodasLasOrdenesDePagoNominaUseCase  = mListarTodasLasOrdenesDePagoNominaUseCase,
                    mListarTodasLasOrdenesDePagoNominaPorVigenciaUseCase = mListarTodasLasOrdenesDePagoNominaPorVigenciaUseCase,
                    mListarOrdenesDePagoNominaPorOperarioIdYVigenciaUseCase = mListarOrdenesDePagoNominaPorOperarioIdYVigenciaUseCase,
                    mObtenerOrdenDePagoNominaMasRecienteUseCase = mObtenerOrdenDePagoNominaMasRecienteUseCase,
                    mObtenerCantidadDeRegistrosDeOrdenesDePagoUseCase = mObtenerCantidadDeRegistrosDeOrdenesDePagoUseCase
                )
            }
        }
    }
}