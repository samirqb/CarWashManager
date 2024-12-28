package samirqb.carwashmanager.app.ui.components.custom.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.layouts.sLazyColumn
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout2P
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout1P
import samirqb.carwashmanager.app.ui.components.custom.old.viewcontents.CantidadPorDenominacionViewContent
import samirqb.carwashmanager.app.ui.components.custom.textfields.xOutlinedTextField_NUM
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextDisplay
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextTitle
import samirqb.carwashmanager.app.viewmodels.CajaViewModel
import samirqb.carwashmanager.app.viewmodels.viewdtos.DetalleACCajaDto
import samirqb.lcarwashmanager.app.ui.layoutcomponets.VLayout2P
import samirqb.lib.caja.entidades.MonedaEntity
import samirqb.lib.helpers.ValidarEntradasRegex

@Composable
fun AgregarCantPorDenominacionWidget(
    lista_monedas: List<MonedaEntity>,
    es_apertura: Boolean,
    //onValueChange: (String) -> Unit,
    mCajaViewModel: CajaViewModel = viewModel(),
    modifier: Modifier = Modifier.fillMaxWidth(),
) {

    val uiState_CVM by mCajaViewModel.uiState.collectAsState()
    val uiState_ACVM by mCajaViewModel.uiState_AperturaCaja.collectAsState()

    var lista_subtotales_por_denominacion: MutableList<Float> = mutableListOf()

    var lista_detalles_ac_caja_dto: MutableList<DetalleACCajaDto> = mutableListOf()

    val mValidarEntradasRegex = ValidarEntradasRegex()

    /** valores de los ICONOS */
    val moneda_icon = R.drawable.round_monetization_on_24
    val billete_icon = R.drawable.rounded_universal_currency_alt_24
    val digital_icon = R.drawable.rounded_currency_exchange_24

    sLazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = 1.dp)
    ) {
        /*
        itemsIndexed(lista_denominaciones){ index, denominacion ->
            sSurface(
                modifier = Modifier.fillMaxWidth().size(75.dp)
            ) {
                HLayout2P(
                    content1 = {
                        sSurface(modifier = Modifier.weight(1.5f).fillMaxWidth().size(53.dp)) {
                            VLayout2P(
                                content1 = { xTextLabel(text = denominacion) },
                                content2 = { xTextBody(text = index.toString()) },
                            )
                        }
                    },

                    content2 = {
                        sSurface(
                            modifier = Modifier.weight(1f).fillMaxWidth().size(53.dp).padding(end = 9.dp),
                            //shape = RoundedCornerShape(0.dp),
                        ) {
                            var value by rememberSaveable{mutableStateOf("")}
                            sOutlinedTextField(
                                value = value,
                                onValueChange = {value = it},
                            )
                        }
                    },
                )
            }
        }
        */

        if (lista_monedas.isEmpty()) {
            item {
                sSurface(
                    modifier = Modifier.fillParentMaxSize()
                ) {
                    VLayout1P(
                        content1 = {
                            //xTextTitle(text = "No existen monedas agregadas")
                            xTextTitle(text = stringResource(id = R.string.informacion_no_disponible))
                        }
                    )
                }
            }
        } else {

            var lista_monedas_size = lista_monedas.size

            if ( (lista_monedas_size != lista_detalles_ac_caja_dto.size) or (lista_monedas_size != lista_subtotales_por_denominacion.size)){

                lista_detalles_ac_caja_dto = mutableListOf()
                lista_subtotales_por_denominacion = mutableListOf()

                for (moneda in lista_monedas.indices){

                    var mDetalleACCajaDto = DetalleACCajaDto(
                        id_ac_caja = uiState_ACVM.id_apertura_actual,
                        id_moneda = lista_monedas[moneda].id_moneda_pk,
                        cant_unidades_de_la_denominacion = 0,
                        monto_total_de_la_denominacion = 0f,
                        fecha_hora_creacion = uiState_CVM.fecha_y_hora,
                    )
                    lista_detalles_ac_caja_dto.add(index = moneda, element = mDetalleACCajaDto)

                    lista_subtotales_por_denominacion.add(index = moneda, element = 0f)
                }

            }


            itemsIndexed(lista_monedas) { index, moneda ->

                var value by rememberSaveable { mutableStateOf("") }
                //var total_por_moneda = 0f
                var total_por_moneda by rememberSaveable{ mutableFloatStateOf(0f)}

                sSurface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(75.dp)
                ) {

                    var icono_actual = 0

                    if (moneda.tipo_fk == "BILLETE") {
                        icono_actual = billete_icon
                    }

                    if (moneda.tipo_fk == "MONEDA") {
                        icono_actual = moneda_icon
                    }

                    if (moneda.tipo_fk == "DIGITAL") {
                        icono_actual = digital_icon
                    }

                    HLayout2P(

                        content1 = {

                            VLayout2P(
                                modifier = Modifier
                                    .weight(1.5f)
                                    .fillMaxWidth()
                                    .size(53.dp),
                                content1 = {
                                    sSurface(
                                        /*modifier = Modifier
                                        .weight(1.5f)
                                        .fillMaxWidth()
                                        .size(53.dp)*/
                                    ) {

                                        CantidadPorDenominacionViewContent(
                                            image_vector_id = icono_actual,
                                            denominacion_fk = moneda.denominacion_fk.toString(),
                                            codigo_iso_4217_fk = moneda.codigo_iso_4217_fk,
                                            tipo_fk = moneda.tipo_fk
                                        )
                                    }
                                },
                                content2 = {
                                    var condicion = if (total_por_moneda > 0) { true } else { false }
                                    sSurface(
                                        /*
                                        color = if(condicion) {
                                            MaterialTheme.colorScheme.inverseOnSurface
                                        } else{
                                            Color.Transparent
                                        },*/
                                    ) {
                                        xTextBody(
                                            text = "Total: $${total_por_moneda}",
                                            color = if (condicion) {
                                                MaterialTheme.colorScheme.tertiary
                                            } else {
                                                Color.Transparent
                                            }
                                        )
                                    }
                                },
                            )
                        },

                        // INPUTFIELD
                        content2 = {

                            sSurface(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                                    .size(53.dp)
                                    .padding(end = 9.dp),
                                //shape = RoundedCornerShape(0.dp),
                            ) {

                                xOutlinedTextField_NUM(

                                    value = value,

                                    onValueChange = {

                                        if (mValidarEntradasRegex.validarNumerosEnteros(it)) {

                                            value = it

                                            total_por_moneda = it.toInt() * moneda.denominacion_fk

                                            lista_detalles_ac_caja_dto[index] =
                                                DetalleACCajaDto(
                                                    id_ac_caja = uiState_CVM.id_apertura_caja,
                                                    id_moneda = moneda.id_moneda_pk,
                                                    cant_unidades_de_la_denominacion = it.toInt(),
                                                    monto_total_de_la_denominacion = total_por_moneda,
                                                    fecha_hora_creacion =  uiState_CVM.fecha_y_hora,
                                                )

                                            lista_subtotales_por_denominacion[index] = total_por_moneda

                                            mCajaViewModel.actualizarSumaToalACCaje(
                                                es_apertura = es_apertura,
                                                lista = lista_subtotales_por_denominacion,
                                            )

                                            mCajaViewModel.actualizarListaDetallesACCaja(
                                                lista_detalles_ac_caja_dto
                                            )

                                        } else {

                                            value = ""

                                            total_por_moneda = 0f

                                            lista_detalles_ac_caja_dto[index] =
                                                DetalleACCajaDto(
                                                    id_ac_caja = uiState_CVM.id_apertura_caja,
                                                    id_moneda = moneda.id_moneda_pk,
                                                    cant_unidades_de_la_denominacion = 0,
                                                    monto_total_de_la_denominacion = total_por_moneda,
                                                    fecha_hora_creacion =  uiState_CVM.fecha_y_hora,
                                                )

                                            lista_subtotales_por_denominacion[index] = total_por_moneda

                                            mCajaViewModel.actualizarSumaToalACCaje(
                                                es_apertura = es_apertura,
                                                lista = lista_subtotales_por_denominacion
                                            )
                                        }
                                    },

                                    //visualTransformation = SeparadorDeMiles()
                                )
                            }
                        },
                    )
                }
            }
        }
    }
}