package samirqb.carwashmanager.app.ui.components.custom.old.viewcontents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout3P

@Composable
fun CantidadPorDenominacionViewContent(
    content1: @Composable() (ColumnScope.() -> Unit),
    content2: @Composable() (ColumnScope.() -> Unit),
    content3: @Composable() (ColumnScope.() -> Unit),
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(1.dp),
) {
    VLayout3P(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        content1 = { content1() },
        content2 = { content2() },
        content3 = { content3() },
    )
}