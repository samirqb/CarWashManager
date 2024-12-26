package samirqb.carwashmanager.app.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableFloatStateOf
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
import samirqb.carwashmanager.app.viewmodels.uistates.AperturaCajaUiState
import samirqb.carwashmanager.app.viewmodels.uistates.CajaUiState
import samirqb.carwashmanager.app.viewmodels.viewdtos.DetalleACCajaDto
import samirqb.lib.caja.entidades.AperturaCajaEntity
import samirqb.lib.caja.repositories.AperturaCajaRepository
import samirqb.lib.helpers.FechaYHora
import samirqb.lib.helpers.SumaValoresDeItemsDeUnaLista

class CajaViewModel(
    private val mAperturaCajaRepository : AperturaCajaRepository
    //val mDetalleAperturaCajaRepository : AperturaCajaRepository
    //val mCierreCajaRepository : CierreCajaRepository
    //val mDetalleCierreCajaRepository : CierreCajaRepository

) : ViewModel() {

    private val _uiState = MutableStateFlow(CajaUiState())
    val uiState: StateFlow<CajaUiState> = _uiState.asStateFlow()

    private val _uiState_AperturaCaja = MutableStateFlow(AperturaCajaUiState())
    val uiState_AperturaCaja: StateFlow<AperturaCajaUiState> = _uiState_AperturaCaja.asStateFlow()

    private val helper = SumaValoresDeItemsDeUnaLista()

    fun actualizarIdAperturaCaja() {
        _uiState.update {
            it.copy(
                id_apertura_caja = 20241222
            )
        }
    }

    fun listarTodasLasAperturas() {

        viewModelScope.launch {
            mAperturaCajaRepository.leerTodo().collect{
                val lista = it
                _uiState_AperturaCaja.update {
                    it.copy(
                        lista_aperturas_caja = lista
                    )
                }
            }
        }
    }

    fun actualizarSumaToalACCaje(lista: List<Float>) {

        var suma = 0f

        suma = helper.sumar(lista)

        _uiState.update {
            it.copy(
                suma_total_todas_las_monedas = mutableFloatStateOf(suma)
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun actualizarFechaYHora() {

        var mFechaYHora = FechaYHora()
        mFechaYHora.now()

        _uiState.update {
            it.copy(
                fecha_y_hora = mFechaYHora.getDateTime(),
                fecha = mFechaYHora.getDate(),
                hora = mFechaYHora.getTime(),
            )
        }
    }

    fun actualizarListaDetallesACCaja(lista_detalles_ac_caja: MutableList<DetalleACCajaDto>) {

        _uiState.update {

            it.copy(
                lista_detalles_ac_caja_dtos = lista_detalles_ac_caja
            )

        }
    }

    fun vaciarUiStatus() {

        _uiState.update {

            it.copy(
                fecha_y_hora = "",
                id_apertura_caja = 0,
                suma_total_todas_las_monedas = mutableFloatStateOf(0f),
                lista_detalles_ac_caja_dtos = mutableListOf(),
            )

        }
    }

    fun apertura(mAperturaCajaEntity: AperturaCajaEntity): Boolean {

        viewModelScope.launch {
            mAperturaCajaRepository.insertar(mAperturaCajaEntity)
        }

        return true
    }

    //fun cierre():Boolean{}
    //fun ingreso():Boolean{}
    //fun egreso():Boolean{}

    /** ViewModelFactori **/
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                //val savedStateHandle = createSavedStateHandle()
                val mAperturaCajaRepository = (this[APPLICATION_KEY] as MyApplication).mAperturaCajaRepository
                //val mDetalleAperturaCajaRepository = (this[APPLICATION_KEY] as MyApplication).mDetalleAperturaCajaRepository
                //val mCierreCajaRepository = (this[APPLICATION_KEY] as MyApplication).mCierreCajaRepository
                //val mDetalleCierreCajaRepository = (this[APPLICATION_KEY] as MyApplication).mDetalleCierreCajaRepository
                CajaViewModel(
                    mAperturaCajaRepository,
                    //mDetalleAperturaCajaRepository,
                    //mCierreCajaRepository,
                    //mDetalleCierreCajaRepository,
                )
            }
        }
    }

}