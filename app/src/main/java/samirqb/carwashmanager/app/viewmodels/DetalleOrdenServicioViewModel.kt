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
import samirqb.carwashmanager.app.viewmodels.uistates.DetalleOrdenServicioUiState
import samirqb.lib.helpers.FechaYHora
import samirqb.lib.ventas.entities.DetalleOrdenServicioEntity
import samirqb.lib.ventas.uc.InsertarVariosDetallesDeOrdenServiciosUseCase

class DetalleOrdenServicioViewModel(
    private val mInsertarVariosDetallesDeOrdenServiciosUseCase: InsertarVariosDetallesDeOrdenServiciosUseCase
): ViewModel() {

    private val NOMBRE_CLASE = "DetalleOrdenServicioViewModel"

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

        viewModelScope.launch(Dispatchers.IO) {
            try {
                mInsertarVariosDetallesDeOrdenServiciosUseCase(mEntities)
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
                val mInsertarVariosDetallesDeOrdenServiciosUseCase = (this[APPLICATION_KEY] as MyApplication).mInsertarVariosDetallesDeOrdenServiciosUseCase
                DetalleOrdenServicioViewModel(
                    mInsertarVariosDetallesDeOrdenServiciosUseCase = mInsertarVariosDetallesDeOrdenServiciosUseCase,
                )
            }
        }
    }
}