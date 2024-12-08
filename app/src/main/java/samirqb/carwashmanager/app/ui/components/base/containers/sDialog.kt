package samirqb.carwashmanager.app.ui.components.base.containers

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun sDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit
    ){
    Dialog (
        onDismissRequest = onDismissRequest,
        properties = properties,
        content = content
    )
}
