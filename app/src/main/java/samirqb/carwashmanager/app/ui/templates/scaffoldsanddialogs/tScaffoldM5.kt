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
import samirqb.carwashmanager.app.ui.templates.topappbars.tTopAppBarM3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun tScaffoldM5(
    top_app_bar_title:Int,
    top_app_bar_navigation_back: () -> Unit,
    top_app_bar_navigation_back_icon:Int,
    content1:  @Composable() (ColumnScope.() -> Unit),
    //floatingActionButton_onClick: () -> Unit,
    //floatingActionButton_image_vector_id: Int,
    modifier: Modifier = Modifier,
) {
    sScaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            tTopAppBarM3(
                top_app_bar_modifier = Modifier.fillMaxWidth(),
                top_app_bar_title = top_app_bar_title,
                top_app_bar_navigation_back = top_app_bar_navigation_back,
                top_app_bar_navigation_back_icon = top_app_bar_navigation_back_icon,
            )
        },
        /*
        floatingActionButton = {
            tFloatingActionButton(
                onClick = floatingActionButton_onClick,
                image_vector_id = floatingActionButton_image_vector_id,
            )
        },
        */
        //content = content,
    ){

        VLayout1P(
            modifier = Modifier.padding(it).padding(9.dp),
            content1 = { content1() },
        )
    }
}
