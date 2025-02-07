package samirqb.lib.caja

class UnidadesMonetariasParams(
    private val _unidades_monetarias: Map<String, String> = mapOf(
        "ARS" to "ARGENTINE PESO",
        "BOB" to "BOLIVIANO",
        "BRL" to "BRAZILIAN REAL",
        "CLP" to "CHILEAN PESO",
        "COP" to "COLOMBIAN PESO",
        "CRC" to "COSTA RICAN COLON",
        "CUP" to "CUBAN PESO",
        "DOP" to "DOMINICAN PESO",
        "EUR" to "EURO",
        "GTQ" to "QUETZAL",
        "GYD" to "GUYANA DOLLAR",
        "HNL" to "LEMPIRA",
        "MXN" to "MEXICAN PESO",
        "NIO" to "CORDOBA ORO",
        "PAB" to "BALBOA",
        "PEN" to "SOL",
        "PYG" to "GUARANI",
        "SVC" to "EL SALVADOR COLON",
        "USD" to "US DOLLAR",
        "UYU" to "PESO URUGUAYO",
        "VED" to "BOLÍVAR SOBERANO",
        "VES" to "BOLÍVAR SOBERANO",
        "XXX" to "OTRA MONEDA",
    )
) {
    fun obtener():Map<String, String>{
        return _unidades_monetarias
    }
}