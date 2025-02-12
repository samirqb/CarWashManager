package samirqb.carwashmanager.app.viewmodels.uistates

import androidx.compose.runtime.mutableStateListOf
import samirqb.lib.personas.entities.ClienteEntity

data class ClienteUiState(
    val cliente_id_value_buscado: String = "",
    val resultado_busqueda_cliente: ClienteEntity? = null,
    val todos_los_clientes: MutableList<ClienteEntity> = mutableStateListOf(),
    val fecha_y_hora: String = "",
)