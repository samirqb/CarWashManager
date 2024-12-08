package samirqb.carwashmanager.app.ui.components.custom.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import samirqb.carwashmanager.app.ui.components.base.layouts.sColumn

@Composable
fun VLayout1P(
    content1: @Composable () (ColumnScope.()-> Unit),
    modifier: Modifier = Modifier,
    horizontalArrangement: Alignment.Horizontal = Alignment.CenterHorizontally,
    verticalAlignment: Arrangement.Vertical = Arrangement.Center,
){
    sColumn(
        modifier = modifier,
        horizontalAlignment = horizontalArrangement,
        verticalArrangement = verticalAlignment,
        content = {
            content1()
        }
    )
}