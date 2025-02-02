package samirqb.carwashmanager.app.ui.screens

import android.util.Log
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
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tScaffoldM3
import samirqb.carwashmanager.app.viewmodels.ClasificacionDelVehiculoViewModel
import samirqb.carwashmanager.app.viewmodels.VehiculoViewModel

@Composable
fun AdministrarVehiculosScreen(
    mClasificacionDelVehiculoViewModel: ClasificacionDelVehiculoViewModel,
    mVehiculoViewModel: VehiculoViewModel,
    onClick_navigate_back: ()->Unit,
    onClick_agregar_vehiculo: ()->Unit,
) {

    val uiState by mVehiculoViewModel.uiState.collectAsState()
    val uiState_ClasificacionVehiculo by mClasificacionDelVehiculoViewModel.uiState.collectAsState()

    var lista_de_vehiculos = uiState.listar_todos_los_vehiculos
    var lista_de_clasificacion_vehiculos = uiState_ClasificacionVehiculo.listar_todas_las_clasificaciones_de_vehiculo.sortedBy { it.clase_id_pk }

    mVehiculoViewModel.listarTodosLosVehiculoUserCase()
    mClasificacionDelVehiculoViewModel.listarTodasLasClasificacionesDeVehiculoUserCase()

    tScaffoldM3(
        top_app_bar_title = R.string.txt_body_menu_vehiculos,
        top_app_bar_navigation_back = {
            onClick_navigate_back()
        },
        content1 = {

            if (lista_de_vehiculos.isEmpty() || lista_de_clasificacion_vehiculos.isEmpty()){

                sColumn(

                ) {
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
                }

                //xTextTitle(text = stringResource(id = R.string.informacion_no_disponible))

            } else{

                Log.i("_xTAG", lista_de_vehiculos.toString())
                Log.i("_xTAG", lista_de_clasificacion_vehiculos.toString())

                sLazyColumn(
                    verticalArrangement = Arrangement.spacedBy(space = 13.dp, alignment = Alignment.CenterVertically)
                ) {
                    itemsIndexed( lista_de_vehiculos ){index, item ->

                        val id_clasificacion = lista_de_clasificacion_vehiculos.binarySearch{
                            it.clase_id_pk.compareTo(item.clase_id_fk)
                        }

                        sCard(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceContainer
                            ),
                            modifier = Modifier.fillMaxSize().size(59.dp)
                        ) {
                            sColumn {
                                xTextTitle(text = item.matricula_pk)
                                xTextLabel(text = lista_de_clasificacion_vehiculos[id_clasificacion].descripcion?:"NO DEFINIDA_1")
                                xTextLabel(text = item.fecha_hora_creacion)
                            }
                        }
                    }
                }
            }
        },
        top_app_bar_navigation_back_icon = R.drawable.rounded_keyboard_backspace_24,
        floatingActionButton_onClick = {
            onClick_agregar_vehiculo()
        },
        floatingActionButton_image_vector_id = R.drawable.rounded_add_24,
    )
}