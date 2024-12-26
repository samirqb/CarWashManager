package samirqb.carwashmanager.app.ui.dialogs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.custom.widgets.FechaYHoraWidget
import samirqb.carwashmanager.app.ui.components.custom.widgets.TotalDineroAperturaCierreCajaWidget
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tDialogScaffoldM1
import samirqb.carwashmanager.app.viewmodels.CajaViewModel
import samirqb.lcarwashmanager.app.ui.layoutcomponets.VLayout2P
import samirqb.lib.caja.entidades.AperturaCajaEntity

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AperturaCajaConfirmacionDialog(
    onNavigateToInicioScreen: () -> Unit,
    onDismissFromAperturaCajaConfirmacionDialog: () -> Unit,
    mCajaViewModel: CajaViewModel = viewModel(),
) {

    val uiState_CVM by mCajaViewModel.uiState.collectAsState()

    tDialogScaffoldM1(
        modifier_content1 = Modifier
            .fillMaxWidth()
            .size(150.dp),
        modifier_content3 = Modifier
            .fillMaxWidth()
            .size(50.dp),
        header_icon_id = R.drawable.rounded_point_of_sale_24,
        header_text_titulo_id = R.string.txt_titulo_apertura_de_caja,
        header_text_subtitulo_id = R.string.txt_label_numero_de_apertura,
        header_text_consecutivo = uiState_CVM.id_apertura_caja,
        content_dialg_body = {
            sSurface(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(180.dp),
                color = MaterialTheme.colorScheme.secondaryContainer
            ) {
                VLayout2P(
                    modifier = Modifier.fillMaxSize(),

                    content1 = {

                        FechaYHoraWidget(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(90.dp)
                                .padding(7.dp),
                            txt_body_fecha = uiState_CVM.fecha,
                            txt_body_hora = uiState_CVM.hora,
                        )
                    },

                    content2 = {
                        TotalDineroAperturaCierreCajaWidget(
                            txt_body_suma_total_denominaciones = uiState_CVM.suma_total_todas_las_monedas.floatValue.toString(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(90.dp),
                        )
                    },
                )
            }
        },

        boton_txt_1 = R.string.txt_label_cancelar,

        on_click_boton_1 = {
            mCajaViewModel.vaciarUiStatus()
            onDismissFromAperturaCajaConfirmacionDialog()
        },

        boton_txt_2 = R.string.txt_label_aceptar,

        on_click_boton_2 = {

            mCajaViewModel.actualizarFechaYHora()

            mCajaViewModel.apertura(

                mAperturaCajaEntity = AperturaCajaEntity(
                    id_apertura_caja_pk = 0,
                    fecha_hora_creacion = uiState_CVM.fecha_y_hora,
                    total_dinero_apertura = uiState_CVM.suma_total_todas_las_monedas.floatValue,
                    apertura_activa = true,
                )
            )
            mCajaViewModel.vaciarUiStatus()
            onNavigateToInicioScreen()
        },
    )
}
