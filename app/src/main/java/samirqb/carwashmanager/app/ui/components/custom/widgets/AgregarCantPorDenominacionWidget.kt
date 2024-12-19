package samirqb.carwashmanager.app.ui.components.custom.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.layouts.sLazyColumn
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout2P
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout1P
import samirqb.carwashmanager.app.ui.components.custom.old.viewcontents.CantidadPorDenominacionViewContent
import samirqb.carwashmanager.app.ui.components.custom.textfields.config.SeparadorDeMiles
import samirqb.carwashmanager.app.ui.components.custom.textfields.xOutlinedTextField_NUM
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextHeadLine
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextTitle
import samirqb.lib.caja.entidades.MonedaEntity
import samirqb.lib.helpers.ValidarEntradasRegex

@Composable
fun AgregarCantPorDenominacionWidget(
    lista_monedas: List<MonedaEntity>,
    //onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
) {

    val mValidarEntradasRegex = ValidarEntradasRegex()

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
                            xTextTitle(text = "No existen monedas agregadas")
                        }
                    )
                }
            }
        } else {
            itemsIndexed(lista_monedas) { index, moneda ->

                var icono_actual: Int = 0

                if (moneda.tipo_fk == "BILLETE") {
                    icono_actual = billete_icon
                }
                if (moneda.tipo_fk == "MONEDA") {
                    icono_actual = moneda_icon
                }
                if (moneda.tipo_fk == "DIGITAL") {
                    icono_actual = digital_icon
                }

                sSurface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(75.dp)
                ) {
                    HLayout2P(

                        content1 = {
                            sSurface(modifier = Modifier
                                .weight(1.5f)
                                .fillMaxWidth()
                                .size(53.dp)) {

                                CantidadPorDenominacionViewContent(
                                    image_vector_id = icono_actual,
                                    denominacion_fk = moneda.denominacion_fk.toString(),
                                    codigo_iso_4217_fk = moneda.codigo_iso_4217_fk,
                                    tipo_fk = moneda.tipo_fk
                                )
                            }
                        },

                        content2 = {
                            sSurface(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                                    .size(53.dp)
                                    .padding(end = 9.dp),
                                //shape = RoundedCornerShape(0.dp),
                            ) {
                                var value by rememberSaveable { mutableStateOf("") }
                                xOutlinedTextField_NUM(
                                    //sOutlinedTextField(
                                    value = value,
                                    onValueChange = {
                                        //value = it
                                        if (mValidarEntradasRegex.validarNumerosEnteros(it)) {
                                            value = it
                                            //denominacion_value = it
                                        } else {
                                            value = ""
                                            //denominacion_value = ""
                                        }
                                    },
                                    visualTransformation = SeparadorDeMiles()
                                )
                            }
                        },
                    )
                }
            }
        }

    }
}