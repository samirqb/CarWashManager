package samirqb.carwashmanager.app.ui.dialogs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody
import samirqb.carwashmanager.app.ui.templates.iconsandtexts.tHTextAndIcon
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tDialogScaffoldM2
import samirqb.carwashmanager.app.viewmodels.OperarioViewModel
import samirqb.carwashmanager.app.viewmodels.OrdenDeVentaViewModel
import samirqb.carwashmanager.app.viewmodels.ServicioYPrecioViewModel
import samirqb.carwashmanager.app.ui.layoutcomponets.VLayout2P
import samirqb.carwashmanager.app.viewmodels.OrdenDePagoNominaViewModel
import samirqb.lib.pagos.entities.OrdenPagoNominaEntity
import samirqb.lib.ventas.entities.DetalleOrdenServicioEntity

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AgregarServicioAOrdenDeVentaDialog(
    mOrdenDeVentaViewModel: OrdenDeVentaViewModel,
    mOrdenDePagoNominaViewModel: OrdenDePagoNominaViewModel,
    mServicioYPrecioViewModel: ServicioYPrecioViewModel,
    mOperarioViewModel: OperarioViewModel,
    onDismissFromAgregarServicioAOrdenDeVentaDialog: () -> Unit,
) {

    val uiState_ServicioYPrecioViewModel by mServicioYPrecioViewModel.uiState.collectAsState()
    val uiState_OperarioViewModel by mOperarioViewModel.uiState.collectAsState()
    val uiState_OrdenDeVentaViewModel by mOrdenDeVentaViewModel.uiState.collectAsState()
    val uiState_OrdenDePagoNominaViewModel by mOrdenDePagoNominaViewModel.uiState.collectAsState()

    mOrdenDeVentaViewModel.calcularIdDeNuevaOrdenDeVenta()
    mOrdenDeVentaViewModel.actualizarFechaYHora()
    mServicioYPrecioViewModel.listarTodosLosServiciosYPreciosUC()
    mServicioYPrecioViewModel.listarTodosLosServiciosYPreciosActivosConNombreDelServicio()
    mOrdenDePagoNominaViewModel.calcularIdProximaOrdenPagoNomina()
    mOperarioViewModel.listarTodosLosOperariosInactivosUC()

    var listar_todos_los_servicios_y_precios_activos_con_nombre_del_servicio =
        uiState_ServicioYPrecioViewModel.todos_los_servicios_y_precios_activos_y_nommbre_del_servicio


    var listar_todos_los_operarios_activos =
        uiState_OperarioViewModel.todos_los_operarios_activos.sortedBy {
            it.identificacion_pk
        }

    // servicios y precios
    var id_servicio_y_precio_seleccionado by rememberSaveable { mutableIntStateOf(0) }
    var nombre_servicio_seleccionado by rememberSaveable { mutableStateOf("") }
    var precio_servicio_seleccionado by rememberSaveable { mutableFloatStateOf(0f) }

    // operarios
    var id_operario_seleccionado by rememberSaveable { mutableStateOf("") }
    var nombre_operario_seleccionado by rememberSaveable { mutableStateOf("") }

    var servicio_y_precio_seleccionado_flag by rememberSaveable { mutableStateOf(false) }
    var operario_seleccionado_flag by rememberSaveable { mutableStateOf(false) }

    tDialogScaffoldM2(
        header_icon_id = R.drawable.rounded_details_24,
        header_text_titulo_id = R.string.txt_titulo_agregar_servicio,
        content_dialg_body = {

            sSurface(
                color = MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier
                    .fillMaxWidth()
                //.size(432.dp),
            ) {

                //VLayout3P(
                VLayout2P(
                    modifier = Modifier.padding(all = 9.dp),
                    verticalArrangement = Arrangement.spacedBy(
                        space = 19.dp,
                        alignment = Alignment.CenterVertically
                    ),
                    /** ********* SELECCION DEL SERVICIO ********* */
                    content1 = {

                        sSurface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(113.dp),
                            //.weight(1f),
                            shape = RoundedCornerShape(13),
                            color = MaterialTheme.colorScheme.onSecondary,
                        ) {

                            VLayout2P(
                                modifier = Modifier.padding(all = 11.dp),
                                verticalArrangement = Arrangement.spacedBy(7.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                content1 = {
                                    xTextBody(
                                        text = stringResource(R.string.txt_body_elija_un_servicio),
                                        color = MaterialTheme.colorScheme.error
                                    )
                                },
                                content2 = {

                                    var expander by rememberSaveable { mutableStateOf(false) }

                                    if (nombre_servicio_seleccionado.isBlank() or nombre_servicio_seleccionado.isEmpty()) {
                                        tHTextAndIcon(
                                            full_surface_modifier = Modifier
                                                .fillMaxWidth()
                                                .size(55.dp)
                                                .pointerInput(Unit) {
                                                    detectTapGestures {
                                                        expander = true
                                                    }
                                                },
                                            image_vector_fin_id = R.drawable.rounded_arrow_drop_down_24,
                                            txt_body = R.string.txt_body_elige_un_servicio_y_precio,
                                        )
                                    } else {
                                        tHTextAndIcon(
                                            full_surface_modifier = Modifier
                                                .fillMaxWidth()
                                                .size(55.dp)
                                                .pointerInput(Unit) {
                                                    detectTapGestures {
                                                        expander = true
                                                    }
                                                },
                                            //image_vector_ini_id = R.drawable.round_monetization_on_24,
                                            image_vector_fin_id = R.drawable.rounded_arrow_drop_down_24,
                                            txt_body = nombre_servicio_seleccionado,
                                        )
                                    }

                                    DropdownMenu(
                                        expanded = expander,
                                        onDismissRequest = { expander = false },
                                        modifier = Modifier
                                            .size(
                                                width = 300.dp,
                                                height = Dp.Unspecified
                                            ),
                                        offset = DpOffset(x = 0.dp, y = 1.dp)
                                    ) {

                                        listar_todos_los_servicios_y_precios_activos_con_nombre_del_servicio.forEach { (key, value) ->

                                            DropdownMenuItem(
                                                text = { xTextBody(text = "${value}\n$${key.precio_fk} ${key.codigo_iso_4217_fk}") },
                                                onClick = {

                                                    id_servicio_y_precio_seleccionado = key.id_servicio_fk

                                                    nombre_servicio_seleccionado = "${value}\n$${key.precio_fk} ${key.codigo_iso_4217_fk}"

                                                    precio_servicio_seleccionado = key.precio_fk

                                                    servicio_y_precio_seleccionado_flag = true

                                                    expander = false
                                                },
                                            )
                                        }
                                    }
                                }
                            )
                        }
                    },

                    /** ********* SELECCION DEL EMPLEADO QUE RESPONSABLE DEL SERVICIO ********* */
                    content2 = {

                        sSurface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(113.dp),
                            //.weight(1f),
                            shape = RoundedCornerShape(13),
                            color = MaterialTheme.colorScheme.onSecondary,
                        ) {

                            VLayout2P(
                                modifier = Modifier.padding(all = 11.dp),
                                verticalArrangement = Arrangement.spacedBy(7.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                content1 = {
                                    xTextBody(
                                        text = stringResource(R.string.txt_body_elija_un_operario),
                                        color = MaterialTheme.colorScheme.error
                                    )
                                },
                                content2 = {

                                    var expander by rememberSaveable { mutableStateOf(false) }

                                    if (nombre_operario_seleccionado.isBlank() or nombre_operario_seleccionado.isEmpty()) {
                                        tHTextAndIcon(
                                            full_surface_modifier = Modifier
                                                .fillMaxWidth()
                                                .size(55.dp)
                                                .pointerInput(Unit) {
                                                    detectTapGestures {
                                                        expander = true
                                                    }
                                                },
                                            image_vector_fin_id = R.drawable.rounded_arrow_drop_down_24,
                                            txt_body = R.string.txt_body_elige_un_operario,
                                        )
                                    } else {
                                        tHTextAndIcon(
                                            full_surface_modifier = Modifier
                                                .fillMaxWidth()
                                                .size(55.dp)
                                                .pointerInput(Unit) {
                                                    detectTapGestures {
                                                        expander = true
                                                    }
                                                },
                                            //image_vector_ini_id = R.drawable.round_monetization_on_24,
                                            image_vector_fin_id = R.drawable.rounded_arrow_drop_down_24,
                                            txt_body = nombre_operario_seleccionado,
                                        )
                                    }

                                    DropdownMenu(
                                        expanded = expander,
                                        onDismissRequest = { expander = false },
                                        modifier = Modifier
                                            .size(
                                                width = 300.dp,
                                                height = Dp.Unspecified
                                            ),
                                        offset = DpOffset(x = 0.dp, y = 1.dp),

                                        ) {
                                        listar_todos_los_operarios_activos.forEachIndexed { index, item ->

                                            DropdownMenuItem(
                                                text = { xTextBody(text = "${item.nombre_apellido} \nID: ${item.identificacion_pk} - TEL: ${item.telefono}") },
                                                onClick = {
                                                    operario_seleccionado_flag = true
                                                    id_operario_seleccionado = item.identificacion_pk
                                                    mOrdenDePagoNominaViewModel.listarTodasLasOrdenesDePagoNominaPorOperarioIdVigente(id_operario_seleccionado)
                                                    nombre_operario_seleccionado =
                                                        "${item.nombre_apellido} \n${item.identificacion_pk} ${item.telefono}"
                                                    expander = false
                                                },
                                            )
                                        }
                                    }
                                }
                            )
                        }
                    },
                )
            }
        },
        boton_txt_1 = R.string.txt_label_cancelar,
        on_click_boton_1 = { onDismissFromAgregarServicioAOrdenDeVentaDialog() },
        boton_txt_2 = R.string.txt_label_agregar,
        on_click_boton_2 = {

            if ( uiState_OrdenDePagoNominaViewModel.lista_ordenes_pago_nomina_por_operario_id_vigentes.isEmpty() ){
                // guardar la orden de pago nomina
                mOrdenDePagoNominaViewModel.agregarOrdenDePagoNomina(
                    OrdenPagoNominaEntity(
                        id_orden_pago_nomina_pk = 0,
                        operario_id_fk = id_operario_seleccionado,
                        orden_vigente = true,
                        fecha_hora_creacion = uiState_OrdenDeVentaViewModel.fecha_y_hora
                    )
                )
            }
            mOrdenDePagoNominaViewModel.listarTodasLasOrdenesDePagoNominaPorOperarioIdVigente(id_operario_seleccionado)

            mOrdenDeVentaViewModel.agregarServicioAOrdenDeVentaSoloEnUiState(
                DetalleOrdenServicioEntity(
                    id_registro_pk = 0 ,
                    id_orden_venta_fk = uiState_OrdenDeVentaViewModel.numero_de_nueva_orden_de_venta,
                    /*id_orden_pago_nomina_fk = if (uiState_OrdenDePagoNominaViewModel.lista_ordenes_pago_nomina_por_operario_id_vigentes.isEmpty()){
                        uiState_OrdenDePagoNominaViewModel.numero_de_proxima_orden_de_pago
                    } else{
                        uiState_OrdenDePagoNominaViewModel.lista_ordenes_pago_nomina_por_operario_id_vigentes[0].id_orden_pago_nomina_pk
                    },*/
                    id_orden_pago_nomina_fk = uiState_OrdenDePagoNominaViewModel.lista_ordenes_pago_nomina_por_operario_id_vigentes[0].id_orden_pago_nomina_pk,
                    id_precio_y_servicio_fk = id_servicio_y_precio_seleccionado,
                    id_operario_fk = id_operario_seleccionado,
                    servicio_culminado = false,
                    fecha_hora_creacion = uiState_OrdenDeVentaViewModel.fecha_y_hora,
                )
            )

            // agregar solo los precios de los servicios
            mOrdenDeVentaViewModel.agregarPresiosDeServicioAOrdenDeVentaSoloEnUiState(precio_servicio_seleccionado)

            mOrdenDeVentaViewModel.sumarTodosLosPreciosDeListaDeServiciosAgregados()

            onDismissFromAgregarServicioAOrdenDeVentaDialog()

        },
        enabled_btn1 = true,
        enabled_btn2 = servicio_y_precio_seleccionado_flag && operario_seleccionado_flag,
        // header
        modifier_content_header = Modifier
            .fillMaxWidth()
            .size(150.dp),
        // botones accion
        modifier_content_action_buttons = Modifier
            .fillMaxWidth()
            .size(50.dp),
    )
}