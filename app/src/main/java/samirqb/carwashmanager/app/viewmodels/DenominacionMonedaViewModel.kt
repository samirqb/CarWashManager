package samirqb.carwashmanager.app.viewmodels

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
import samirqb.carwashmanager.app.viewmodels.uistates.DenomoninacionMonedaUiState
import samirqb.lib.caja.entidades.DenominacionMonedaEntity
import samirqb.lib.caja.repositories.DenominacionMonedaRepository

class DenominacionMonedaViewModel(
    val reposotorio: DenominacionMonedaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DenomoninacionMonedaUiState())
    val uiState: StateFlow<DenomoninacionMonedaUiState> = _uiState.asStateFlow()

    init{
        leerTodo()
    }

    fun actualizarDenominacionMonedaEntity(mDenominacionMonedaEntity: DenominacionMonedaEntity){
        _uiState.update{
            it.copy(
                mDenominacionMonedaEntity = mDenominacionMonedaEntity
            )
        }
    }

    fun leerTodo() {
        viewModelScope.launch {

            // se lee el repositorio
            reposotorio.leerTodo().collect{ it ->

                val todoslasDSenominacionesMoneda = it

                // actualizamos el estado que lee la UI
                _uiState.update {
                    it.copy( todasLasDenominacionesMoneda = todoslasDSenominacionesMoneda.toMutableList())
                }
            }
        }
    }

    fun agregarDenominacion(mTEntity: DenominacionMonedaEntity){
        viewModelScope.launch {
            reposotorio.insertar(mTEntity)
        }
    }

    /** ViewModelFactori **/
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // val savedStateHandle = createSavedStateHandle()
                val myRepository = (this[APPLICATION_KEY] as MyApplication).mDenominacionMonedaRepository
                DenominacionMonedaViewModel(
                    reposotorio = myRepository,
                )
            }
        }
    }
}