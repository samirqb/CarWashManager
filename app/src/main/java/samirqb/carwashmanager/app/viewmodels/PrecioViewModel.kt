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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import samirqb.carwashmanager.app.MyApplication
import samirqb.carwashmanager.app.viewmodels.uistates.PrecioUiState
import samirqb.lib.helpers.FechaYHora
import samirqb.lib.ofertas.entities.PrecioEntity
import samirqb.lib.ofertas.uc.AgregarPrecioUseCase
import samirqb.lib.ofertas.uc.ListarTodosLosPreciosUseCase
import samirqb.lib.ofertas.uc.ObtenerElPrecioMasRecienteUseCase

class PrecioViewModel(
    private val mAgregarPrecioUseCase: AgregarPrecioUseCase,
    private val mObtenerElPrecioMasRecienteUseCase: ObtenerElPrecioMasRecienteUseCase,
    private val mListarTodosLosPreciosUseCase: ListarTodosLosPreciosUseCase,
) : ViewModel() {

    private val NOMBRE_CLASE = "PrecioViewModel"

    private val _uiState = MutableStateFlow(PrecioUiState())
    val uiState: StateFlow<PrecioUiState> = _uiState.asStateFlow()

    init {
        listarTodosLosPreciosUseCase()
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

    fun agregarPrecioUseCase(mTEntity: PrecioEntity) {

        val NOMBRE_FUN = "agregarPrecioUseCase"

        viewModelScope.launch {

            try {
                mAgregarPrecioUseCase(mTEntity)
            } catch (e: Exception) {
                Log.e("_xTAG", "Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun obtenerPrecioMasRecienteUseCase() {

        val NOMBRE_FUN = "obtenerPrecioMasRecienteUseCase"

        viewModelScope.launch {
            try {
                mObtenerElPrecioMasRecienteUseCase().collect {
                    var precioEntity = it

                    _uiState.update {
                        it.copy(
                            precio_mas_reciente = precioEntity
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("_xTAG", "Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun listarTodosLosPreciosUseCase() {

        val NOMBRE_FUN = "listarTodosLosPreciosUseCase"

        viewModelScope.launch {

            try {
                mListarTodosLosPreciosUseCase().collect {

                    var lista = it

                    _uiState.update {

                        it.copy(
                            todos_los_precios = lista
                        )
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
                val mAgregarPrecioUseCase =
                    (this[APPLICATION_KEY] as MyApplication).mAgregarPrecioUseCase
                val mObtenerElPrecioMasRecienteUseCase =
                    (this[APPLICATION_KEY] as MyApplication).mObtenerElPrecioMasRecienteUseCase
                val mListarTodosLosPreciosUseCase =
                    (this[APPLICATION_KEY] as MyApplication).mListarTodosLosPreciosUseCase
                PrecioViewModel(
                    mAgregarPrecioUseCase = mAgregarPrecioUseCase,
                    mObtenerElPrecioMasRecienteUseCase = mObtenerElPrecioMasRecienteUseCase,
                    mListarTodosLosPreciosUseCase = mListarTodosLosPreciosUseCase,
                )
            }
        }
    }
}