package samirqb.lib.vehiculos

class ClasificacionesDeVehiculosParams {

    private val _clasificaciones: List<String> = listOf(
        "ARTICULADO",
        "AUTOMOVIL",
        "BICICLETA",
        "BUS",
        "BUSETA",
        "CAMION",
        "CAMIONETA",
        "CAMPERO",
        "CUATRIMOTO",
        "MICROBUS",
        "MOTOCARRO",
        "MOTOCICLETA",
        "MOTOTRICICLO",
        "SUV",
        "TRACTOCAMION",
        "VAN",
        "VOLQUETA"
    )

    fun obtener(): List<String>{
        return _clasificaciones.sorted()
    }
}