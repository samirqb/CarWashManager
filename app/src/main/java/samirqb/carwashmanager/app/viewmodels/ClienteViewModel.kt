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
import samirqb.carwashmanager.app.viewmodels.uistates.ClienteUiState
import samirqb.lib.helpers.FechaYHora
import samirqb.lib.personas.uc.AgregarClienteUseCase
import samirqb.lib.personas.uc.ListarTodosLosClientesUseCase
import samirqb.lib.personas.entities.ClienteEntity


class ClienteViewModel(
    private val mListarTodosLosClientesUseCase: ListarTodosLosClientesUseCase,
    private val mAgregarClienteUseCase: AgregarClienteUseCase,
):ViewModel() {

    private val NOMBRE_CLASE = "ClienteViewModel"

    private val _uiState = MutableStateFlow(ClienteUiState())
    val uiState: StateFlow<ClienteUiState> = _uiState.asStateFlow()

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

    fun listarTodosLosClientesUC(){

        val NOMBRE_FUN = "listarTodosLosClientesUC"

        viewModelScope.launch {

            try {
                mListarTodosLosClientesUseCase().collect{

                    var lista = it

                    _uiState.update{

                        it.copy(
                            todos_los_clientes = lista
                        )
                    }
                }
            } catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }


    fun agregarClienteUC(mTEntity: ClienteEntity){

        val NOMBRE_FUN = "agregarClienteUC"

        viewModelScope.launch {

            try {
                mAgregarClienteUseCase(mTEntity)
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
                val mAgregarClienteUseCase = (this[APPLICATION_KEY] as MyApplication).mAgregarClienteUseCase
                val mListarTodosLosClientesUseCase = (this[APPLICATION_KEY] as MyApplication).mListarTodosLosClientesUseCase
                ClienteViewModel(
                    mListarTodosLosClientesUseCase = mListarTodosLosClientesUseCase,
                    mAgregarClienteUseCase = mAgregarClienteUseCase,
                )
            }
        }
    }
}