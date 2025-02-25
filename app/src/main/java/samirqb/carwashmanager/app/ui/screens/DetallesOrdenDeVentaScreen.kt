package samirqb.carwashmanager.app.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.base.inputs.sButton
import samirqb.carwashmanager.app.ui.components.base.inputs.sTextButton
import samirqb.carwashmanager.app.ui.components.base.layouts.sLazyColumn
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout1P
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextHeadLine
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.carwashmanager.app.ui.components.custom.widgets.DatosUsuarioWidget
import samirqb.carwashmanager.app.ui.components.custom.widgets.DatosVehiculoWidget
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tScaffoldM5
import samirqb.carwashmanager.app.viewmodels.ClasificacionDelVehiculoViewModel
import samirqb.carwashmanager.app.viewmodels.ClienteViewModel
import samirqb.carwashmanager.app.viewmodels.OperarioViewModel
import samirqb.carwashmanager.app.viewmodels.OrdenDeVentaViewModel
import samirqb.carwashmanager.app.viewmodels.ServicioYPrecioViewModel
import samirqb.carwashmanager.app.viewmodels.VehiculoViewModel
import samirqb.carwashmanager.app.ui.layoutcomponets.VLayout2P
import samirqb.carwashmanager.app.viewmodels.DetalleOrdenProductoViewModel
import samirqb.carwashmanager.app.viewmodels.DetalleOrdenServicioViewModel
import samirqb.lib.helpers.ValidarEntradasRegex
import samirqb.lib.ofertas.entities.ServicioYPrecioEntity

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetallesOrdenDeVentaScreen(
    mClienteViewModel: ClienteViewModel,
    mVehiculoViewModel: VehiculoViewModel,
    mClasificacionDelVehiculoViewModel: ClasificacionDelVehiculoViewModel,
    mOperarioViewModel: OperarioViewModel,
    mServicioYPrecioViewModel: ServicioYPrecioViewModel,
    mOrdenDeVentaViewModel: OrdenDeVentaViewModel,
    mDetalleOrdenServicioViewModel: DetalleOrdenServicioViewModel,
    mDetalleOrdenProductoViewModel: DetalleOrdenProductoViewModel,
    onNavigateToAgregarServicioDialog: (Boolean) -> Unit,
    onClick_navigate_back: () -> Unit,
) {

    val mValidarEntradasRegex = ValidarEntradasRegex()
    val color = MaterialTheme.colorScheme.surfaceContainer
    val color2 = MaterialTheme.colorScheme.secondaryContainer

    mClasificacionDelVehiculoViewModel.listarTodasLasClasificacionesDeVehiculoUserCase()
    mOperarioViewModel.listarTodosLosOperariosUC()
    mServicioYPrecioViewModel.listarTodosLosServiciosYPreciosUC()

    val uiState_ClienteViemodel by mClienteViewModel.uiState.collectAsState()
    val uiState_VehiculoViemodel by mVehiculoViewModel.uiState.collectAsState()
    val uiState_ClasificacionDelVehiculoViewModel by mClasificacionDelVehiculoViewModel.uiState.collectAsState()
    val uiState_OperarioViewModel by mOperarioViewModel.uiState.collectAsState()
    val uiState_ServicioYPrecioViewModel by mServicioYPrecioViewModel.uiState.collectAsState()
    val uiState_OrdenDeVentaViewModel by mOrdenDeVentaViewModel.uiState.collectAsState()
    val uiState_DetalleOrdenServicioViewModel by mDetalleOrdenServicioViewModel.uiState.collectAsState()
    val uiState_DetalleOrdenProductoViewModel by mDetalleOrdenProductoViewModel.uiState.collectAsState()

    val resultado_busqueda_cliente = uiState_ClienteViemodel.resultado_busqueda_cliente
    val resultado_busqueda_vehiculo = uiState_VehiculoViemodel.resultado_busqueda_vehiculo

    val cliente_id_value_buscado = uiState_ClienteViemodel.cliente_id_value_buscado
    val matricala_value_buscado = uiState_VehiculoViemodel.matricala_value_buscado

    val lista_clasificaciones_vehiculos =
        uiState_ClasificacionDelVehiculoViewModel.listar_todas_las_clasificaciones_de_vehiculo.sortedBy {
            it.clase_id_pk
        }
    val listar_todos_los_operarios = uiState_OperarioViewModel.todos_los_operarios
    val lista_categorias_vehiculo =
        uiState_ClasificacionDelVehiculoViewModel.listar_todas_las_clasificaciones_de_vehiculo

    val servicios_agregados_a_la_OrdenDeVenta =
        uiState_DetalleOrdenServicioViewModel.todos_los_servicios_agregados_a_la_orden

    val servicios_por_agregar_a_la_orden_de_venta =
        uiState_DetalleOrdenServicioViewModel.nuevos_servicios_por_agregar_a_la_orden

    val todos_los_servicios_y_precios_con_nombre =
        uiState_ServicioYPrecioViewModel.todos_los_servicios_y_precios_con_nombre


    var total_orden_venta =
        uiState_DetalleOrdenServicioViewModel.suma_precios_servicios_agregados_a_orden.floatValue +
                uiState_DetalleOrdenProductoViewModel.suma_precios_productos_agregados_a_orden.floatValue

    var orden_de_venta_id by rememberSaveable { mutableIntStateOf(0) }

    tScaffoldM5(
        verticalArrangement = Arrangement.Top,
        top_app_bar_title = R.string.txt_titulo_detalles_orden_servidios,
        top_app_bar_navigation_back = {
            onClick_navigate_back()
            mOrdenDeVentaViewModel.limpiarDatosUiState()
        },
        top_app_bar_navigation_back_icon = R.drawable.rounded_keyboard_backspace_24,
        content1 = {

            //VLayout3P(
            sLazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 3.dp),
                verticalArrangement = Arrangement.spacedBy(
                    space = 19.dp,
                    alignment = Alignment.Top
                )
            ) {

                item {

                    HLayout1P(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        content1 = {
                            xTextHeadLine(
                                text = stringResource(R.string.txt_datos_cliente),
                            )
                        }
                    )

                    DatosUsuarioWidget(
                        txt_body_nombre = resultado_busqueda_cliente!!.nombre_apellidos,
                        txt_body_identificacion = resultado_busqueda_cliente!!.identificacion_pk,
                        txt_body_telefono = resultado_busqueda_cliente!!.telefono,
                    )

                }
                item {

                    HLayout1P(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        content1 = {
                            xTextHeadLine(
                                text = stringResource(R.string.txt_datos_vehiculo),
                            )
                        }
                    )

                    val id_clasificacion = lista_clasificaciones_vehiculos.binarySearch {
                        it.clase_id_pk.compareTo(resultado_busqueda_vehiculo!!.clase_id_fk)
                    }

                    DatosVehiculoWidget(
                        txt_body_matricula = resultado_busqueda_vehiculo!!.matricula_pk,
                        txt_body_clasificacion = lista_clasificaciones_vehiculos[id_clasificacion].descripcion,
                    )

                }

                item {
                    HLayout1P(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        content1 = {
                            xTextHeadLine(
                                text = stringResource(R.string.txt_headline_lista_servicios_agregados),
                                //color = MaterialTheme.colorScheme.primary
                            )
                        }
                    )

                    HLayout1P(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        content1 = {
                            sTextButton(onClick = {
                                var desdeNuevaOrdenDeVenta = false
                                onNavigateToAgregarServicioDialog(desdeNuevaOrdenDeVenta)
                            }) {
                                xTextLabel(text = stringResource(id = R.string.txt_label_agregar_servicio))
                                sIcon(image_vector_id = R.drawable.rounded_add_24)
                            }
                        },
                    )
                }

                itemsIndexed(servicios_agregados_a_la_OrdenDeVenta) { index, item ->

                    var servicio_agregado: ServicioYPrecioEntity? = null
                    var monbre_del_servicio_agregado = ""
                    var monbre_del_operario = ""

                    todos_los_servicios_y_precios_con_nombre.forEach() {
                        if (it.key.id_registro.equals(item.id_precio_y_servicio_fk)) {
                            servicio_agregado = it.key
                            monbre_del_servicio_agregado = it.value
                        }
                    }

                    listar_todos_los_operarios.forEach() {
                        if (it.identificacion_pk.equals(item.id_operario_fk)) {
                            monbre_del_operario = it.nombre_apellido
                        }
                    }

                    sSurface(
                        modifier = Modifier
                            .fillMaxSize()
                        //    .padding(13.dp)
                        ,
                        shape = RoundedCornerShape(15),
                        color = color2
                    ) {
                        VLayout2P(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(13.dp),
                            content1 = {
                                HLayout1P(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End,
                                    content1 = {
                                        xTextLabel(
                                            text = stringResource(R.string.txt_label_servicio),
                                            //color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                )
                            },
                            content2 = {
                                var color = MaterialTheme.colorScheme.inverseSurface
                                xTextBody(text = monbre_del_servicio_agregado, color = color)
                                xTextBody(text = monbre_del_operario, color = color)
                                xTextBody(
                                    text = "$${servicio_agregado!!.precio_fk} ${servicio_agregado!!.codigo_iso_4217_fk}",
                                    color = color
                                )
                            },
                        )
                    }
                }

                item {
                    var todos_los_servicios_agregados_a_la_orden = uiState_DetalleOrdenServicioViewModel.todos_los_servicios_agregados_a_la_orden.size
                    xTextLabel(
                        text = "Servicios existente: ${todos_los_servicios_agregados_a_la_orden}",
                        //color = MaterialTheme.colorScheme.primary
                    )
                }
                item {
                    var cantidad_servicios_nuevos = uiState_DetalleOrdenServicioViewModel.nuevos_servicios_por_agregar_a_la_orden.size
                    xTextLabel(
                        text = "Servicios nuevos: ${cantidad_servicios_nuevos}",
                        //color = MaterialTheme.colorScheme.primary
                    )
                }

                itemsIndexed(servicios_por_agregar_a_la_orden_de_venta) { index, item ->

                    var servicio_agregado: ServicioYPrecioEntity? = null
                    var monbre_del_servicio_agregado = ""
                    var monbre_del_operario = ""

                    todos_los_servicios_y_precios_con_nombre.forEach() {
                        if (it.key.id_registro.equals(item.id_precio_y_servicio_fk)) {
                            servicio_agregado = it.key
                            monbre_del_servicio_agregado = it.value
                        }
                    }

                    listar_todos_los_operarios.forEach() {
                        if (it.identificacion_pk.equals(item.id_operario_fk)) {
                            monbre_del_operario = it.nombre_apellido
                        }
                    }

                    sSurface(
                        modifier = Modifier
                            .fillMaxSize()
                        //    .padding(13.dp)
                        ,
                        shape = RoundedCornerShape(15),
                        color = color2
                    ) {
                        VLayout2P(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(13.dp),
                            content1 = {
                                HLayout1P(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End,
                                    content1 = {
                                        xTextLabel(
                                            text = stringResource(R.string.txt_label_servicio),
                                            //color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                )
                            },
                            content2 = {
                                var color = MaterialTheme.colorScheme.inverseSurface
                                xTextBody(text = monbre_del_servicio_agregado, color = color)
                                xTextBody(text = monbre_del_operario, color = color)
                                xTextBody(
                                    text = "$${servicio_agregado!!.precio_fk} ${servicio_agregado!!.codigo_iso_4217_fk}",
                                    color = color
                                )
                            },
                        )
                    }
                }


                item {
                    var enabled_boton_crear_nueva_orden = false

                    if (uiState_DetalleOrdenServicioViewModel.nuevos_servicios_por_agregar_a_la_orden.isNotEmpty()) {
                        enabled_boton_crear_nueva_orden = true
                    }

                    sSurface {
                        HLayout1P(
                            content1 = {
                                sButton(
                                    enabled = enabled_boton_crear_nueva_orden,
                                    onClick = {

                                        mOrdenDeVentaViewModel.actualizarFechaYHora()

                                        // guardar los servicios y/o productos
                                        mDetalleOrdenServicioViewModel.insertarListaDeDetallesAOrdenDeServiciosUC(
                                            //uiState_OrdenDeVentaViewModel.todos_los_servicios_agregados_a_la_orden
                                            uiState_DetalleOrdenServicioViewModel.nuevos_servicios_por_agregar_a_la_orden
                                        )

                                        // eliminar valores de uiState
                                        mOrdenDeVentaViewModel.limpiarDatosUiState()

                                        onClick_navigate_back()

                                    },
                                    content = {
                                        sIcon(image_vector_id = R.drawable.rounded_save_24)
                                        xTextLabel(text = stringResource(id = R.string.txt_label_guardar))
                                    },
                                )
                            },
                            //content2 = {},
                        )
                    }
                }
            }
        },
    )
}