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
import samirqb.carwashmanager.app.viewmodels.uistates.ServicioYPrecioUiState
import samirqb.lib.helpers.FechaYHora
import samirqb.lib.ofertas.entities.ServicioYPrecioEntity
import samirqb.lib.ofertas.uc.AgregarServicioYPrecioUseCase
import samirqb.lib.ofertas.uc.ListarTodosLosServiciosYPreciosActivosConNombreDelServicioUseCase
import samirqb.lib.ofertas.uc.ListarTodosLosServiciosYPreciosActivosUseCase
import samirqb.lib.ofertas.uc.ListarTodosLosServiciosYPreciosConNomnreUseCase
import samirqb.lib.ofertas.uc.ListarTodosLosServiciosYPreciosUseCase

class ServicioYPrecioViewModel(
    private val mAgregarServicioYPrecioUseCase: AgregarServicioYPrecioUseCase,
    private val mListarTodosLosServiciosYPreciosUseCase: ListarTodosLosServiciosYPreciosUseCase,
    private val mListarTodosLosServiciosYPreciosConNomnreUseCase: ListarTodosLosServiciosYPreciosConNomnreUseCase,
    private val mListarTodosLosServiciosYPreciosActivosUseCase: ListarTodosLosServiciosYPreciosActivosUseCase,
    private val mListarTodosLosServiciosYPreciosActivosConNombreDelServicioUseCase: ListarTodosLosServiciosYPreciosActivosConNombreDelServicioUseCase,
):ViewModel() {

    private val NOMBRE_CLASE = "ServicioYPrecioViewModel"

    private val _uiState = MutableStateFlow(ServicioYPrecioUiState())
    val uiState: StateFlow<ServicioYPrecioUiState> = _uiState.asStateFlow()

    init {
        //listarTodosLosServiciosYPreciosActivosUseCase()
        //listarTodosLosServiciosYPreciosInactivosUseCase()
        listarTodosLosServiciosYPreciosConNombreUC()
        listarTodosLosServiciosYPreciosActivosConNombreDelServicio()
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

                    var lista = it.toMutableList()

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

    fun listarTodosLosServiciosYPreciosConNombreUC(){

        val NOMBRE_FUN = "listarTodosLosServiciosYPreciosConNombreUC"

        viewModelScope.launch {

            try {
                mListarTodosLosServiciosYPreciosConNomnreUseCase().collect(){

                    var lista = it.toMutableMap()

                    _uiState.update{

                        it.copy(
                            todos_los_servicios_y_precios_con_nombre = lista
                        )
                    }
                }
            } catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun listarTodosLosServiciosYPreciosActivosUseCase(){

        val NOMBRE_FUN = "listarTodosLosServiciosYPreciosActivosUseCase"

        val PRECIO_ACTIVO = true

        viewModelScope.launch {

            try {
                mListarTodosLosServiciosYPreciosActivosUseCase(PRECIO_ACTIVO).collect{

                    var lista = it

                    _uiState.update{

                        it.copy(
                            listar_todos_los_servicios_y_precios_activos = lista.toMutableList()
                        )
                    }
                }
            } catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun listarTodosLosServiciosYPreciosInactivosUseCase(){

        val NOMBRE_FUN = "listarTodosLosServiciosYPreciosInactivosUseCase"

        val PRECIO_ACTIVO = false

        viewModelScope.launch {

            try {
                mListarTodosLosServiciosYPreciosActivosUseCase(PRECIO_ACTIVO).collect{

                    var lista = it

                    _uiState.update{

                        it.copy(
                            todos_los_servicios_y_precios_inactivos = lista.toMutableList()
                        )
                    }
                }
            } catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun listarTodosLosServiciosYPreciosActivosConNombreDelServicio(){

        val NOMBRE_FUN = "listarTodosLosServiciosYPreciosActivosConNombreDelServicio"

        val PRECIO_ACTIVO = true

        viewModelScope.launch {

            try {
                mListarTodosLosServiciosYPreciosActivosConNombreDelServicioUseCase(PRECIO_ACTIVO).collect{

                    var lista = it

                    _uiState.update{

                        it.copy(
                            //todos_los_servicios_y_precios_inactivos = lista
                            todos_los_servicios_y_precios_activos_y_nommbre_del_servicio = lista
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
                val mListarTodosLosServiciosYPreciosConNomnreUseCase = (this[APPLICATION_KEY] as MyApplication).mListarTodosLosServiciosYPreciosConNomnreUseCase
                val mListarTodosLosServiciosYPreciosActivosUseCase = (this[APPLICATION_KEY] as MyApplication).mListarTodosLosServiciosYPreciosActivosUseCase
                val mListarTodosLosServiciosYPreciosActivosConNombreDelServicioUseCase = (this[APPLICATION_KEY] as MyApplication).mListarTodosLosServiciosYPreciosActivosConNombreDelServicioUseCase
                ServicioYPrecioViewModel(
                    mAgregarServicioYPrecioUseCase = mAgregarServicioYPrecioUseCase,
                    mListarTodosLosServiciosYPreciosUseCase = mListarTodosLosServiciosYPreciosUseCase,
                    mListarTodosLosServiciosYPreciosConNomnreUseCase = mListarTodosLosServiciosYPreciosConNomnreUseCase,
                    mListarTodosLosServiciosYPreciosActivosUseCase = mListarTodosLosServiciosYPreciosActivosUseCase,
                    mListarTodosLosServiciosYPreciosActivosConNombreDelServicioUseCase = mListarTodosLosServiciosYPreciosActivosConNombreDelServicioUseCase,
                )
            }
        }
    }
}
