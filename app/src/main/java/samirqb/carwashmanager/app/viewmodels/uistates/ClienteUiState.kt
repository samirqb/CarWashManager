package samirqb.carwashmanager.app.viewmodels.uistates

import samirqb.lib.personas.entities.ClienteEntity

data class ClienteUiState(
    val resultado_busqueda_cliente: ClienteEntity? = null,
    val todos_los_clientes: List<ClienteEntity> = listOf(),
    val fecha_y_hora: String = "",
)