package samirqb.carwashmanager.app.viewmodels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import samirqb.carwashmanager.app.MyApplication
import samirqb.carwashmanager.app.viewmodels.uistates.MonedaUiState
import samirqb.lib.caja.entidades.MonedaEntity
import samirqb.lib.caja.repositories.MonedaRepository
import samirqb.lib.helpers.FechaYHora

class MonedaViewModel(
    val repositorio: MonedaRepository
) : ViewModel() {

    val _uiState = MutableStateFlow(MonedaUiState())
    val uiState: StateFlow<MonedaUiState> = _uiState.asStateFlow()

    fun actualizarMonedaEntity(mMonedaEntity: MonedaEntity) {
        _uiState.update {
            it.copy(
                mMonedaEntity = mMonedaEntity
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun actualizarFechaYHora() {

        var mFechaYHora = FechaYHora()
        mFechaYHora.now()

        _uiState.update {
            it.copy(
                fecha_hora_creacion = mFechaYHora.toString(),
            )
        }
    }

    fun leerTodo() {
        viewModelScope.launch {

            try {
                // se lee el repositorio
                repositorio.leerTodo()
                    /*
                    .filter { it.size < 1 }
                    .map {
                        listOf(
                            MonedaEntity(
                                id_moneda_pk = 0,
                                codigo_iso_4217_fk = "HELL",
                                denominacion_fk = 666f,
                                tipo_fk = "BILLETE",
                                fecha_hora_creacion = "SIN FECHA"
                            )
                        )
                    }
                    */
                    .collect { it ->
                        val todasLasMonedas = it
                        // actualizamos el estado que lee la UI
                        _uiState.update {
                            it.copy(todasLasMonedas = todasLasMonedas)
                        }
                    }

            } catch (e:Exception){
                Log.e("_TAG","Error: ${e.message}")
            }
        }
    }

    fun agregarMoneda(mTEntity: MonedaEntity) {
        try {
            viewModelScope.launch {
                repositorio.insertar(mTEntity = mTEntity)
            }
        } catch (e:Exception){
            Log.e("_TAG","Error: ${e.message}")
        }

    }

    /** ViewModelFactori **/
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val myRepository = (this[APPLICATION_KEY] as MyApplication).mMonedaRepository
                MonedaViewModel(
                    repositorio = myRepository,
                )
            }
        }
    }
}