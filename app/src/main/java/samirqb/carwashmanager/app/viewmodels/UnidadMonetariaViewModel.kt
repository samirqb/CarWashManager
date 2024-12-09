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
import samirqb.carwashmanager.app.viewmodels.uistates.UnidadMonetariaUiState
import samirqb.lib.caja.entidades.UnidadMonetariaEntity
import samirqb.lib.caja.repositories.UnidadMonetariaRepository


class UnidadMonetariaViewModel(
    val reposotorio: UnidadMonetariaRepository
): ViewModel() {

    // UI state
    private val _uiState = MutableStateFlow(UnidadMonetariaUiState())
    val uiState: StateFlow<UnidadMonetariaUiState> = _uiState.asStateFlow()

    fun actualizarUnidadMonetariaEntity(mUnidadMonetariaEntity: UnidadMonetariaEntity){
        _uiState.update{
            it.copy(
                mUnidadMonetariaEntity = mUnidadMonetariaEntity
            )
        }
    }



    fun leerTodo() {
        viewModelScope.launch {

            // se lee el repositorio
            reposotorio.leerTodo().collect{ it ->

                val lista_unidades_monetarias_entity = it

                // actualizamos el estado que lee la UI
                _uiState.update {
                    it.copy(lista_unidades_monetarias = lista_unidades_monetarias_entity)
                }
            }
        }
    }


    /** ViewModelFactori **/
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val myRepository = (this[APPLICATION_KEY] as MyApplication).mUnidadMonetariaRepository
                UnidadMonetariaViewModel(
                    reposotorio = myRepository,
                )
            }
        }
    }
}