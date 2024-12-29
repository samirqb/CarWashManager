package samirqb.carwashmanager.app.viewmodels

import android.os.Build
import android.util.Log
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
    private val mDetalleCierreCajaRepository: DetalleCierreCajaRepository,
    private val mCierreCajaRepository: CierreCajaRepository,

    ) : ViewModel() {


    private val helper = SumaValoresDeItemsDeUnaLista()

    private val _uiState = MutableStateFlow(CajaUiState())
    val uiState: StateFlow<CajaUiState> = _uiState.asStateFlow()

    private val _uiState_AperturaCaja = MutableStateFlow(AperturaCajaUiState())
    val uiState_AperturaCaja: StateFlow<AperturaCajaUiState> = _uiState_AperturaCaja.asStateFlow()

    private val _uiState_CierreCaja = MutableStateFlow(CierreCajaUiState())
    val uiState_CierreCaja: StateFlow<CierreCajaUiState> = _uiState_CierreCaja.asStateFlow()

    init {
        obtenerUltimaAperturaCaja()
        obtenerUltimoCierreCaja()
    }

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

    fun obtenerUltimoCierreCaja() {
        viewModelScope.launch {

            try {
                mCierreCajaRepository.leerCierreCajaMasReciente().collect {

                    var ultimoCierre: CierreCajaEntity? = null

                    if (it == null) {
                        ultimoCierre = CierreCajaEntity(
                            id_cierre_caja_pk = 0,
                            id_apertura_caja_fk = 0,
                            total_dinero_cierre = 0f,
                            fecha_hora_creacion = "",
                        )
                    } else {
                        ultimoCierre = it
                    }

                    _uiState_CierreCaja.update {
                        it.copy(
                            ultimoCierreCaja = ultimoCierre
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("_TAG_ERROR", e.toString())
            }
        }
    }

    fun getIdAperturaActual() {

        if (uiState_AperturaCaja.value.ultimaAperturaCaja!!.apertura_activa) {

            _uiState_AperturaCaja.update {
                it.copy(
                    id_apertura_actual = uiState_AperturaCaja.value.ultimaAperturaCaja!!.id_apertura_caja_pk
                )
            }

            _uiState_CierreCaja.update {
                it.copy(
                    id_cierre_actual = uiState_CierreCaja.value.ultimoCierreCaja!!.id_cierre_caja_pk
                )
            }
        } else {

            _uiState_AperturaCaja.update {
                it.copy(
                    id_apertura_actual = uiState_AperturaCaja.value.ultimaAperturaCaja!!.id_apertura_caja_pk + 1
                )
            }

            _uiState_CierreCaja.update {
                it.copy(
                    id_cierre_actual = uiState_CierreCaja.value.ultimoCierreCaja!!.id_cierre_caja_pk + 1
                )
            }
        }
    }

    fun getIdCierreActual() {

        _uiState_CierreCaja.update {
            it.copy(
                id_cierre_actual = uiState_CierreCaja.value.ultimoCierreCaja!!.id_cierre_caja_pk + 1
            )
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

    fun actualizarSumaTotalACCaje(es_apertura: Boolean, lista: List<Float>) {

        var suma = 0f

        suma = helper.sumar(lista)

        if (es_apertura) {
            _uiState_AperturaCaja.update {
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

    fun actualizarListaDetallesACCaja(es_apertura: Boolean, lista_detalles_ac_caja: MutableList<DetalleACCajaDto>) {

        if (es_apertura) {
            _uiState_AperturaCaja.update {

                it.copy(
                    lista_detalles_ac_caja_dtos = lista_detalles_ac_caja
                )

            }
        } else {
            _uiState_CierreCaja.update {

                it.copy(
                    lista_detalles_ac_caja_dtos = lista_detalles_ac_caja
                )

            }
        }
    }

    fun vaciarUiStatus() {

        _uiState.update {

            it.copy(
                fecha_y_hora = "",
                id_apertura_caja = 0,
            )

        }

        _uiState_AperturaCaja.update {

            it.copy(
                suma_total_todas_las_monedas = mutableFloatStateOf(0f),
                lista_detalles_ac_caja_dtos = mutableListOf(),
            )
        }

        _uiState_CierreCaja.update {

            it.copy(
                suma_total_todas_las_monedas = mutableFloatStateOf(0f),
                lista_detalles_ac_caja_dtos = mutableListOf(),
            )
        }
    }

    fun apertura(
        mAperturaCajaEntity: AperturaCajaEntity,
        mDetalleAperturaCajaEntity: Array<DetalleAperturaCajaEntity>
    ): Boolean {
        try {
            viewModelScope.launch {
                mAperturaCajaRepository.insertar(mAperturaCajaEntity)
                mDetalleAperturaCajaRepository.insertarVarios(mDetalleAperturaCajaEntity)
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun cierre(
        mCierreCajaEntity: CierreCajaEntity,
        mDetalleCierreCajaEntity: Array<DetalleCierreCajaEntity>
    ): Boolean {
        try {

            _uiState_AperturaCaja.update {
                it.copy(
                    ultimaAperturaCaja = AperturaCajaEntity(
                        id_apertura_caja_pk = uiState_AperturaCaja.value.ultimaAperturaCaja!!.id_apertura_caja_pk,
                        total_dinero_apertura = uiState_AperturaCaja.value.ultimaAperturaCaja!!.total_dinero_apertura,
                        fecha_hora_creacion = uiState_AperturaCaja.value.ultimaAperturaCaja!!.fecha_hora_creacion,
                        apertura_activa = false
                    )
                )
            }

            viewModelScope.launch {
                mCierreCajaRepository.insertar(mCierreCajaEntity)
                mDetalleCierreCajaRepository.insertarVarios(mDetalleCierreCajaEntity)
                mAperturaCajaRepository.actualizar(uiState_AperturaCaja.value.ultimaAperturaCaja!!)
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
                    mAperturaCajaRepository = mAperturaCajaRepository,
                    mDetalleAperturaCajaRepository = mDetalleAperturaCajaRepository,
                    mCierreCajaRepository = mCierreCajaRepository,
                    mDetalleCierreCajaRepository = mDetalleCierreCajaRepository,
                )
            }
        }
    }

}