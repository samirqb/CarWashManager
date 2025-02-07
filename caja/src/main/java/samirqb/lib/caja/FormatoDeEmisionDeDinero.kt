package samirqb.lib.caja

class FormatoDeEmisionDeDinero(
    private val _forma_de_emision_de_dinero: List<String> = listOf(
        "BILLETE",
        "DIGITAL",
        "MONEDA",
    )
) {
    fun obtener(): List<String>{
        return _forma_de_emision_de_dinero
    }
}
