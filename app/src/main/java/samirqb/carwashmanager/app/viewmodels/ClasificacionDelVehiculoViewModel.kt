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
import samirqb.carwashmanager.app.viewmodels.uistates.ClasificacionDelVehiculoUiState
import samirqb.lib.helpers.FechaYHora
import samirqb.lib.vehiculos.entities.ClasificacionDelVehiculoEntity
import samirqb.lib.vehiculos.uc.AgregarClasificacionDelVehiculoUseCase
import samirqb.lib.vehiculos.uc.ListarTodasLasClasificacionesDelVehiculoUseCase

class ClasificacionDelVehiculoViewModel(
    private val mAgregarClasificacionDelVehiculoUseCase: AgregarClasificacionDelVehiculoUseCase,
    private val mListarTodasLasClasificacionesDelVehiculoUseCase: ListarTodasLasClasificacionesDelVehiculoUseCase
) : ViewModel() {

    private val NOMBRE_CLASE = "ClasificacionDelVehiculoViewModel"

    private val _uiState = MutableStateFlow(ClasificacionDelVehiculoUiState())
    val uiState: StateFlow<ClasificacionDelVehiculoUiState> = _uiState.asStateFlow()

    init {
        listarTodasLasClasificacionesDeVehiculoUserCase()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun actualizarFechaYHora() {

        var mFechaYHora = FechaYHora()
        mFechaYHora.now()

        _uiState.update {
            it.copy(
                fecha_y_hora = mFechaYHora.getDateTime(),
                /*
                fecha = mFechaYHora.getDate(),
                hora = mFechaYHora.getTime(),
                */
            )
        }
    }

    fun agregarNuevaClasificacionDeVehiculo( mEntity: ClasificacionDelVehiculoEntity ){
        viewModelScope.launch {

            val NOMBRE_FUN = "agregarNuevaClasificacionDeVehiculo"

            try {
                mAgregarClasificacionDelVehiculoUseCase(mEntity)
            }catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun listarTodasLasClasificacionesDeVehiculoUserCase(){

        val NOMBRE_FUN = "listarTodasLasClasificacionesDeVehiculoUserCase"

        viewModelScope.launch {

            try {
                mListarTodasLasClasificacionesDelVehiculoUseCase().collect{

                    var lista = it

                    _uiState.update{

                        it.copy(
                            listar_todas_las_clasificaciones_de_vehiculo = lista
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

                //val savedStateHandle = createSavedStateHandle()

                val mAgregarClasificacionDelVehiculoUseCase =
                    (this[APPLICATION_KEY] as MyApplication).mAgregarClasificacionDelVehiculoUseCase

                val mListarTodasLasClasificacionesDelVehiculoUseCase =
                    (this[APPLICATION_KEY] as MyApplication).mListarTodasLasClasificacionesDelVehiculoUseCase

                ClasificacionDelVehiculoViewModel(
                    mAgregarClasificacionDelVehiculoUseCase = mAgregarClasificacionDelVehiculoUseCase,
                    mListarTodasLasClasificacionesDelVehiculoUseCase = mListarTodasLasClasificacionesDelVehiculoUseCase,
                )
            }
        }
    }
}