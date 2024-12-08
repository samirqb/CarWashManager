package samirqb.carwashmanager.app.ui.screens

import androidx.compose.runtime.Composable
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tScaffoldM3

@Composable
fun AdministrarEmpleadosScreen(
    onClick_agregar_empleado:()->Unit,
    onClick_navigate_back:()->Unit,
) {
    tScaffoldM3(
        top_app_bar_title = R.string.txt_body_menu_empleados,
        top_app_bar_navigation_back = {
            onClick_navigate_back()
        },
        content1 = {},
        top_app_bar_navigation_back_icon = R.drawable.rounded_keyboard_backspace_24,
        floatingActionButton_onClick = {
            onClick_agregar_empleado()
        },
        floatingActionButton_image_vector_id = R.drawable.rounded_add_24,
    )
}
