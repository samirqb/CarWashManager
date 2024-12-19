package samirqb.carwashmanager.app.ui.screens

import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.layouts.sLazyColumn
import samirqb.carwashmanager.app.ui.components.custom.old.viewcontents.CantidadPorDenominacionViewContent
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tScaffoldM3
import samirqb.carwashmanager.app.viewmodels.MonedaViewModel

@Composable
fun AdministrarMonedaScreen(
    onClick_agregar_moneda:()->Unit,
    onClick_navigate_back:()->Unit,
    mMVM: MonedaViewModel = viewModel(),
) {
    val moneda_icon = R.drawable.round_monetization_on_24
    val billete_icon = R.drawable.rounded_universal_currency_alt_24
    val digital_icon = R.drawable.rounded_currency_exchange_24

    val uiState_MVM by mMVM.uiState.collectAsState()

    mMVM.leerTodo()

    val lM = uiState_MVM.todasLasMonedas

    tScaffoldM3(
        top_app_bar_title = R.string.txt_body_menu_monedas,
        top_app_bar_navigation_back = {
            onClick_navigate_back()
        },
        content1 = {
            sLazyColumn {

                itemsIndexed(lM){index, item ->

                    var icono_actual:Int = 0

                    if (item.tipo_fk == "BILLETE"){ icono_actual = billete_icon }
                    if (item.tipo_fk == "MONEDA") { icono_actual = moneda_icon }
                    if (item.tipo_fk == "DIGITAL") { icono_actual = digital_icon }

                    CantidadPorDenominacionViewContent(
                        image_vector_id = icono_actual,
                        denominacion_fk = item.denominacion_fk.toString(),
                        codigo_iso_4217_fk = item.codigo_iso_4217_fk,
                        tipo_fk = item.tipo_fk
                    )
                }
            }
        },
        top_app_bar_navigation_back_icon = R.drawable.rounded_keyboard_backspace_24,
        floatingActionButton_onClick = {
            onClick_agregar_moneda()
        },
        floatingActionButton_image_vector_id = R.drawable.rounded_add_24,
    )
}
