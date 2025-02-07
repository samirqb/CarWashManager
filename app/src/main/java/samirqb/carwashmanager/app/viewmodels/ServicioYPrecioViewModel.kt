package samirqb.carwashmanager.app.viewmodels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.compiler.plugins.kotlin.lower.forEachWith
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
import samirqb.carwashmanager.app.viewmodels.uistates.ServicioYPrecioUiState
import samirqb.lib.helpers.FechaYHora
import samirqb.lib.ofertas.entities.ServicioYPrecioEntity
import samirqb.lib.ofertas.uc.AgregarServicioYPrecioUseCase
import samirqb.lib.ofertas.uc.ListarTodosLosServiciosYPreciosUseCase

class ServicioYPrecioViewModel(
    private val mAgregarServicioYPrecioUseCase: AgregarServicioYPrecioUseCase,
    private val mListarTodosLosServiciosYPreciosUseCase: ListarTodosLosServiciosYPreciosUseCase
):ViewModel() {

    private val NOMBRE_CLASE = "ServicioYPrecioViewModel"

    private val _uiState = MutableStateFlow(ServicioYPrecioUiState())
    val uiState: StateFlow<ServicioYPrecioUiState> = _uiState.asStateFlow()

    init {
        listarTodosLosServiciosYPreciosUC()
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

    fun agregarServicioYPrecioUseCase(mTEntity: ServicioYPrecioEntity){

        val NOMBRE_FUN = "agregarServicioYPrecioUseCase"

        viewModelScope.launch {

            try {
                mAgregarServicioYPrecioUseCase(mTEntity)
            } catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.message}")
                e.stackTrace.forEach {
                    Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${it}")
                }
            }
        }
    }

    fun listarTodosLosServiciosYPreciosUC(){

        val NOMBRE_FUN = "listarTodosLosServiciosYPreciosUC"

        viewModelScope.launch {

            try {
                mListarTodosLosServiciosYPreciosUseCase().collect{

                    var lista = it

                    _uiState.update{

                        it.copy(
                            todos_los_servicios_y_precios = lista
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
                val mAgregarServicioYPrecioUseCase = (this[APPLICATION_KEY] as MyApplication).mAgregarServicioYPrecioUseCase
                val mListarTodosLosServiciosYPreciosUseCase = (this[APPLICATION_KEY] as MyApplication).mListarTodosLosServiciosYPreciosUseCase
                ServicioYPrecioViewModel(
                    mAgregarServicioYPrecioUseCase  = mAgregarServicioYPrecioUseCase,
                    mListarTodosLosServiciosYPreciosUseCase = mListarTodosLosServiciosYPreciosUseCase,
                )
            }
        }
    }
}