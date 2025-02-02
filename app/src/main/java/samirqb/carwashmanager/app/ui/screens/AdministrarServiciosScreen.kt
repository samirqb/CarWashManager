package samirqb.carwashmanager.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sCard
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.base.layouts.sColumn
import samirqb.carwashmanager.app.ui.components.base.layouts.sLazyColumn
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout1P
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextTitle
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tScaffoldM4
import samirqb.carwashmanager.app.viewmodels.ServicioViewModel
import samirqb.carwashmanager.app.viewmodels.ServicioYPrecioViewModel

@Composable
fun AdministrarServiciosScreen(
    mServicioYPrecioViewModel: ServicioYPrecioViewModel,
    mServicioViewModel: ServicioViewModel,
    onClick_agregar_servicio_y_precio:()->Unit,
    onClick_navigate_back:()->Unit,
    onClick_crear_servicio:()->Unit,
) {

    val uiState_TLSYP by mServicioYPrecioViewModel.uiState.collectAsState()
    val uiState_TLS by mServicioViewModel.uiState.collectAsState()

    mServicioYPrecioViewModel.listarTodosLosServiciosYPreciosUC()
    mServicioViewModel.listarTodosLosServiciosUC()

    var listarTLSYP = uiState_TLSYP.todos_los_servicios_y_precios
    var listarTLS = uiState_TLS.todos_los_servicios

    tScaffoldM4(
        top_app_bar_title = R.string.txt_body_menu_servicios,
        top_app_bar_navigation_back = { onClick_navigate_back() },
        top_app_bar_navigation_back_icon = R.drawable.rounded_keyboard_backspace_24,
        top_app_bar_action_button_icon_1 = R.drawable.rounded_new_label_24,
        top_app_bar_action_button_onClick1 = { onClick_crear_servicio() },
        content1 = {

            if (listarTLSYP.isEmpty()){

                sSurface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    VLayout1P(
                        modifier = Modifier.fillMaxSize(),
                        content1 = {
                            xTextTitle(text = stringResource(id = R.string.informacion_no_disponible))
                        }
                    )
                }

            } else {

                sLazyColumn(
                    verticalArrangement = Arrangement.spacedBy(space = 13.dp, alignment = Alignment.CenterVertically)
                ) {
                    itemsIndexed( listarTLSYP ){index, item ->

                        var nombre_servicio = listarTLS.find {
                            it.id_servicio_pk == item.id_servicio_fk
                        }

                        //listarTLS.binarySearch {  }

                        sCard(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceContainer
                            ),
                            modifier = Modifier.fillMaxSize().size(90.dp)
                        ) {
                            sColumn {
                                xTextTitle(text = nombre_servicio!!.descripcion)
                                xTextBody(text = item.precio_fk.toString())
                                xTextLabel(text = item.codigo_iso_4217_fk)
                                xTextLabel(text = item.precio_activo.toString())
                                xTextLabel(text = item.fecha_hora_creacion)
                            }
                        }
                    }
                }
            }

        },
        floatingActionButton_onClick = { onClick_agregar_servicio_y_precio() },
        floatingActionButton_image_vector_id = R.drawable.rounded_add_24,
    )
}
