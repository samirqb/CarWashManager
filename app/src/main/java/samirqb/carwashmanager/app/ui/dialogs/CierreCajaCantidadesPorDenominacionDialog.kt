package samirqb.carwashmanager.app.ui.dialogs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.custom.widgets.AgregarCantPorDenominacionWidget
import samirqb.carwashmanager.app.ui.components.custom.widgets.TotalDineroAperturaCierreCajaWidget
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tDialogScaffoldM1
import samirqb.carwashmanager.app.viewmodels.CajaViewModel
import samirqb.carwashmanager.app.viewmodels.MonedaViewModel
import samirqb.lcarwashmanager.app.ui.layoutcomponets.VLayout2P

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CierreCajaCantidadesPorDenominacionDialog(
    onNavigateToCerrarCajaConfirmacionDialog: () -> Unit,
    onDismissFromCerrarCajaCantidadesPorDenominacionDialog: () -> Unit,
    mCajaViewModel: CajaViewModel = viewModel(),
    mMonedaViewModel: MonedaViewModel = viewModel(),
) {

    val uiState_MVM by mMonedaViewModel.uiState.collectAsState()
    //val uiState_CVM by mCajaViewModel.uiState.collectAsState()
    val uiState_CCVM by mCajaViewModel.uiState_CierreCaja.collectAsState()

    mMonedaViewModel.leerTodo()
    mCajaViewModel.actualizarFechaYHora()

    val lM = uiState_MVM.todasLasMonedas

    tDialogScaffoldM1(
        modifier_content1 = Modifier
            .fillMaxWidth()
            .size(150.dp),
        modifier_content3 = Modifier
            .fillMaxWidth()
            .size(50.dp),
        header_icon_id = R.drawable.rounded_point_of_sale_24,
        header_text_titulo_id =  R.string.txt_titulo_cierre_de_caja,
        header_text_subtitulo_id = R.string.txt_label_codigo_ac_caja,
        header_text_consecutivo = uiState_CCVM.id_cierre_actual.toInt(),
        content_dialg_body = {

            sSurface(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(432.dp),
                color = MaterialTheme.colorScheme.surface
            ) {
                VLayout2P(
                    content1 = {
                        TotalDineroAperturaCierreCajaWidget(
                            txt_body_suma_total_denominaciones = uiState_CCVM.suma_total_todas_las_monedas.floatValue.toString(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(90.dp),
                            //.weight(0.9f)
                        )
                    },

                    content2 = {
                        AgregarCantPorDenominacionWidget(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(342.dp),
                            //.weight(2f)
                            es_apertura = false,
                            lista_monedas = lM,
                            //value = value,
                            //onValueChange = {value = it}
                            mCajaViewModel = mCajaViewModel,
                        )
                    },
                )
            }
        },

        boton_txt_1 = R.string.txt_label_salir,

        on_click_boton_1 = {
            mCajaViewModel.vaciarUiStatus()
            onDismissFromCerrarCajaCantidadesPorDenominacionDialog()
        },

        boton_txt_2 = R.string.txt_label_siguiente,

        on_click_boton_2 = {
            onNavigateToCerrarCajaConfirmacionDialog()
        },
        enabled_btn2 = if (lM.isEmpty()) false else true
    )
}
