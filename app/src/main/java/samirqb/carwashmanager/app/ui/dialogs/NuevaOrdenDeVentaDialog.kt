package samirqb.carwashmanager.app.ui.dialogs

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout2P
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout3P
import samirqb.carwashmanager.app.ui.components.custom.textfields.xOutlinedTextField_CHAR
import samirqb.carwashmanager.app.ui.components.custom.textfields.xOutlinedTextField_TEL
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextTitle
import samirqb.carwashmanager.app.ui.components.custom.widgets.DatosUsuarioWidget
import samirqb.carwashmanager.app.ui.components.custom.widgets.DatosVehiculoWidget
import samirqb.carwashmanager.app.ui.templates.iconsandtexts.tHTextAndIcon
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tDialogScaffoldM2
import samirqb.carwashmanager.app.viewmodels.ClasificacionDelVehiculoViewModel
import samirqb.carwashmanager.app.viewmodels.ClienteViewModel
import samirqb.carwashmanager.app.viewmodels.OperarioViewModel
import samirqb.carwashmanager.app.viewmodels.ServicioViewModel
import samirqb.carwashmanager.app.viewmodels.VehiculoViewModel
import samirqb.lcarwashmanager.app.ui.layoutcomponets.VLayout2P
import samirqb.lib.helpers.ValidarEntradasRegex

