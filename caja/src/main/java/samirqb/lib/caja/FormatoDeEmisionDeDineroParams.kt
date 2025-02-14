package samirqb.lib.caja

class FormatoDeEmisionDeDineroParams(
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
