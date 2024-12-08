package samirqb.carwashmanager.app.ui.dialogs

import androidx.compose.runtime.Composable
import samirqb.carwashmanager.app.ui.components.base.containers.sDialog
import samirqb.carwashmanager.app.ui.components.custom.old.viewcontents.ListaClientesViewContent

@Composable
fun ListaClientesDialog() {
    sDialog(
        onDismissRequest = {}
    ) {
        ListaClientesViewContent()
    }
}