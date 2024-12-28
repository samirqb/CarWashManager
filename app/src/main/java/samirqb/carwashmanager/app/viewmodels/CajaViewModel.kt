package samirqb.carwashmanager.app.viewmodels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
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
import samirqb.carwashmanager.app.viewmodels.uistates.CierreCajaUiState
import samirqb.carwashmanager.app.viewmodels.viewdtos.DetalleACCajaDto
import samirqb.lib.caja.entidades.AperturaCajaEntity
import samirqb.lib.caja.entidades.CierreCajaEntity
import samirqb.lib.caja.entidades.DetalleAperturaCajaEntity
import samirqb.lib.caja.entidades.DetalleCierreCajaEntity
import samirqb.lib.caja.repositories.AperturaCajaRepository
import samirqb.lib.caja.repositories.CierreCajaRepository
import samirqb.lib.caja.repositories.DetalleAperturaCajaRepository
import samirqb.lib.caja.repositories.DetalleCierreCajaRepository
import samirqb.lib.helpers.FechaYHora
import samirqb.lib.helpers.SumaValoresDeItemsDeUnaLista

class CajaViewModel(
    private val mAperturaCajaRepository: AperturaCajaRepository,
    private val mDetalleAperturaCajaRepository: DetalleAperturaCajaRepository,
    val mCierreCajaRepository : CierreCajaRepository,
    val mDetalleCierreCajaRepository : DetalleCierreCajaRepository

) : ViewModel() {

    private val _uiState = MutableStateFlow(CajaUiState())
    val uiState: StateFlow<CajaUiState> = _uiState.asStateFlow()

    private val _uiState_AperturaCaja = MutableStateFlow(AperturaCajaUiState())
    val uiState_AperturaCaja: StateFlow<AperturaCajaUiState> = _uiState_AperturaCaja.asStateFlow()

    private val _uiState_CierreCaja = MutableStateFlow(CierreCajaUiState())
    val uiState_CierreCaja: StateFlow<CierreCajaUiState> = _uiState_CierreCaja.asStateFlow()

    private val helper = SumaValoresDeItemsDeUnaLista()

    fun obtenerUltimaAperturaCaja() {
        viewModelScope.launch {

            try {
                mAperturaCajaRepository.leerAperturaCajaMasReciente().collect {

                    var ultimaApertura: AperturaCajaEntity? = null

                    if (it == null) {
                        ultimaApertura = AperturaCajaEntity(
                            id_apertura_caja_pk = 0,
                            total_dinero_apertura = 0f,
                            fecha_hora_creacion = "",
                            apertura_activa = false
                        )
                    } else {
                        ultimaApertura = it
                    }

                    _uiState_AperturaCaja.update {
                        it.copy(
                            ultimaAperturaCaja = ultimaApertura
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("_TAG_ERROR", e.toString())
            }
        }
    }

    fun getIdAperturaActual(){

        var id_actual = uiState_AperturaCaja.value.ultimaAperturaCaja?.id_apertura_caja_pk

        if (id_actual.toString().isNullOrEmpty() or id_actual.toString().isNullOrBlank()){
            _uiState_AperturaCaja.update {
                it.copy(
                    id_apertura_actual = 1,
                )
            }
            _uiState_CierreCaja.update {
                it.copy(
                    id_cierre_actual = 1,
                )
            }
        } else {

            id_actual = id_actual?.plus(1)

            _uiState_AperturaCaja.update {
                it.copy(
                    id_apertura_actual = id_actual ?: 1,
                )
            }

            _uiState_CierreCaja.update {
                it.copy(
                    id_cierre_actual = id_actual ?: 1,
                )
            }
        }

    }

    fun listarTodasLasAperturas() {

        viewModelScope.launch {
            mAperturaCajaRepository.leerTodo().collect {
                val lista = it
                _uiState_AperturaCaja.update {
                    it.copy(
                        lista_aperturas_caja = lista
                    )
                }
            }
        }
    }

    fun actualizarSumaToalACCaje(es_apertura: Boolean, lista: List<Float>) {

        var suma = 0f

        suma = helper.sumar(lista)

        if(es_apertura){
            _uiState.update {
                it.copy(
                    suma_total_todas_las_monedas = mutableFloatStateOf(suma)
                )
            }
        } else {
            _uiState_CierreCaja.update {
                it.copy(
                    suma_total_todas_las_monedas = mutableFloatStateOf(suma)
                )
            }
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

    fun apertura(
        mAperturaCajaEntity: AperturaCajaEntity,
        mDetalleAperturaCajaEntity: Array<DetalleAperturaCajaEntity>
    ): Boolean {


        viewModelScope.launch {
            mAperturaCajaRepository.insertar(mAperturaCajaEntity)
            mDetalleAperturaCajaRepository.insertarVarios(mDetalleAperturaCajaEntity)
        }

        return true
    }

    fun cierre(
        mCierreCajaEntity: CierreCajaEntity,
        mDetalleCierreCajaEntity: Array<DetalleCierreCajaEntity>
    ): Boolean {
        try {
            viewModelScope.launch {
                mCierreCajaRepository.insertar(mCierreCajaEntity)
                mDetalleCierreCajaRepository.insertarVarios(mDetalleCierreCajaEntity)
            }
            return true
        } catch (ex: Exception) {
            return false
        }
    }

    //fun ingreso():Boolean{}
    //fun egreso():Boolean{}

    /** ViewModelFactori **/
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                //val savedStateHandle = createSavedStateHandle()
                val mAperturaCajaRepository =
                    (this[APPLICATION_KEY] as MyApplication).mAperturaCajaRepository
                val mDetalleAperturaCajaRepository =
                    (this[APPLICATION_KEY] as MyApplication).mDetalleAperturaCajaRepository
                val mCierreCajaRepository =
                    (this[APPLICATION_KEY] as MyApplication).mCierreCajaRepository
                val mDetalleCierreCajaRepository =
                    (this[APPLICATION_KEY] as MyApplication).mDetalleCierreCajaRepository
                CajaViewModel(
                    mAperturaCajaRepository,
                    mDetalleAperturaCajaRepository,
                    mCierreCajaRepository,
                    mDetalleCierreCajaRepository,
                )
            }
        }
    }

}