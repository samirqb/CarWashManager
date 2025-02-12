package samirqb.carwashmanager.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import samirqb.carwashmanager.app.MyApplication
import samirqb.carwashmanager.app.viewmodels.uistates.TipoMonedaUiState
import samirqb.lib.caja.entidades.TipoMonedaEntity
import samirqb.lib.caja.repositories.TipoMonedaRepository

class TipoMonedaViewModel(
    val repositorio: TipoMonedaRepository
): ViewModel() {

    // UI state
    private val _uiState = MutableStateFlow(TipoMonedaUiState())
    val uiState: StateFlow<TipoMonedaUiState> = _uiState.asStateFlow()

    init {
        leerTodo()
    }

    fun actualizarTipoMonedaEntity(mTipoMonedaEntity: TipoMonedaEntity){
        _uiState.update{
            it.copy(
                mTipoMonedaEntity = mTipoMonedaEntity
            )
        }
    }

    fun leerTodo() {
        viewModelScope.launch {

            // se lee el repositorio
            repositorio.leerTodo().collect{ it ->

                val todoslosTiposMoneda = it

                // actualizamos el estado que lee la UI
                _uiState.update {
                    it.copy(todoslosTiposMoneda = todoslosTiposMoneda.toMutableList())
                }
            }
        }
    }

    /** ViewModelFactori **/
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val myRepository = (this[APPLICATION_KEY] as MyApplication).mTipoMonedaRepository
                TipoMonedaViewModel(
                    repositorio = myRepository,
                )
            }
        }
    }
}