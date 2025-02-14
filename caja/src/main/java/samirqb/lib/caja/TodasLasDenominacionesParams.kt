package samirqb.lib.caja

class TodasLasDenominacionesParams(
    private val _denominaciones: List<Float> = listOf(
        0.01F,
        0.02F,
        0.05F,
        0.10F,
        0.20F,
        0.25F,
        0.50F,
        1.00F,
        2.00F,
        3.00F,
        5.00F,
        10.00F,
        20.00F,
        25.00F,
        50.00F,
        100.00F,
        200.00F,
        500.00F,
        1000.00F,
        2000.00F,
        5000.00F,
        10000.00F,
        20000.00F,
        50000.00F,
        100000.00F,
    )
) {
    fun obtener(): List<Float>{
        return _denominaciones
    }
}