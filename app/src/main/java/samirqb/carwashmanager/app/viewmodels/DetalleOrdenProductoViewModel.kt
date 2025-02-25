package samirqb.carwashmanager.app.viewmodels

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
import samirqb.carwashmanager.app.viewmodels.uistates.DetalleOrdenProductoUiState

class DetalleOrdenProductoViewModel(): ViewModel() {

    private val _uiState = MutableStateFlow(DetalleOrdenProductoUiState())
    val uiState: StateFlow<DetalleOrdenProductoUiState> = _uiState.asStateFlow()

    /** ViewModelFactori **/
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
//                val mInsertarVariosDetallesDeOrdenServiciosUseCase =
//                    (this[APPLICATION_KEY] as MyApplication).mInsertarVariosDetallesDeOrdenServiciosUseCase
                DetalleOrdenProductoViewModel(
//                    mInsertarVariosDetallesDeOrdenServiciosUseCase = mInsertarVariosDetallesDeOrdenServiciosUseCase,
                )
            }
        }
    }

    fun limpiarDatosUiState() {

        viewModelScope.launch(Dispatchers.IO) {

            _uiState.update {
                it.copy(

                )
            }
        }
    }

}