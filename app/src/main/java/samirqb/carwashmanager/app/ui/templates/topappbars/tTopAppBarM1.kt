package samirqb.carwashmanager.app.ui.templates.topappbars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon
import samirqb.carwashmanager.app.ui.components.base.inputs.sIconButton
import samirqb.carwashmanager.app.ui.components.base.containers.sTopAppBar
import samirqb.carwashmanager.app.ui.templates.topappbars.config.miCustomSetTopAppBarColors
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextHeadLine
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.lcarwashmanager.app.ui.layoutcomponets.VLayout2P


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun tTopAppBarM1(
    top_app_bar_title: String,
    top_app_bar_subtitle: String,
    top_app_bar_action_button_icon_1: Int,
    top_app_bar_action_button_onClick1: () -> Unit,
    top_app_bar_modifier: Modifier = Modifier,
    top_app_bar_navigationIcon: @Composable () -> Unit = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    top_app_bar_colors: TopAppBarColors = miCustomSetTopAppBarColors(),
    top_app_bar_scrollBehavior: TopAppBarScrollBehavior? = null

) {
    sTopAppBar(
        title = {
            VLayout2P(
                content1 = { xTextHeadLine(text = top_app_bar_title) },
                content2 = { xTextLabel(text = top_app_bar_subtitle) },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            )
        },
        modifier = top_app_bar_modifier,
        navigationIcon = top_app_bar_navigationIcon,
        actions = {
            sIconButton(
                onClick = top_app_bar_action_button_onClick1,
                content = {
                    sIcon(image_vector_id = top_app_bar_action_button_icon_1)
                }
            )
        },
        windowInsets = windowInsets,
        colors = top_app_bar_colors,
        scrollBehavior = top_app_bar_scrollBehavior,
    )
}