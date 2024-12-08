package samirqb.carwashmanager.app.ui.components.custom.old.viewcontents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sCard
import samirqb.carwashmanager.app.ui.components.base.layouts.sLazyColumn
import samirqb.carwashmanager.app.ui.components.custom.widgets.ClienteResumenCardWidget

@Composable
fun ListaClientesViewContent() {

    var lista_clientes = listOf("BKK601", "TLO009", "JJP123")

    sCard(
        modifier = Modifier.fillMaxWidth().size(555.dp)
    ) {
        sLazyColumn(
            modifier = Modifier.padding(9.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            itemsIndexed(lista_clientes){   index, cliente ->
                ClienteResumenCardWidget(
                    icon_id = R.drawable.rounded_airport_shuttle_24,
                    matricula_vehiculo = cliente,
                    categoria_vehiculo = index.toString()
                )
            }
        }
    }
}