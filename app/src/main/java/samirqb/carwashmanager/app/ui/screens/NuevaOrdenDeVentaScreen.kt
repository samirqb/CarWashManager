package samirqb.carwashmanager.app.ui.screens

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
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
import samirqb.carwashmanager.app.ui.components.custom.widgets.DatosUsuarioWidget
import samirqb.carwashmanager.app.ui.components.custom.widgets.DatosVehiculoWidget
import samirqb.carwashmanager.app.ui.templates.iconsandtexts.tHTextAndIcon
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tScaffoldM5
import samirqb.carwashmanager.app.viewmodels.ClasificacionDelVehiculoViewModel
import samirqb.carwashmanager.app.viewmodels.ClienteViewModel
import samirqb.carwashmanager.app.viewmodels.OperarioViewModel
import samirqb.carwashmanager.app.viewmodels.ServicioViewModel
import samirqb.carwashmanager.app.viewmodels.VehiculoViewModel
import samirqb.lcarwashmanager.app.ui.layoutcomponets.VLayout2P
import samirqb.lib.helpers.ValidarEntradasRegex

@Composable
fun NuevaOrdenDeVentaScreen(
    mClienteViewModel: ClienteViewModel,
    mVehiculoViewModel: VehiculoViewModel,
    mClasificacionDelVehiculoViewModel: ClasificacionDelVehiculoViewModel,
    mOperarioViewModel: OperarioViewModel,
    mServicioViewModel: ServicioViewModel,

    onClick_navigate_back: () -> Unit,
) {

    val mValidarEntradasRegex = ValidarEntradasRegex()

    val uiState_ClienteViemodel by mClienteViewModel.uiState.collectAsState()
    val uiState_VehiculoViemodel by mVehiculoViewModel.uiState.collectAsState()
    val uiState_ClasificacionDelVehiculoViewModel by mClasificacionDelVehiculoViewModel.uiState.collectAsState()


    mClasificacionDelVehiculoViewModel.listarTodasLasClasificacionesDeVehiculoUserCase()
    mOperarioViewModel.listarTodosLosOperariosUC()
    mServicioViewModel.listarTodosLosServiciosUC()


    val resultado_busqueda_cliente = uiState_ClienteViemodel.resultado_busqueda_cliente
    val resultado_busqueda_vehiculo = uiState_VehiculoViemodel.resultado_busqueda_vehiculo

    var cliente_id_value by rememberSaveable { mutableStateOf("") }
    var matricala_value by rememberSaveable { mutableStateOf("") }

    val lista_clasificaciones_vehiculos =
        uiState_ClasificacionDelVehiculoViewModel.listar_todas_las_clasificaciones_de_vehiculo.sortedBy { it.clase_id_pk }
    val lista_categorias_vehiculo =
        uiState_ClasificacionDelVehiculoViewModel.listar_todas_las_clasificaciones_de_vehiculo

    // formulario cliente si cliente no existe en db
    var identificacion_value by rememberSaveable { mutableStateOf("") }
    var nombre_y_apellidos_value by rememberSaveable { mutableStateOf("") }
    var telefono_value by rememberSaveable { mutableStateOf("") }

    // formulario vahiculo si vehiculo no exista en db
    var categoria_seleccionada by rememberSaveable { mutableStateOf(0) }
    var matricula_vehiculo_value by rememberSaveable { mutableStateOf("") }

    tScaffoldM5(
        top_app_bar_title = R.string.txt_titulo_nueva_orden_servidios,
        top_app_bar_navigation_back = { onClick_navigate_back() },
        top_app_bar_navigation_back_icon = R.drawable.rounded_keyboard_backspace_24,
        content1 = {


            VLayout2P(

                modifier = Modifier.padding(all = 3.dp),
                verticalArrangement = Arrangement.spacedBy(
                    space = 19.dp,
                    alignment = Alignment.CenterVertically
                ),

                content1 = {

                    if (resultado_busqueda_cliente == null) {

                        sSurface(
                            modifier = Modifier
                                .fillMaxWidth(),
                            //.size(339.dp)
                            //.weight(1f),
                            shape = RoundedCornerShape(5),
                            //color = MaterialTheme.colorScheme.onSecondary,
                            //color = MaterialTheme.colorScheme.secondaryContainer,
                            color = MaterialTheme.colorScheme.surfaceContainer,
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
                                                // var value by rememberSaveable { mutableStateOf("") }
                                                var value by rememberSaveable {
                                                    mutableStateOf(
                                                        cliente_id_value
                                                    )
                                                }

                                                xOutlinedTextField_CHAR(
                                                    value = value,
                                                    onValueChange = {

                                                        if (mValidarEntradasRegex.validarAlfanumericos(
                                                                it
                                                            )
                                                        ) {
                                                            value = it
                                                            identificacion_value = it
                                                        } else if (it.isEmpty()) {
                                                            value = it
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

                },
                content2 = {

                    if (resultado_busqueda_vehiculo == null) {

                        sSurface(
                            modifier = Modifier
                                .fillMaxWidth(),
                            //.size(339.dp)
                            //.weight(1f),
                            shape = RoundedCornerShape(5),
                            //color = MaterialTheme.colorScheme.onSecondary,
                            color = MaterialTheme.colorScheme.surfaceContainer,
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
                                                var value by rememberSaveable {
                                                    mutableStateOf(
                                                        matricala_value
                                                    )
                                                }

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

                                                        if (mValidarEntradasRegex.validarAlfanumericos(
                                                                it
                                                            )
                                                        ) {
                                                            value = it.uppercase()
                                                            matricula_vehiculo_value =
                                                                it.uppercase()
                                                        } else {
                                                            value = ""
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
                                                            }
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

                },
            )
        },
        modifier = Modifier,
    )
}