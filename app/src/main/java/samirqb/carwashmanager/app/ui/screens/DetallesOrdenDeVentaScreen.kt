package samirqb.carwashmanager.app.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.base.inputs.sButton
import samirqb.carwashmanager.app.ui.components.base.inputs.sTextButton
import samirqb.carwashmanager.app.ui.components.base.layouts.sLazyColumn
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout1P
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout2P
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout3P
import samirqb.carwashmanager.app.ui.components.custom.textfields.xOutlinedTextField_CHAR
import samirqb.carwashmanager.app.ui.components.custom.textfields.xOutlinedTextField_TEL
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextHeadLine
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.carwashmanager.app.ui.components.custom.widgets.DatosUsuarioWidget
import samirqb.carwashmanager.app.ui.components.custom.widgets.DatosVehiculoWidget
import samirqb.carwashmanager.app.ui.templates.iconsandtexts.tHTextAndIcon
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tScaffoldM5
import samirqb.carwashmanager.app.viewmodels.ClasificacionDelVehiculoViewModel
import samirqb.carwashmanager.app.viewmodels.ClienteViewModel
import samirqb.carwashmanager.app.viewmodels.OperarioViewModel
import samirqb.carwashmanager.app.viewmodels.OrdenDeVentaViewModel
import samirqb.carwashmanager.app.viewmodels.ServicioYPrecioViewModel
import samirqb.carwashmanager.app.viewmodels.VehiculoViewModel
import samirqb.carwashmanager.app.ui.layoutcomponets.VLayout2P
import samirqb.carwashmanager.app.viewmodels.DetalleOrdenServicioViewModel
import samirqb.carwashmanager.app.viewmodels.OrdenDePagoNominaViewModel
import samirqb.lib.helpers.ValidarEntradasRegex
import samirqb.lib.ofertas.entities.ServicioYPrecioEntity
import samirqb.lib.pagos.entities.OrdenPagoNominaEntity
import samirqb.lib.personas.entities.ClienteEntity
import samirqb.lib.vehiculos.entities.VehiculoEntity
import samirqb.lib.ventas.entities.OrdenDeVentaEntity

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
    onNavigateToAgregarServicioDialog: () -> Unit,
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
        uiState_OrdenDeVentaViewModel.todos_los_servicios_agregados_a_la_orden

    val todos_los_servicios_y_precios_con_nombre =
        uiState_ServicioYPrecioViewModel.todos_los_servicios_y_precios_con_nombre


    var total_orden_venta =
        uiState_OrdenDeVentaViewModel.suma_precios_servicios_agregados_a_orden.floatValue +
                uiState_OrdenDeVentaViewModel.suma_precios_productos_agregados_a_orden.floatValue

    var orden_de_venta_id by rememberSaveable { mutableIntStateOf(0) }
    // formulario cliente si cliente no existe en db
    var identificacion_value by rememberSaveable { mutableStateOf(cliente_id_value_buscado) }
    var nombre_y_apellidos_value by rememberSaveable { mutableStateOf("") }
    var telefono_value by rememberSaveable { mutableStateOf("") }

    // formulario vahiculo si vehiculo no exista en db
    var matricula_vehiculo_value by rememberSaveable { mutableStateOf(matricala_value_buscado) }
    var categoria_seleccionada by rememberSaveable { mutableIntStateOf(0) }

    tScaffoldM5(
        verticalArrangement = Arrangement.Top,
        //horizontalAlignment,
        top_app_bar_title = R.string.txt_titulo_detalles_orden_servidios,
        top_app_bar_navigation_back = { onClick_navigate_back() },
        top_app_bar_navigation_back_icon = R.drawable.rounded_keyboard_backspace_24,
        content1 = {

            //VLayout3P(
            sLazyColumn(
                //modifier = Modifier.fillMaxSize(),
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
                                //color = MaterialTheme.colorScheme.primary
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
                                //color = MaterialTheme.colorScheme.primary
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
                            sTextButton(onClick = onNavigateToAgregarServicioDialog) {
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
                    var enabled_boton_crear_nueva_orden = false

                    if (resultado_busqueda_cliente != null && resultado_busqueda_vehiculo != null
                        && uiState_OrdenDeVentaViewModel.todos_los_servicios_agregados_a_la_orden.isNotEmpty()
                    ) {
                        enabled_boton_crear_nueva_orden = true
                    } else if (identificacion_value.length >= 2
                        && nombre_y_apellidos_value.length >= 2 && telefono_value.length >= 2
                        && matricula_vehiculo_value.length >= 2 && categoria_seleccionada > 0
                    ) {
                        enabled_boton_crear_nueva_orden = true
                    }

                    sSurface {
                        HLayout1P(
                            content1 = {
                                sButton(
                                    enabled = enabled_boton_crear_nueva_orden,
                                    onClick = {

                                        mOrdenDeVentaViewModel.actualizarFechaYHora()
                                        //Guardar usuario si no existe
                                        if (resultado_busqueda_cliente == null) {
                                            mClienteViewModel.agregarClienteUC(
                                                ClienteEntity(
                                                    identificacion_pk = identificacion_value,
                                                    nombre_apellidos = nombre_y_apellidos_value,
                                                    telefono = telefono_value,
                                                    fecha_hora_creacion = uiState_OrdenDeVentaViewModel.fecha_y_hora,
                                                )
                                            )
                                        }

                                        //Guardar vehiculo si no existe
                                        if (resultado_busqueda_vehiculo == null) {
                                            mVehiculoViewModel.agregarNuevoVehiculo(
                                                VehiculoEntity(
                                                    matricula_pk = matricula_vehiculo_value,
                                                    clase_id_fk = categoria_seleccionada,
                                                    fecha_hora_creacion = uiState_OrdenDeVentaViewModel.fecha_y_hora,
                                                )
                                            )
                                        }

                                        mOrdenDeVentaViewModel.crearNuevaOrdenDeVentaUC(
                                            OrdenDeVentaEntity(
                                                id_orden_pk = 0,
                                                cliente_identificacion_fk = (resultado_busqueda_cliente?.identificacion_pk
                                                    ?: identificacion_value),
                                                matricula_vehiculo_fk = (resultado_busqueda_vehiculo?.matricula_pk
                                                    ?: matricula_vehiculo_value),
                                                valor_total_orden = total_orden_venta,
                                                valor_total_solo_servicios = uiState_OrdenDeVentaViewModel.suma_precios_servicios_agregados_a_orden.floatValue,
                                                valor_total_solo_productos = uiState_OrdenDeVentaViewModel.suma_precios_productos_agregados_a_orden.floatValue,
                                                orden_vigente = false,
                                                fecha_hora_creacion = uiState_OrdenDeVentaViewModel.fecha_y_hora,
                                            )
                                        )

                                        // guardar los servicios y/o productos
                                        mDetalleOrdenServicioViewModel.insertarListaDeDetallesAOrdenDeServiciosUC(
                                            uiState_OrdenDeVentaViewModel.todos_los_servicios_agregados_a_la_orden
                                        )

                                        // eliminar valores de uiState
                                        mOrdenDeVentaViewModel.limpiarDatosUiState()

                                        onClick_navigate_back()

                                    },
                                    content = {
//                                        sIcon(image_vector_id = R.drawable.rounded_save_24)
//                                        xTextLabel(text = stringResource(id = R.string.txt_label_guardar))
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