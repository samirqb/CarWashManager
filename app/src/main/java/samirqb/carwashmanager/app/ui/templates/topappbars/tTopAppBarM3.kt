package samirqb.carwashmanager.app.ui.templates.topappbars

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import samirqb.carwashmanager.app.ui.components.base.containers.sTopAppBar
import samirqb.carwashmanager.app.ui.components.base.inputs.sIconButton
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextHeadLine
import samirqb.carwashmanager.app.ui.templates.topappbars.config.miCustomSetTopAppBarColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun tTopAppBarM3(
    top_app_bar_title: Int,
    top_app_bar_navigation_back: () -> Unit,
    top_app_bar_navigation_back_icon: Int,
    top_app_bar_color: TopAppBarColors = miCustomSetTopAppBarColors(),
    top_app_bar_modifier: Modifier = Modifier,
    ) {
    sTopAppBar(
        modifier = top_app_bar_modifier,
        title = { xTextHeadLine(text = stringResource(id = top_app_bar_title)) },
        colors = top_app_bar_color,
        navigationIcon = {
            sIconButton(
                onClick = top_app_bar_navigation_back,
            ) {
                sIcon(image_vector_id = top_app_bar_navigation_back_icon )
            }
        },
    )
}