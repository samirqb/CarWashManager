package samirqb.carwashmanager.app.ui.templates.topappbars

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon
import samirqb.carwashmanager.app.ui.components.base.inputs.sIconButton
import samirqb.carwashmanager.app.ui.components.base.containers.sTopAppBar
import samirqb.carwashmanager.app.ui.templates.topappbars.config.miCustomSetTopAppBarColors
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextHeadLine
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.carwashmanager.app.ui.templates.dropdowns.tTopAppBarMenuM1
import samirqb.lcarwashmanager.app.ui.layoutcomponets.VLayout2P

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun tTopAppBarM2(
    top_app_bar_title: Int,
    top_app_bar_subtitle: Int,
    top_app_bar_action_button_icon_1: Int,
    top_app_bar_action_button_onClick1: () -> Unit,
    top_app_bar_action_button_icon_2: Int,
    top_app_bar_action_button_onClick2: () -> Unit,
    onClick_dropdownmenuitem_admin_moneda: () -> Unit,
    onClick_dropdownmenuitem_admin_servicios: () -> Unit,
    onClick_dropdownmenuitem_admin_empleados: () -> Unit,
    onClick_dropdownmenuitem_admin_categorias: () -> Unit,
    onClick_dropdownmenuitem_admin_clientes: () -> Unit,
    top_app_bar_modifier: Modifier = Modifier,
    top_app_bar_navigationIcon: @Composable () -> Unit = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    top_app_bar_colors: TopAppBarColors = miCustomSetTopAppBarColors(),
    top_app_bar_scrollBehavior: TopAppBarScrollBehavior? = null

) {
   sTopAppBar(
        title = {
            VLayout2P(
                content1 = { xTextHeadLine(text = stringResource(id = top_app_bar_title)) },
                content2 = { xTextLabel(text = stringResource(id = top_app_bar_subtitle)) },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            )
        },
        modifier = top_app_bar_modifier,
        navigationIcon = top_app_bar_navigationIcon,
        actions = {

            sIconButton(
                onClick = top_app_bar_action_button_onClick2,
                content = {
                    sIcon(image_vector_id = top_app_bar_action_button_icon_2)
                }
            )

            /*
            sIconButton(
                onClick = { top_app_bar_action_button_onClick1() },
                content = {
                    sIcon(image_vector_id = top_app_bar_action_button_icon_1)
                }
            )
            */

            tTopAppBarMenuM1(
                onClick_dropdownmenuitem_admin_moneda = onClick_dropdownmenuitem_admin_moneda,
                onClick_dropdownmenuitem_admin_servicios = onClick_dropdownmenuitem_admin_servicios,
                onClick_dropdownmenuitem_admin_empleados = onClick_dropdownmenuitem_admin_empleados,
                onClick_dropdownmenuitem_admin_categorias = onClick_dropdownmenuitem_admin_categorias,
                onClick_dropdownmenuitem_admin_clientes = onClick_dropdownmenuitem_admin_clientes,
            )
        },
        windowInsets = windowInsets,
        colors = top_app_bar_colors,
        scrollBehavior = top_app_bar_scrollBehavior,
    )
}