@Composable
fun NuevaOrdenDeVentaDialog(
    mClienteViewModel: ClienteViewModel,
    mClasificacionDelVehiculoViewModel: ClasificacionDelVehiculoViewModel,
    mVehiculoViewModel: VehiculoViewModel,
    mOperarioViewModel: OperarioViewModel,
    mServicioViewModel: ServicioViewModel,
    onDismissFromNuevaOrdenDeVentaDialog: () -> Unit,
    //onNavigateToVincularVehiculoDialog: () -> Unit,
) {

    val mValidarEntradasRegex = ValidarEntradasRegex()

    mClasificacionDelVehiculoViewModel.listarTodasLasClasificacionesDeVehiculoUserCase()
    mOperarioViewModel.listarTodosLosOperariosUC()
    mServicioViewModel.listarTodosLosServiciosUC()


    val uiState_ClienteViemodel by mClienteViewModel.uiState.collectAsState()
    val uiState_ClasificacionDelVehiculoViewModel by mClasificacionDelVehiculoViewModel.uiState.collectAsState()
    val uiState_VehiculoViemodel by mVehiculoViewModel.uiState.collectAsState()

    val resultado_busqueda_cliente = uiState_ClienteViemodel.resultado_busqueda_cliente
    val lista_clasificaciones_vehiculos = uiState_ClasificacionDelVehiculoViewModel.listar_todas_las_clasificaciones_de_vehiculo.sortedBy { it.clase_id_pk }
    val resultado_busqueda_vehiculo = uiState_VehiculoViemodel.resultado_busqueda_vehiculo
    val lista_categorias_vehiculo = uiState_ClasificacionDelVehiculoViewModel.listar_todas_las_clasificaciones_de_vehiculo

    var enabled_btn1 by rememberSaveable { mutableStateOf(true) }
    var min_char_to_enabled_btn2_from_cliente by rememberSaveable { mutableStateOf(false) }
    var min_char_to_enabled_btn2_from_vehiculo by rememberSaveable { mutableStateOf(false) }

    var cliente_id_value by rememberSaveable { mutableStateOf("") }
    var busqueda_cliente_realizada by rememberSaveable { mutableStateOf( false) }

    var matricala_value by rememberSaveable { mutableStateOf("") }
    var busqueda_vehiculo_realizada by rememberSaveable { mutableStateOf( false) }

    // formulario cliente si cliente no existe en db
    var identificacion_value by rememberSaveable { mutableStateOf("") }
    var nombre_y_apellidos_value by rememberSaveable { mutableStateOf("") }
    var telefono_value by rememberSaveable { mutableStateOf("") }

    // formulario vahiculo si vehiculo no exista en db
    var categoria_seleccionada by rememberSaveable { mutableStateOf(0) }
    var matricula_vehiculo_value by rememberSaveable { mutableStateOf("") }

    tDialogScaffoldM2(
        header_icon_id = R.drawable.rounded_order_play_24,
        header_text_titulo_id = R.string.txt_titulo_nueva_orden_servidios,
        content_dialg_body = {

            sSurface(
                color = MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier
                    .fillMaxWidth()
                    //.size(432.dp),
            ) {

                //VLayout3P(
                VLayout2P(
                    modifier = Modifier.padding(all = 13.dp),
                    verticalArrangement = Arrangement.spacedBy(space = 19.dp),

                    /** DATOS DEL CLIENTE */
                    content1 = {

                        if (!busqueda_cliente_realizada && resultado_busqueda_cliente == null){

                            var value by rememberSaveable { mutableStateOf("") }

                            if(value.length >= 1){
                                min_char_to_enabled_btn2_from_cliente = true
                            } else {
                                min_char_to_enabled_btn2_from_cliente = false
                            }

                            xTextBody(
                                text = stringResource(R.string.txt_body_ingrese_identificacion_cliente),
                                color = MaterialTheme.colorScheme.error
                            )

                            //denominacion_value
                            xOutlinedTextField_CHAR(
                                value = value,
                                onValueChange = {

                                    if(mValidarEntradasRegex.validarAlfanumericos(it)) {
                                        value = it.uppercase()
                                        cliente_id_value = it.uppercase()
                                    } else {
                                        value = ""
                                        cliente_id_value = ""
                                    }
                                },
                                label = {
                                    HLayout2P(
                                        horizontalArrangement = Arrangement.spacedBy(9.dp, alignment = Alignment.CenterHorizontally),
                                        content1 = { sIcon(image_vector_id = R.drawable.rounded_id_card_24) },
                                        content2 = { xTextLabel(text = stringResource(id = R.string.identificacion)) },
                                    )
                                },
                            )
                        } else if(busqueda_cliente_realizada && resultado_busqueda_cliente == null) {
                            xTextBody(
                                text = stringResource(R.string.txt_body_agregar_datos_cliente),
                                color = MaterialTheme.colorScheme.error
                            )

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
                                        // var value by rememberSaveable { mutableStateOf("") }
                                        var value by rememberSaveable { mutableStateOf(cliente_id_value) }

                                        xOutlinedTextField_CHAR(
                                            value = value,
                                            onValueChange = {

                                                if (mValidarEntradasRegex.validarAlfanumericos(it)) {
                                                    value = it
                                                    identificacion_value = it
                                                } else if(it.isEmpty()) {
                                                    value = it
                                                    identificacion_value = it
                                                }

                                            },
                                            label = {
                                                HLayout2P(
                                                    horizontalArrangement = Arrangement.spacedBy(9.dp, alignment = Alignment.CenterHorizontally),
                                                    content1 = { sIcon(image_vector_id = R.drawable.rounded_id_card_24) },
                                                    content2 = { xTextLabel(text = stringResource(id = R.string.identificacion)) },
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

                                                if (mValidarEntradasRegex.validarAlfanumericos(it)) {
                                                    value = it
                                                    nombre_y_apellidos_value = it
                                                } else if(it.isEmpty()) {
                                                    value = it
                                                    nombre_y_apellidos_value = it
                                                }

                                            },
                                            label = {
                                                HLayout2P(
                                                    horizontalArrangement = Arrangement.spacedBy(9.dp, alignment = Alignment.CenterHorizontally),
                                                    content1 = { sIcon(image_vector_id = R.drawable.rounded_insert_text_24) },
                                                    content2 = { xTextLabel(text = stringResource(id = R.string.txt_label_nombre_apellidos)) },
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

                                                if (mValidarEntradasRegex.validarAlfanumericos(it)) {
                                                    value = it
                                                    telefono_value = it
                                                } else if(it.isEmpty()) {
                                                    value = it
                                                    telefono_value = it
                                                }

                                            },
                                            label = {
                                                HLayout2P(
                                                    //modifier = Modifier.background( color = MaterialTheme.colorScheme.secondaryContainer, shape = RoundedCornerShape(17.dp) ),
                                                    horizontalArrangement = Arrangement.spacedBy(9.dp, alignment = Alignment.CenterHorizontally),
                                                    content1 = { sIcon(image_vector_id = R.drawable.rounded_perm_phone_msg_24) },
                                                    content2 = { xTextLabel(text = stringResource(id = R.string.txt_label_telefono)) },
                                                )
                                            },
                                            //visualTransformation = SeparadorDeMiles()
                                        )
                                    }

                                },
                            )

                        } else {

                            DatosUsuarioWidget(
                                txt_body_nombre = resultado_busqueda_cliente!!.nombre_apellidos,
                                txt_body_identificacion = resultado_busqueda_cliente!!.identificacion_pk,
                                txt_body_telefono =  resultado_busqueda_cliente!!.telefono,
                            )

                            /*
                            VLayout3P(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(space = 13.dp),
                                horizontalAlignment = Alignment.Start,
                                content1 = {
                                    xTextTitle(text = resultado_busqueda_cliente!!.nombre_apellidos)
                                },
                                content2 = {
                                    HLayout2P(
                                        content1 = {
                                            //sIcon(image_vector_id = R.drawable.rounded_id_card_24)
                                            xTextBody(text = "Identificacion: ")
                                        },
                                        content2 = {
                                            xTextBody(text = resultado_busqueda_cliente!!.identificacion_pk)
                                        },
                                    )

                                },
                                content3 = {
                                    HLayout2P(
                                        content1 = {
                                            //sIcon(image_vector_id = R.drawable.rounded_perm_phone_msg_24)
                                            xTextBody(text = "Telefono: ")
                                        },
                                        content2 = {
                                            xTextBody(text = resultado_busqueda_cliente!!.telefono)
                                        },
                                    )
                                },
                            )
                            */
                        }
                    },

                    /** CAMPO PARA BUSCAR MATRICULA DEL VEHICULO */
                    content2 = {

                        if(!busqueda_vehiculo_realizada && resultado_busqueda_vehiculo == null){
                            var value by rememberSaveable { mutableStateOf("") }

                            if (value.length >= 1) {
                                min_char_to_enabled_btn2_from_vehiculo = true
                            } else {
                                min_char_to_enabled_btn2_from_vehiculo = false
                            }

                            xTextBody(
                                text = stringResource(R.string.txt_body_ingrese_matricula_vehiculo),
                                color = MaterialTheme.colorScheme.error
                            )

                            xOutlinedTextField_CHAR(
                                value = value,
                                onValueChange = {

                                    if (mValidarEntradasRegex.validarAlfanumericos(it)) {
                                        value = it.uppercase()
                                        matricala_value = it.uppercase()
                                    } else {
                                        value = ""
                                        matricala_value = ""
                                    }
                                },

                                label = {
                                    HLayout2P(
                                        horizontalArrangement = Arrangement.spacedBy(9.dp, alignment = Alignment.CenterHorizontally),
                                        content1 = { sIcon(image_vector_id = R.drawable.rounded_pin_24) },
                                        content2 = { xTextLabel(text = stringResource(id = R.string.txt_label_matricula_vehiculo)) },
                                    )
                                },
                            )

                        } else if(busqueda_vehiculo_realizada && resultado_busqueda_vehiculo == null) {
                            xTextBody(
                                text = stringResource(R.string.txt_body_agregar_datos_vehiculo),
                                color = MaterialTheme.colorScheme.error
                            )

                            VLayout2P(
                                modifier = Modifier.padding(all = 13.dp),
                                verticalArrangement = Arrangement.spacedBy(space = 13.dp),

                                content1 = {
                                    sSurface() {
                                        //var value by rememberSaveable { mutableStateOf("") }
                                        var value by rememberSaveable { mutableStateOf(matricala_value) }

                                        /*
                                        if(value.length >= 1){
                                            enabled_btn2 = true
                                        } else {
                                            enabled_btn2 = false
                                        }
                                        */

                                        //denominacion_value
                                        xOutlinedTextField_CHAR(
                                            value = value,
                                            onValueChange = {

                                                if(mValidarEntradasRegex.validarAlfanumericos(it)) {
                                                    value = it.uppercase()
                                                    matricula_vehiculo_value = it.uppercase()
                                                } else {
                                                    value = ""
                                                    matricula_vehiculo_value = ""
                                                }
                                            },
                                            label = {
                                                HLayout2P(
                                                    horizontalArrangement = Arrangement.spacedBy(9.dp, alignment = Alignment.CenterHorizontally),
                                                    content1 = { sIcon(image_vector_id = R.drawable.rounded_pin_24) },
                                                    content2 = { xTextLabel(text = stringResource(id = R.string.txt_label_matricula_vehiculo)) },
                                                )
                                            },
                                        )
                                    }
                                },

                                content2 = {

                                    var categoria_vehiculo by rememberSaveable { mutableStateOf("") }

                                    var expander by rememberSaveable { mutableStateOf(false) }

                                    sSurface {
                                        if (categoria_vehiculo.isBlank() or categoria_vehiculo.isEmpty()) {
                                            tHTextAndIcon(
                                                full_surface_modifier = Modifier
                                                    .fillMaxWidth()
                                                    .size(55.dp)
                                                    .pointerInput(Unit) {
                                                        detectTapGestures { expander = true }
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
                                                        detectTapGestures { expander = true }
                                                    },
                                                //image_vector_ini_id = R.drawable.round_monetization_on_24,
                                                image_vector_fin_id = R.drawable.rounded_arrow_drop_down_24,
                                                txt_body = categoria_vehiculo,
                                            )
                                        }

                                        DropdownMenu(
                                            expanded = expander,
                                            onDismissRequest = { expander = false },
                                            modifier = Modifier.size(width = 266.dp, height = Dp.Unspecified)
                                        ) {
                                            lista_categorias_vehiculo.forEachIndexed { index, item ->
                                                DropdownMenuItem(
                                                    text = { xTextBody(text = "${index} - ${item.clase_id_pk} - ${item.descripcion}") },
                                                    onClick = {
                                                        categoria_seleccionada = item.clase_id_pk
                                                        categoria_vehiculo =
                                                            "${item.clase_id_pk} - ${item.descripcion}"
                                                        expander = false
                                                    }
                                                )

                                            }
                                        }
                                    }

                                },
                            )

                        } else {

                            val id_clasificacion = lista_clasificaciones_vehiculos.binarySearch{
                                it.clase_id_pk.compareTo(resultado_busqueda_vehiculo!!.clase_id_fk)
                            }

                            DatosVehiculoWidget(
                                txt_body_matricula = resultado_busqueda_vehiculo!!.matricula_pk,
                                txt_body_clasificacion = lista_clasificaciones_vehiculos[id_clasificacion].descripcion,
                            )

                            /*
                            VLayout2P(
                                content1 = {
                                    HLayout2P(
                                        content1 = {
                                            sIcon(image_vector_id = R.drawable.rounded_pin_24)
                                        },
                                        content2 = {
                                            xTextTitle(text = resultado_busqueda_vehiculo!!.matricula_pk)
                                        },
                                    )
                                },

                                content2 = {
                                    HLayout2P(
                                        content1 = {
                                            sIcon(image_vector_id = R.drawable.rounded_car_tag_24)
                                        },
                                        content2 = {
                                            val id_clasificacion = lista_clasificaciones_vehiculos.binarySearch{
                                                it.clase_id_pk.compareTo(resultado_busqueda_vehiculo!!.clase_id_fk)
                                            }
                                            xTextTitle(text = lista_clasificaciones_vehiculos[id_clasificacion].descripcion)
                                        },
                                    )
                                },
                            )
                            */
                        }
                    },
                    /*
                    content3 = {
                        if (busqueda_cliente_realizada && busqueda_vehiculo_realizada){
                            xTextBody(
                                text = "DROPDOWN LISTA SERVICIOS DISPONIBLES",
                            )
                        }
                    },
                    */
                )
            }
        },

        enabled_btn1 = enabled_btn1,
        boton_txt_1 = R.string.txt_label_cancelar,
        on_click_boton_1 = {
            onDismissFromNuevaOrdenDeVentaDialog()

            /** SE LIMPIAN LOS RESULTADOS DE BUSQUEDA */
            mClienteViewModel.limpiarResultadoDeBusqueda()
            mVehiculoViewModel.limpiarResultadoDeBusqueda()
        },

        enabled_btn2 = min_char_to_enabled_btn2_from_cliente && min_char_to_enabled_btn2_from_vehiculo,
        boton_txt_2 = R.string.txt_label_siguiente,
        on_click_boton_2 = {

            mClienteViewModel.buscarClientePorIdUseCase(cliente_id_value)
            busqueda_cliente_realizada = true

            mVehiculoViewModel.buscarVehiculoPorMatriculaUseCase(matricala_value)
            busqueda_vehiculo_realizada = true

            /*
            if (resultado_busqueda_cliente != null) {
                onDismissFromVincularClienteYVehiculoDialog()
                //onNavigateToVincularVehiculoDialog()
            }
            */

        },
        //header
        modifier_content_header = Modifier
            .fillMaxWidth()
            .size(150.dp),

        //botones accion
        modifier_content_action_buttons = Modifier
            .fillMaxWidth()
            .size(50.dp),
    )

}