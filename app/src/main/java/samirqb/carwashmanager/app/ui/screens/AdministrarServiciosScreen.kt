package samirqb.carwashmanager.app.ui.screens

import androidx.compose.runtime.Composable
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tScaffoldM3
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tScaffoldM4

@Composable
fun AdministrarServiciosScreen(
    onClick_agregar_servicio:()->Unit,
    onClick_navigate_back:()->Unit,
    onClick_crear_servicio:()->Unit,
) {
    tScaffoldM4(
        top_app_bar_title = R.string.txt_body_menu_servicios,
        top_app_bar_navigation_back = { onClick_navigate_back() },
        top_app_bar_navigation_back_icon = R.drawable.rounded_keyboard_backspace_24,
        top_app_bar_action_button_icon_1 = R.drawable.rounded_design_services_24,
        top_app_bar_action_button_onClick1 = { onClick_crear_servicio() },
        content1 = {},
        floatingActionButton_onClick = { onClick_agregar_servicio() },
        floatingActionButton_image_vector_id = R.drawable.rounded_add_24,
    )
}
