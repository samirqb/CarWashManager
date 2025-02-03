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
import samirqb.carwashmanager.app.viewmodels.uistates.VehiculoUiState
import samirqb.lib.helpers.FechaYHora
import samirqb.lib.vehiculos.entities.VehiculoEntity
import samirqb.lib.vehiculos.uc.AgregarVehiculoUseCase
import samirqb.lib.vehiculos.uc.BuscarVehiculoPorMatriculaUseCase
import samirqb.lib.vehiculos.uc.ListarTodosLosVehiculosUseCase

class VehiculoViewModel(
    private val mAgregarVehiculoUseCase: AgregarVehiculoUseCase,
    private val mListarTodosLosVehiculosUseCase: ListarTodosLosVehiculosUseCase,
    private val mBuscarVehiculoPorMatriculaUseCase: BuscarVehiculoPorMatriculaUseCase,
): ViewModel() {

    private val NOMBRE_CLASE = "VehiculoViewModel"

    private val _uiState = MutableStateFlow(VehiculoUiState())
    val uiState: StateFlow<VehiculoUiState> = _uiState.asStateFlow()

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

    fun agregarNuevoVehiculo(mEntity: VehiculoEntity){
        viewModelScope.launch {

            val NOMBRE_FUN = "agregarNuevaVehiculo"

            try {
                mAgregarVehiculoUseCase(mEntity)
            }catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun listarTodosLosVehiculoUserCase(){

        val NOMBRE_FUN = "listarTodosLosVehiculoUserCase"

        viewModelScope.launch(Dispatchers.IO) {

            try {
                mListarTodosLosVehiculosUseCase().collect{

                    var lista = it

                    _uiState.update{

                        it.copy(
                            listar_todos_los_vehiculos = lista
                        )
                    }
                }
            } catch (e:Exception){
                Log.e("_xTAG","Exception: ${NOMBRE_CLASE}.${NOMBRE_FUN} -> ${e.stackTrace}")
            }
        }
    }

    fun buscarVehiculoPorMatriculaUseCase(matricala_vehiculo: String){

        val NOMBRE_FUN = "buscarVehiculoPorMatriculaUseCase"

        viewModelScope.launch(Dispatchers.IO) {

            try {
                mBuscarVehiculoPorMatriculaUseCase(matricala_vehiculo).collect{

                    var resultado_busqueda_vehiculo = it

                    _uiState.update{

                        it.copy(
                            resultado_busqueda_vehiculo = resultado_busqueda_vehiculo
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
                    resultado_busqueda_vehiculo = null
                )
            }
        }
    }


    /** ViewModelFactori **/
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {

                //val savedStateHandle = createSavedStateHandle()

                val mAgregarVehiculoUseCase =
                    (this[APPLICATION_KEY] as MyApplication).mAgregarVehiculoUseCase

                val mListarTodosLosVehiculosUseCase =
                    (this[APPLICATION_KEY] as MyApplication).mListarTodosLosVehiculosUseCase

                val mBuscarVehiculoPorMatriculaUseCase =
                    (this[APPLICATION_KEY] as MyApplication).mBuscarVehiculoPorMatriculaUseCase

                VehiculoViewModel(
                    mAgregarVehiculoUseCase = mAgregarVehiculoUseCase,
                    mListarTodosLosVehiculosUseCase = mListarTodosLosVehiculosUseCase,
                    mBuscarVehiculoPorMatriculaUseCase = mBuscarVehiculoPorMatriculaUseCase,
                )
            }
        }
    }
}
