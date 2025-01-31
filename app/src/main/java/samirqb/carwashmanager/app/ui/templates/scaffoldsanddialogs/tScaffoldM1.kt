package samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.ui.components.base.containers.sScaffold
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout1P
import samirqb.carwashmanager.app.ui.templates.topappbars.tTopAppBarM2


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun tScaffoldM1(
    top_app_bar_title:Int,
    top_app_bar_subtitle:Int,
    content1:  @Composable() (ColumnScope.() -> Unit),
    top_app_bar_action_button_icon_1: Int,
    top_app_bar_action_button_onClick1: () -> Unit,
    onClick_dropdownmenuitem_admin_moneda: () -> Unit,
    onClick_dropdownmenuitem_admin_servicios: () -> Unit,
    onClick_dropdownmenuitem_admin_empleados: () -> Unit,
    onClick_dropdownmenuitem_admin_categorias: () -> Unit,
    onClick_dropdownmenuitem_admin_clientes: () -> Unit,
    onClick_dropdownmenuitem_admin_vehiculos: () -> Unit,
    top_app_bar_action_button_icon_2: Int,
    top_app_bar_action_button_onClick2: () -> Unit,
    modifier: Modifier = Modifier,
) {
    sScaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            tTopAppBarM2(
                top_app_bar_modifier = Modifier.fillMaxWidth(),
                top_app_bar_title = top_app_bar_title,
                top_app_bar_subtitle = top_app_bar_subtitle,
                top_app_bar_action_button_icon_1 = top_app_bar_action_button_icon_1,
                top_app_bar_action_button_onClick1 = top_app_bar_action_button_onClick1,
                top_app_bar_action_button_icon_2 = top_app_bar_action_button_icon_2,
                top_app_bar_action_button_onClick2 = top_app_bar_action_button_onClick2,
                onClick_dropdownmenuitem_admin_moneda = onClick_dropdownmenuitem_admin_moneda,
                onClick_dropdownmenuitem_admin_servicios = onClick_dropdownmenuitem_admin_servicios,
                onClick_dropdownmenuitem_admin_empleados = onClick_dropdownmenuitem_admin_empleados,
                onClick_dropdownmenuitem_admin_categorias = onClick_dropdownmenuitem_admin_categorias,
                onClick_dropdownmenuitem_admin_clientes = onClick_dropdownmenuitem_admin_clientes,
                onClick_dropdownmenuitem_admin_vehiculos = onClick_dropdownmenuitem_admin_vehiculos,
            )
        },
        floatingActionButton = {
            /*
            tFloatingActionButton(
                onClick = floatingActionButton_onClick,
                image_vector_id = floatingActionButton_image_vector_id,
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            )
            */
        },
        //content = content,
    ){
        VLayout1P(
            modifier = Modifier.padding(it).padding(9.dp),
            content1 = { content1() },
        )
    }
}
