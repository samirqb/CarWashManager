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
import samirqb.carwashmanager.app.viewmodels.DetalleOrdenProductoViewModel
import samirqb.carwashmanager.app.viewmodels.DetalleOrdenServicioViewModel
import samirqb.carwashmanager.app.viewmodels.OrdenDePagoNominaViewModel
import samirqb.lib.helpers.ValidarEntradasRegex
import samirqb.lib.ofertas.entities.ServicioYPrecioEntity
import samirqb.lib.personas.entities.ClienteEntity
import samirqb.lib.vehiculos.entities.VehiculoEntity
import samirqb.lib.ventas.entities.OrdenDeVentaEntity

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NuevaOrdenDeVentaScreen(
    mClienteViewModel: ClienteViewModel,
    mVehiculoViewModel: VehiculoViewModel,
    mClasificacionDelVehiculoViewModel: ClasificacionDelVehiculoViewModel,
    mOperarioViewModel: OperarioViewModel,
    mServicioYPrecioViewModel: ServicioYPrecioViewModel,
    mOrdenDeVentaViewModel: OrdenDeVentaViewModel,
    mOrdenDePagoNominaViewModel: OrdenDePagoNominaViewModel,
    mDetalleOrdenServicioViewModel: DetalleOrdenServicioViewModel,
    mDetalleOrdenProductoViewModel: DetalleOrdenProductoViewModel,
    onNavigateToAgregarServicioDialog: (Boolean) -> Unit,
    onClick_navigate_back: () -> Unit,
) {

    val mValidarEntradasRegex = ValidarEntradasRegex()
    val color = MaterialTheme.colorScheme.surfaceContainer
    val color2 = MaterialTheme.colorScheme.secondaryContainer

    // listar registro necesarios
    mServicioYPrecioViewModel.listarTodosLosServiciosYPreciosUC()
    mServicioYPrecioViewModel.listarTodosLosServiciosYPreciosActivosConNombreDelServicio()
    mOrdenDePagoNominaViewModel.calcularIdProximaOrdenPagoNomina()
    mOrdenDeVentaViewModel.calcularIdDeNuevaOrdenDeVenta()
    mOperarioViewModel.listarTodosLosOperariosActivosUC()
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

    /*val servicios_agregados_a_la_OrdenDeVenta =
        uiState_OrdenDeVentaViewModel.nuevos_servicios_por_agregar_a_la_orden*/

    val servicios_por_agregar_a_la_orden_de_venta =
        uiState_DetalleOrdenServicioViewModel.nuevos_servicios_por_agregar_a_la_orden

    val todos_los_servicios_y_precios_con_nombre =
        uiState_ServicioYPrecioViewModel.todos_los_servicios_y_precios_con_nombre

    var total_orden_venta =
        uiState_DetalleOrdenServicioViewModel.suma_precios_servicios_por_agregar_a_orden.floatValue +
                uiState_DetalleOrdenProductoViewModel.suma_precios_productos_por_agregar_a_orden .floatValue

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
        top_app_bar_title = R.string.txt_titulo_nueva_orden_servidios,
        top_app_bar_navigation_back = { onClick_navigate_back() },
        top_app_bar_navigation_back_icon = R.drawable.rounded_keyboard_backspace_24,
        content1 = {

            val scroll_state = rememberScrollState()

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
                                //color = MaterialTheme.colorScheme.primary
                            )
                        }
                    )

                    if (resultado_busqueda_cliente == null) {

                        sSurface(
                            modifier = Modifier
                                .fillMaxWidth(),
                            //.size(339.dp)
                            //.weight(1f),
                            shape = RoundedCornerShape(5),
                            color = color,
                        ) {

                            VLayout2P(
                                modifier = Modifier.padding(all = 11.dp),
                                verticalArrangement = Arrangement.spacedBy(7.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                content1 = {
                                    xTextBody(
                                        text = stringResource(R.string.txt_body_agregar_datos_cliente),
                                        color = MaterialTheme.colorScheme.error
                                    )
                                },
                                content2 = {

                                    VLayout3P(
                                        modifier = Modifier.padding(all = 13.dp),
                                        verticalArrangement = Arrangement.spacedBy(space = 19.dp),

                                        /**  I D E N T I F I C A C I O N  */
                                        content1 = {

                                            sSurface(
                                                /*modifier = Modifier.padding(
                                                    horizontal = 9.dp,
                                                    vertical = 15.dp,
                                                )*/
                                            ) {
                                                // Aqui queda el valor con el que se busca en el dialogo anterior
                                                //var value by rememberSaveable { mutableStateOf( cliente_id_value ) }

                                                xOutlinedTextField_CHAR(
                                                    value = identificacion_value,
                                                    onValueChange = {

                                                        if (mValidarEntradasRegex.validarAlfanumericos(
                                                                it
                                                            )
                                                        ) {
                                                            //value = it
                                                            identificacion_value = it
                                                        } else if (it.isEmpty()) {
                                                            //value = it
                                                            identificacion_value = it
                                                        }

                                                    },
                                                    label = {
                                                        HLayout2P(
                                                            horizontalArrangement = Arrangement.spacedBy(
                                                                9.dp,
                                                                alignment = Alignment.CenterHorizontally
                                                            ),
                                                            content1 = { sIcon(image_vector_id = R.drawable.rounded_id_card_24) },
                                                            content2 = {
                                                                xTextLabel(
                                                                    text = stringResource(
                                                                        id = R.string.identificacion
                                                                    )
                                                                )
                                                            },
                                                        )
                                                    },
                                                    //visualTransformation = SeparadorDeMiles()
                                                )
                                            }
                                        },

                                        /**  NOMBRES Y APELLIDOS */
                                        content2 = {
                                            sSurface(
                                                /*modifier = Modifier.padding(
                                                    horizontal = 9.dp,
                                                    vertical = 15.dp,
                                                )*/
                                            ) {
                                                var value by rememberSaveable { mutableStateOf("") }

                                                xOutlinedTextField_CHAR(
                                                    value = value,
                                                    onValueChange = {

                                                        if (mValidarEntradasRegex.validarAlfanumericos(
                                                                it
                                                            )
                                                        ) {
                                                            value = it
                                                            nombre_y_apellidos_value = it
                                                        } else if (it.isEmpty()) {
                                                            value = it
                                                            nombre_y_apellidos_value = it
                                                        }

                                                    },
                                                    label = {
                                                        HLayout2P(
                                                            horizontalArrangement = Arrangement.spacedBy(
                                                                9.dp,
                                                                alignment = Alignment.CenterHorizontally
                                                            ),
                                                            content1 = { sIcon(image_vector_id = R.drawable.rounded_insert_text_24) },
                                                            content2 = {
                                                                xTextLabel(
                                                                    text = stringResource(
                                                                        id = R.string.txt_label_nombre_apellidos
                                                                    )
                                                                )
                                                            },
                                                        )
                                                    },
                                                    //visualTransformation = SeparadorDeMiles()
                                                )
                                            }
                                        },

                                        /** TELEFONO CONTACTO */
                                        content3 = {

                                            sSurface(
                                                /*modifier = Modifier.padding(
                                                    horizontal = 9.dp,
                                                    vertical = 15.dp,
                                                )*/
                                            ) {
                                                var value by rememberSaveable { mutableStateOf("") }

                                                xOutlinedTextField_TEL(
                                                    value = value,
                                                    onValueChange = {

                                                        if (mValidarEntradasRegex.validarAlfanumericos(
                                                                it
                                                            )
                                                        ) {
                                                            value = it
                                                            telefono_value = it
                                                        } else if (it.isEmpty()) {
                                                            value = it
                                                            telefono_value = it
                                                        }

                                                    },
                                                    label = {
                                                        HLayout2P(
                                                            //modifier = Modifier.background( color = MaterialTheme.colorScheme.secondaryContainer, shape = RoundedCornerShape(17.dp) ),
                                                            horizontalArrangement = Arrangement.spacedBy(
                                                                9.dp,
                                                                alignment = Alignment.CenterHorizontally
                                                            ),
                                                            content1 = { sIcon(image_vector_id = R.drawable.rounded_perm_phone_msg_24) },
                                                            content2 = {
                                                                xTextLabel(
                                                                    text = stringResource(
                                                                        id = R.string.txt_label_telefono
                                                                    )
                                                                )
                                                            },
                                                        )
                                                    },
                                                    //visualTransformation = SeparadorDeMiles()
                                                )
                                            }
                                        },
                                    )
                                },
                            )
                        }
                    } else {

                        DatosUsuarioWidget(
                            txt_body_nombre = resultado_busqueda_cliente!!.nombre_apellidos,
                            txt_body_identificacion = resultado_busqueda_cliente!!.identificacion_pk,
                            txt_body_telefono = resultado_busqueda_cliente!!.telefono,
                        )
                    }

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

                    if (resultado_busqueda_vehiculo == null) {

                        sSurface(
                            modifier = Modifier
                                .fillMaxWidth(),
                            //.size(339.dp)
                            //.weight(1f),
                            shape = RoundedCornerShape(5),
                            color = color,
                        ) {

                            VLayout2P(
                                modifier = Modifier.padding(all = 11.dp),
                                verticalArrangement = Arrangement.spacedBy(7.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                content1 = {
                                    xTextBody(
                                        text = stringResource(R.string.txt_body_agregar_datos_vehiculo),
                                        color = MaterialTheme.colorScheme.error
                                    )
                                },
                                content2 = {
                                    VLayout2P(
                                        modifier = Modifier.padding(all = 13.dp),
                                        verticalArrangement = Arrangement.spacedBy(space = 13.dp),

                                        content1 = {
                                            sSurface() {
                                                //var value by rememberSaveable { mutableStateOf("") }
                                                //var value by rememberSaveable { mutableStateOf( matricala_value_buscado ) }

                                                /*
                                                if(value.length >= 1){
                                                    enabled_btn2 = true
                                                } else {
                                                    enabled_btn2 = false
                                                }
                                                */

                                                //denominacion_value
                                                xOutlinedTextField_CHAR(
                                                    //value = value,
                                                    value = matricula_vehiculo_value,
                                                    onValueChange = {

                                                        if (mValidarEntradasRegex.validarAlfanumericos(
                                                                it
                                                            )
                                                        ) {
                                                            //value = it.uppercase()
                                                            matricula_vehiculo_value =
                                                                it.uppercase()
                                                        } else {
                                                            //value = ""
                                                            matricula_vehiculo_value = ""
                                                        }
                                                    },
                                                    label = {
                                                        HLayout2P(
                                                            horizontalArrangement = Arrangement.spacedBy(
                                                                9.dp,
                                                                alignment = Alignment.CenterHorizontally
                                                            ),
                                                            content1 = { sIcon(image_vector_id = R.drawable.rounded_pin_24) },
                                                            content2 = {
                                                                xTextLabel(
                                                                    text = stringResource(
                                                                        id = R.string.txt_label_matricula_vehiculo
                                                                    )
                                                                )
                                                            },
                                                        )
                                                    },
                                                )
                                            }
                                        },

                                        content2 = {

                                            var categoria_vehiculo by rememberSaveable {
                                                mutableStateOf(
                                                    ""
                                                )
                                            }

                                            var expander by rememberSaveable { mutableStateOf(false) }

                                            sSurface {
                                                if (categoria_vehiculo.isBlank() or categoria_vehiculo.isEmpty()) {
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
                                                        txt_body = R.string.txt_body_elige_una_categoria,
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
                                                        txt_body = categoria_vehiculo,
                                                    )
                                                }

                                                DropdownMenu(
                                                    expanded = expander,
                                                    onDismissRequest = { expander = false },
                                                    modifier = Modifier.size(
                                                        width = 245.dp,
                                                        height = Dp.Unspecified
                                                    )
                                                ) {
                                                    lista_categorias_vehiculo.forEachIndexed { index, item ->

                                                        DropdownMenuItem(
                                                            text = { xTextBody(text = "${index} - ${item.clase_id_pk} - ${item.descripcion}") },
                                                            onClick = {
                                                                categoria_seleccionada =
                                                                    item.clase_id_pk
                                                                categoria_vehiculo =
                                                                    "${item.clase_id_pk} - ${item.descripcion}"
                                                                expander = false
                                                            },
                                                        )
                                                    }
                                                }
                                            }
                                        },
                                    )
                                },
                            )
                        }

                    } else {

                        val id_clasificacion = lista_clasificaciones_vehiculos.binarySearch {
                            it.clase_id_pk.compareTo(resultado_busqueda_vehiculo!!.clase_id_fk)
                        }

                        DatosVehiculoWidget(
                            txt_body_matricula = resultado_busqueda_vehiculo!!.matricula_pk,
                            txt_body_clasificacion = lista_clasificaciones_vehiculos[id_clasificacion].descripcion,
                        )
                    }

                }

                mDetalleOrdenServicioViewModel.sumarTodosLosPreciosDeListaDeServiciosPorAgregar(
                    uiState_DetalleOrdenServicioViewModel.nuevos_precios_de_servicios_por_agregar_a_la_orden
                )

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
                            xTextHeadLine(
                                text = "${stringResource(R.string.txt_servicios_agregados)}: $${uiState_DetalleOrdenServicioViewModel.suma_precios_servicios_por_agregar_a_orden.floatValue}",
                                //color = MaterialTheme.colorScheme.primary
                            )
                        }
                    )

                    HLayout1P(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        content1 = {
                            sTextButton(onClick = {
                                val desdeNuevaOrdenDeVenta = true

                                onNavigateToAgregarServicioDialog(desdeNuevaOrdenDeVenta)

                            }) {
                                xTextLabel(text = stringResource(id = R.string.txt_label_agregar_servicio))
                                sIcon(image_vector_id = R.drawable.rounded_add_24)
                            }
                        },
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

                    if (resultado_busqueda_cliente != null && resultado_busqueda_vehiculo != null
                        && uiState_DetalleOrdenServicioViewModel.nuevos_servicios_por_agregar_a_la_orden.isNotEmpty()
                    ) {
                        enabled_boton_crear_nueva_orden = true
                    } else if (identificacion_value.length >= 2
                        && nombre_y_apellidos_value.length >= 2 && telefono_value.length >= 2
                        && matricula_vehiculo_value.length >= 2 && categoria_seleccionada > 0
                        && uiState_DetalleOrdenServicioViewModel.nuevos_servicios_por_agregar_a_la_orden.isNotEmpty()
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
                                                valor_total_solo_servicios = uiState_DetalleOrdenServicioViewModel.suma_precios_servicios_por_agregar_a_orden.floatValue,
                                                valor_total_solo_productos = uiState_DetalleOrdenProductoViewModel.suma_precios_productos_por_agregar_a_orden.floatValue,
                                                orden_vigente = true,
                                                fecha_hora_creacion = uiState_OrdenDeVentaViewModel.fecha_y_hora,
                                            )
                                        )

                                        // guardar los servicios y/o productos
                                        mDetalleOrdenServicioViewModel.insertarListaDeDetallesAOrdenDeServiciosUC(
                                            uiState_DetalleOrdenServicioViewModel.nuevos_servicios_por_agregar_a_la_orden
                                        )

                                        // eliminar valores de uiState
                                        mOrdenDeVentaViewModel.limpiarDatosUiState()
                                        mDetalleOrdenServicioViewModel.limpiarDatosUiState()

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
        modifier = Modifier,
    )
}