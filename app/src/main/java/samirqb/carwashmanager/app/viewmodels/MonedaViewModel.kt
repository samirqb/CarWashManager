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
import samirqb.carwashmanager.app.viewmodels.uistates.MonedaUiState
import samirqb.lib.caja.entidades.MonedaEntity
import samirqb.lib.caja.repositories.MonedaRepository

class MonedaViewModel(
    val repositorio: MonedaRepository
):ViewModel() {

    val _uiState = MutableStateFlow(MonedaUiState())
    val uiState: StateFlow<MonedaUiState> = _uiState.asStateFlow()

    fun actualizarMonedaEntity(mMonedaEntity: MonedaEntity){
        _uiState.update{
            it.copy(
                mMonedaEntity = mMonedaEntity
            )
        }
    }

    fun leerTodo() {
        viewModelScope.launch {

            // se lee el repositorio
            repositorio.leerTodo().collect{ it ->

                val todasLasMonedas = it

                // actualizamos el estado que lee la UI
                _uiState.update {
                    it.copy(todasLasMonedas = todasLasMonedas)
                }
            }
        }
    }

    fun agregarMoneda(mTEntity:MonedaEntity){
        viewModelScope.launch {
            repositorio.insertar(mTEntity = mTEntity)
        }
    }

    /** ViewModelFactori **/
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val myRepository = (this[APPLICATION_KEY] as MyApplication).mMonedaRepository
                MonedaViewModel(
                    repositorio = myRepository,
                )
            }
        }
    }
}