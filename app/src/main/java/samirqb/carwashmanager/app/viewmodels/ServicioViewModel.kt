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
import samirqb.carwashmanager.app.viewmodels.uistates.ServicioUiState
import samirqb.lib.helpers.FechaYHora
import samirqb.lib.ofertas.entities.ServicioEntity
import samirqb.lib.ofertas.uc.AgregarServicioUseCase
import samirqb.lib.ofertas.uc.ListarTodosLosServiciosUseCase

class ServicioViewModel(
    private val mAgregarServicioUseCase: AgregarServicioUseCase,
    private val mListarTodosLosServiciosUseCase: ListarTodosLosServiciosUseCase,
):ViewModel() {

    private val NOMBRE_CLASE = "ServicioViewModel"

    private val _uiState = MutableStateFlow(ServicioUiState())
    val uiState: StateFlow<ServicioUiState> = _uiState.asStateFlow()

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

    fun agregarServicioUseCase(mTEntity:ServicioEntity){
        val NOMBRE_FUN = "agregarServicioUseCase"

        viewModelScope.launch {

            try {
                mAgregarServicioUseCase(mTEntity)
            } catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun listarTodosLosServiciosUC(){

        val NOMBRE_FUN = "listarTodosLosServiciosUseCase"

        viewModelScope.launch {

            try {
                mListarTodosLosServiciosUseCase().collect{

                    var lista = it

                    _uiState.update{

                        it.copy(
                            todos_los_servicios = lista
                        )
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
                val mAgregarServiciosUseCase = (this[APPLICATION_KEY] as MyApplication).mAgregarServicioUseCase
                val mListarTodosLosServiciosUseCase = (this[APPLICATION_KEY] as MyApplication).mListarTodosLosServiciosUseCase
                ServicioViewModel(
                    mAgregarServicioUseCase  = mAgregarServiciosUseCase,
                    mListarTodosLosServiciosUseCase = mListarTodosLosServiciosUseCase,
                )
            }
        }
    }

}