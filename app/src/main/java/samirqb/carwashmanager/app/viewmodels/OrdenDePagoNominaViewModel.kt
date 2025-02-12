package samirqb.carwashmanager.app.viewmodels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import samirqb.lib.pagos.uc.ObtenerOrdenDePagoNominaMasRecienteUseCase

class OrdenDePagoNominaViewModel(
    private val mAgregarOrdenDePagoNominaUseCase: AgregarOrdenDePagoNominaUseCase,
    private val mListarTodasLasOrdenesDePagoNominaUseCase: ListarTodasLasOrdenesDePagoNominaUseCase,
    private val mListarTodasLasOrdenesDePagoNominaPorEstadoDePagoUseCase: ListarTodasLasOrdenesDePagoNominaPorEstadoDePagoUseCase,
    private val mListarOrdenesDePagoPorOperarioIdYEstadoDePagoUseCase: ListarOrdenesDePagoPorOperarioIdYEstadoDePagoUseCase,
    private val mObtenerOrdenDePagoNominaMasRecienteUseCase: ObtenerOrdenDePagoNominaMasRecienteUseCase,
): ViewModel() {

    private val NOMBRE_CLASE = "OrdenDePagoNominaViewModel"

    private val _uiState = MutableStateFlow(OrdenDePagoNominaUiState())
    val uiState: StateFlow<OrdenDePagoNominaUiState> = _uiState.asStateFlow()

    init {
        listarTodasLasOrdenesDePagoNomina()
        listarTodasLasOrdenesDePagoNominaPagadas()
        listarTodasLasOrdenesDePagoNominaSinPagar()
        calcularIdOrdenPagoNomina()
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

    fun calcularIdOrdenPagoNomina(){

        val NOMBRE_FUN = "calcularIdOrdenPagoNomina"

        viewModelScope.launch {
            try {
                mObtenerOrdenDePagoNominaMasRecienteUseCase().collect {
                    var orden_mas_reciente = 0

                    if (it == null) {
                        orden_mas_reciente = 1
                    } else {
                        orden_mas_reciente = it.id_orden_pago_nomina_pk + 1
                    }
                    _uiState.update {
                        it.copy(
                            numero_de_orden_de_pago = orden_mas_reciente
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

    fun listarTodasLasOrdenesDePagoNominaPagadas(){

        val NOMBRE_FUN = "listarTodasLasOrdenesDePagoNomina"

        viewModelScope.launch(Dispatchers.IO) {
            try {
                mListarTodasLasOrdenesDePagoNominaPorEstadoDePagoUseCase(true).collect{

                    var lista = it

                    _uiState.update{
                        it.copy(lista_ordenes_pago_nomina_pagadas = lista.toMutableList())
                    }

                }
            } catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun listarTodasLasOrdenesDePagoNominaPorOperarioPagadas(operario_id:String){

        val NOMBRE_FUN = "listarTodasLasOrdenesDePagoNominaPorOperarioPagadas"

        var orden_pagada = true

        viewModelScope.launch(Dispatchers.IO) {
            try {
                mListarOrdenesDePagoPorOperarioIdYEstadoDePagoUseCase(operario_id,orden_pagada).collect{

                    var lista = it

                    _uiState.update{
                        it.copy(lista_ordenes_pago_nomina_por_operario_id_pagadas = lista.toMutableList())
                    }

                }
            } catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun listarTodasLasOrdenesDePagoNominaSinPagar(){

        val NOMBRE_FUN = "listarTodasLasOrdenesDePagoNominaSinPagadas"

        viewModelScope.launch(Dispatchers.IO) {
            try {
                mListarTodasLasOrdenesDePagoNominaPorEstadoDePagoUseCase(false).collect{

                    var lista = it

                    _uiState.update{
                        it.copy(lista_ordenes_pago_nomina_sin_pagar = lista.toMutableList())
                    }

                }
            } catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }

        }
    }

    fun listarTodasLasOrdenesDePagoNominaPorOperarioNoPagadas(operario_id:String){

        val NOMBRE_FUN = "listarTodasLasOrdenesDePagoNominaPorOperarioNoPagadas"

        var orden_pagada = false

        viewModelScope.launch(Dispatchers.IO) {
            try {
                mListarOrdenesDePagoPorOperarioIdYEstadoDePagoUseCase(operario_id,orden_pagada).collect{

                    var lista = it

                    _uiState.update{
                        it.copy(lista_ordenes_pago_nomina_por_operario_id_no_pagadas = lista.toMutableList())
                    }
                }
            } catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
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
                val mListarTodasLasOrdenesDePagoNominaPorEstadoDePagoUseCase = (this[APPLICATION_KEY] as MyApplication).mListarTodasLasOrdenesDePagoNominaPorEstadoDePagoUseCase
                val mListarOrdenesDePagoPorOperarioIdYEstadoDePagoUseCase = (this[APPLICATION_KEY] as MyApplication).mListarOrdenesDePagoPorOperarioIdYEstadoDePagoUseCase
                val mObtenerOrdenDePagoNominaMasRecienteUseCase = (this[APPLICATION_KEY] as MyApplication).mObtenerOrdenDePagoNominaMasRecienteUseCase

                OrdenDePagoNominaViewModel(
                    mAgregarOrdenDePagoNominaUseCase = mAgregarOrdenDePagoNominaUseCase,
                    mListarTodasLasOrdenesDePagoNominaUseCase  = mListarTodasLasOrdenesDePagoNominaUseCase,
                    mListarTodasLasOrdenesDePagoNominaPorEstadoDePagoUseCase = mListarTodasLasOrdenesDePagoNominaPorEstadoDePagoUseCase,
                    mListarOrdenesDePagoPorOperarioIdYEstadoDePagoUseCase = mListarOrdenesDePagoPorOperarioIdYEstadoDePagoUseCase,
                    mObtenerOrdenDePagoNominaMasRecienteUseCase = mObtenerOrdenDePagoNominaMasRecienteUseCase,
                )
            }
        }
    }
}