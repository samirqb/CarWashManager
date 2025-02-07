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
import samirqb.carwashmanager.app.viewmodels.uistates.OrdenDeVentaUiState
import samirqb.lib.helpers.FechaYHora
import samirqb.lib.ventas.entities.OrdenDeVentaEntity
import samirqb.lib.ventas.uc.CrearNuevaOrdenDeVentaUseCase
import samirqb.lib.ventas.uc.ListarTodasLasOrdenesDeVentasUseCase

class OrdenDeVentaViewModel(
    private val mCrearNuevaOrdenDeVentaUseCase: CrearNuevaOrdenDeVentaUseCase,
    private val mListarTodasLasOrdenesDeVentasUseCase: ListarTodasLasOrdenesDeVentasUseCase,
) : ViewModel() {

    private val NOMBRE_CLASE = "OrdenDeVentaViewModel"

    private val _uiState = MutableStateFlow(OrdenDeVentaUiState())
    val uiState: StateFlow<OrdenDeVentaUiState> = _uiState.asStateFlow()


    init {
        listarTodasLasOrdenesDeVentasUC()
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
                    val lista = it

                    _uiState.update {
                        it.copy(todas_las_ordenes_de_venta = lista)
                    }
                }
            } catch (e: Exception) {
                Log.e("_xTAG", "Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
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
                OrdenDeVentaViewModel(
                    mCrearNuevaOrdenDeVentaUseCase = mCrearNuevaOrdenDeVentaUseCase,
                    mListarTodasLasOrdenesDeVentasUseCase = mListarTodasLasOrdenesDeVentasUseCase,
                )
            }
        }
    }
}