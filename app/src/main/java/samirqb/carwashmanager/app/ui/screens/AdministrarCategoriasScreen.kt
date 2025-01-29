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
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sCard
import samirqb.carwashmanager.app.ui.components.base.layouts.sColumn
import samirqb.carwashmanager.app.ui.components.base.layouts.sLazyColumn
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextTitle
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tScaffoldM3
import samirqb.carwashmanager.app.viewmodels.ClasificacionDelVehiculoViewModel

@Composable
fun AdministrarCategoriasScreen(
    mClasificacionDelVehiculoViewModel: ClasificacionDelVehiculoViewModel,
    onClick_agregar_categoria:()->Unit,
    onClick_navigate_back:()->Unit,
) {

    val uiState by mClasificacionDelVehiculoViewModel.uiState.collectAsState()

    mClasificacionDelVehiculoViewModel.listarTodasLasClasificacionesDeVehiculoUserCase()

    var listarTLC = uiState.listar_todas_las_clasificaciones_de_vehiculo

    tScaffoldM3(
        top_app_bar_title = R.string.txt_body_menu_categorias_vehiculos,
        top_app_bar_navigation_back = {
            onClick_navigate_back()
        },
        content1 = {

            sLazyColumn(
                verticalArrangement = Arrangement.spacedBy(space = 13.dp, alignment = Alignment.CenterVertically)
            ) {
                itemsIndexed( listarTLC ){index, item ->

                    //listarTLS.binarySearch {  }

                    sCard(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer
                        ),
                        modifier = Modifier.fillMaxSize().size(50.dp)
                    ) {
                        sColumn {
                            xTextTitle(text = item.descripcion)
                            xTextLabel(text = item.fecha_hora_creacion)
                        }
                    }
                }
            }

        },
        top_app_bar_navigation_back_icon = R.drawable.rounded_keyboard_backspace_24,
        floatingActionButton_onClick = {
            onClick_agregar_categoria()
        },
        floatingActionButton_image_vector_id = R.drawable.rounded_add_24,
    )
}
