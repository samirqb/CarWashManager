package samirqb.lib.helpers

data class MyRegex(
    val valoresNumericosValidos:Regex = Regex("[0-9.]+"),
    val dosDecimalesOpcionalesRegex:Regex = Regex("\\d+\\.\\d{0,2}"),
    val dosDecimalesOpcionalesV2Regex:Regex = Regex("\\d{0,2}\\.\\d{0,2}"),
    val dosDecimalesSiempreRegex:Regex = Regex("(\\d+)(\\.\\d{0,9})?"),
    val dosDecimalesSiempreV2Regex:Regex = Regex("(\\d+){1,9}(\\.\\d{1,2})?"),
    val valoresTextoValidos:Regex = Regex("[a-zA-Z]+"),
    val valoresAlfaNumericosValidos:Regex = Regex("[a-zA-Z0-9]+"),
)