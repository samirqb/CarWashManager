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
import samirqb.carwashmanager.app.viewmodels.uistates.ClienteUiState
import samirqb.lib.helpers.FechaYHora
import samirqb.lib.personas.uc.AgregarClienteUseCase
import samirqb.lib.personas.uc.ListarTodosLosClientesUseCase
import samirqb.lib.personas.entities.ClienteEntity
import samirqb.lib.personas.uc.BuscarClientePorIdUseCase


class ClienteViewModel(
    private val mListarTodosLosClientesUseCase: ListarTodosLosClientesUseCase,
    private val mAgregarClienteUseCase: AgregarClienteUseCase,
    private val mBuscarClientePorIdUseCase: BuscarClientePorIdUseCase,
):ViewModel() {

    private val NOMBRE_CLASE = "ClienteViewModel"

    private val _uiState = MutableStateFlow(ClienteUiState())
    val uiState: StateFlow<ClienteUiState> = _uiState.asStateFlow()

    init {
        listarTodosLosClientesUC()
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

    fun listarTodosLosClientesUC(){

        val NOMBRE_FUN = "listarTodosLosClientesUC"

        viewModelScope.launch(Dispatchers.IO) {

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

        viewModelScope.launch(Dispatchers.IO) {

            try {
                mAgregarClienteUseCase(mTEntity)
            }catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }


    fun buscarClientePorIdUseCase(id:String){

        val NOMBRE_FUN = "buscarClientePorIdUseCase"

        viewModelScope.launch(Dispatchers.IO) {

            try {

                _uiState.update{

                    it.copy(
                        cliente_id_value_buscado = id
                    )
                }

                mBuscarClientePorIdUseCase(id).collect{

                    var resultado_busqueda_cliente = it

                    _uiState.update{

                        it.copy(
                            resultado_busqueda_cliente = resultado_busqueda_cliente
                        )
                    }
                }
            } catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun limpiarResultadoDeBusqueda(){

        val NOMBRE_FUN = "limpiarResultadoDeBusqueda"

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    resultado_busqueda_cliente = null
                )
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
                val mBuscarClientePorIdUseCase = (this[APPLICATION_KEY] as MyApplication).mBuscarClientePorIdUseCase
                ClienteViewModel(
                    mListarTodosLosClientesUseCase = mListarTodosLosClientesUseCase,
                    mAgregarClienteUseCase = mAgregarClienteUseCase,
                    mBuscarClientePorIdUseCase = mBuscarClientePorIdUseCase,
                )
            }
        }
    }
}