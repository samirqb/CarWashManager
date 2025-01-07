package samirqb.carwashmanager.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sCard
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.base.layouts.sLazyColumn
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout3P
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout3P
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextTitle
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tScaffoldM3
import samirqb.carwashmanager.app.viewmodels.OperarioViewModel
import samirqb.lib.personas.entities.OperarioEntity

@Composable
fun AdministrarEmpleadosScreen(
    mOperarioViewModel: OperarioViewModel,
    onClick_agregar_empleado:()->Unit,
    onClick_navigate_back:()->Unit,
) {

    var uiState = mOperarioViewModel.uiState.collectAsState()

    mOperarioViewModel.listarTodosLosOperariosUC()

    tScaffoldM3(
        top_app_bar_title = R.string.txt_body_menu_empleados,
        top_app_bar_navigation_back = {
            onClick_navigate_back()
        },
        content1 = {

            sLazyColumn(
                verticalArrangement = Arrangement.spacedBy(space = 13.dp, alignment = Alignment.CenterVertically)
            ) {
                itemsIndexed(uiState.value.todos_los_operarios) { index: Int, item: OperarioEntity ->
                    sCard(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer
                        ),
                        modifier = Modifier.fillMaxSize().size(90.dp)
                    ) {
                        HLayout3P(
                            modifier = Modifier.fillMaxSize().padding(7.dp),
                            horizontalArrangement = Arrangement.spacedBy(space = 13.dp, alignment = Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,

                            /**   I C O N O   */
                            content1 = {
                                sSurface(
                                    modifier = Modifier.size(70.dp),
                                    shape = RoundedCornerShape(50.dp),
                                    color = MaterialTheme.colorScheme.secondaryContainer
                                ) {
                                    sIcon(modifier = Modifier.padding(19.dp),
                                        image_vector_id = R.drawable.rounded_group_24
                                    )
                                }
                            },

                            /**   D A T O S   D E L   C L I E N T E   */
                            content2 = {
                                VLayout3P(
                                    content1 = {
                                        xTextTitle(text = item.nombre_apellido)
                                    },
                                    content2 = {
                                        xTextBody(text = item.identificacion_pk)
                                    },
                                    content3 = {
                                        xTextBody(text = item.telefono)
                                    },
                                )
                            },
                            content3 = {

                            },
                        )
                    }
                }
            }
        },
        top_app_bar_navigation_back_icon = R.drawable.rounded_keyboard_backspace_24,
        floatingActionButton_onClick = {
            onClick_agregar_empleado()
        },
        floatingActionButton_image_vector_id = R.drawable.rounded_add_24,
    )
}
