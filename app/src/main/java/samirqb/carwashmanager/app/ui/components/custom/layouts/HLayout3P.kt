package samirqb.carwashmanager.app.ui.components.custom.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import samirqb.carwashmanager.app.ui.components.base.layouts.sRow

@Composable
fun HLayout3P(
    content1:  @Composable() (RowScope.() -> Unit),
    content2: @Composable () (RowScope.() -> Unit),
    content3: @Composable () (RowScope.() -> Unit),
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
){
    sRow(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
        content = {
            content1()
            content2()
            content3()
        }
    )
}