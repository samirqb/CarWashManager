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
import samirqb.carwashmanager.app.viewmodels.uistates.OperarioUiState
import samirqb.lib.helpers.FechaYHora
import samirqb.lib.personas.cu.AgregarOperarioUseCase
import samirqb.lib.personas.cu.ListarTodosLosOperariosUseCase
import samirqb.lib.personas.entities.OperarioEntity

class OperarioViewModel(
    private val mAgregarOperarioUC: AgregarOperarioUseCase,
    private val mListarTodosLosOperariosUC: ListarTodosLosOperariosUseCase
): ViewModel() {

    private val NOMBRE_CLASE = "OperarioViewModel"

    private val _uiState = MutableStateFlow(OperarioUiState())
    val uiState: StateFlow<OperarioUiState> = _uiState.asStateFlow()

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

    fun listarTodosLosOperariosUC(){

        val NOMBRE_FUN = "listarTodosLosOperariosUC"

        viewModelScope.launch {

            try {
                mListarTodosLosOperariosUC().collect{

                    var lista = it

                    _uiState.update{

                        it.copy(
                            todos_los_operarios = lista
                        )
                    }
                }
            } catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }


    fun agregarOperariosUC(mTEntity: OperarioEntity){

        val NOMBRE_FUN = "agregarOperariosUC"

        viewModelScope.launch {

            try {
                mAgregarOperarioUC(mTEntity)
            }catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }


    /** ViewModelFactori **/
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // val savedStateHandle = createSavedStateHandle()
                val mAgregarOperarioUseCase = (this[APPLICATION_KEY] as MyApplication).mAgregarOperarioUseCase
                val mListarTodosLosOperariosUseCase = (this[APPLICATION_KEY] as MyApplication).mListarTodosLosOperariosUseCase
                OperarioViewModel(
                    mListarTodosLosOperariosUC = mListarTodosLosOperariosUseCase,
                    mAgregarOperarioUC = mAgregarOperarioUseCase,
                )
            }
        }
    }
}