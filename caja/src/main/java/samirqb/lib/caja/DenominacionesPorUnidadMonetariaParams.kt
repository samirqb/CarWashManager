package samirqb.lib.caja

class DenominacionesPorUnidadMonetariaParams(
    //rev-ini
    private val _denominaciones_ars: List<List<String>> = listOf(
        listOf("USD", "0.01F", "MONEDA"),
        listOf("USD", "0.05F", "MONEDA"),
        listOf("USD", "0.10F", "MONEDA"),
        listOf("USD", "0.25F", "MONEDA"),
        listOf("USD", "0.50F", "MONEDA"),
        listOf("ARS", "1.00F", "MONEDA"),
        listOf("ARS", "2.00F", "MONEDA"),
        listOf("ARS", "5.00F", "MONEDA"),
        listOf("ARS", "10.00F", "MONEDA"),
        listOf("ARS", "10.00F", "BILLETE"),
        listOf("ARS", "20.00F", "BILLETE"),
        listOf("ARS", "50.00F", "BILLETE"),
        listOf("ARS", "100.00F", "BILLETE"),
        listOf("ARS", "200.00F", "BILLETE"),
        listOf("ARS", "500.00F", "BILLETE"),
        listOf("ARS", "1000.00F", "BILLETE")
    ),
    private val _denominaciones_bob: List<List<String>> = listOf(
        listOf("BOB", "0.10F", "MONEDA"),
        listOf("BOB", "0.20F", "MONEDA"),
        listOf("BOB", "0.50F", "MONEDA"),
        listOf("BOB", "1.00F", "MONEDA"),
        listOf("BOB", "2.00F", "MONEDA"),
        listOf("BOB", "5.00F", "MONEDA"),
        listOf("BOB", "10.00F", "BILLETE"),
        listOf("BOB", "20.00F", "BILLETE"),
        listOf("BOB", "50.00F", "BILLETE"),
        listOf("BOB", "100.00F", "BILLETE"),
        listOf("BOB", "200.00F", "BILLETE")
    ),
    private val _denominaciones_brl: List<List<String>> = listOf(
        listOf("BRL", "0.01F", "MONEDA"),
        listOf("BRL", "0.05F", "MONEDA"),
        listOf("BRL", "0.10F", "MONEDA"),
        listOf("BRL", "0.25F", "MONEDA"),
        listOf("BRL", "0.50F", "MONEDA"),
        listOf("BRL", "1.00F", "MONEDA"),
        listOf("BRL", "2.00F", "BILLETE"),
        listOf("BRL", "5.00F", "BILLETE"),
        listOf("BRL", "10.00F", "BILLETE"),
        listOf("BRL", "20.00F", "BILLETE"),
        listOf("BRL", "50.00F", "BILLETE"),
        listOf("BRL", "100.00F", "BILLETE"),
        listOf("BRL", "200.00F", "BILLETE"),
    ),
    private val _denominaciones_clp: List<List<String>> = listOf(
        listOf("CLP", "1.00F", "MONEDA"),
        listOf("CLP", "5.00F", "MONEDA"),
        listOf("CLP", "10.00F", "MONEDA"),
        listOf("CLP", "50.00F", "MONEDA"),
        listOf("CLP", "100.00F", "MONEDA"),
        listOf("CLP", "500.00F", "MONEDA"),
        listOf("CLP", "1000.00F", "BILLETE"),
        listOf("CLP", "2000.00F", "BILLETE"),
        listOf("CLP", "5000.00F", "BILLETE"),
        listOf("CLP", "10000.00F", "BILLETE"),
        listOf("CLP", "20000.00F", "BILLETE")
    ),
    private val _denominaciones_cop: List<List<String>> = listOf(
        listOf("COP", "50.00F", "MONEDA"),
        listOf("COP", "100.00F", "MONEDA"),
        listOf("COP", "200.00F", "MONEDA"),
        listOf("COP", "500.00F", "MONEDA"),
        listOf("COP", "1000.00F", "MONEDA"),
        listOf("COP", "2000.00F", "BILLETE"),
        listOf("COP", "5000.00F", "BILLETE"),
        listOf("COP", "10000.00F", "BILLETE"),
        listOf("COP", "20000.00F", "BILLETE"),
        listOf("COP", "50000.00F", "BILLETE"),
        listOf("COP", "100000.00F", "BILLETE")
    ),
    private val _denominaciones_crc: List<List<String>> = listOf(
        listOf("CRC", "5.00F", "MONEDA"),
        listOf("CRC", "10.00F", "MONEDA"),
        listOf("CRC", "25.00F", "MONEDA"),
        listOf("CRC", "50.00F", "MONEDA"),
        listOf("CRC", "100.00F", "MONEDA"),
        listOf("CRC", "500.00F", "MONEDA"),
        listOf("CRC", "1000.00F", "BILLETE"),
        listOf("CRC", "2000.00F", "BILLETE"),
        listOf("CRC", "5000.00F", "BILLETE"),
        listOf("CRC", "10000.00F", "BILLETE"),
        listOf("CRC", "20000.00F", "BILLETE"),
        listOf("CRC", "50000.00F", "BILLETE")
    ),
    private val _denominaciones_cup: List<List<String>> = listOf(
        listOf("CUP", "0.01F", "MONEDA"),
        listOf("CUP", "0.02F", "MONEDA"),
        listOf("CUP", "0.05F", "MONEDA"),
        listOf("CUP", "0.20F", "MONEDA"),
        listOf("CUP", "1.00F", "MONEDA"),
        listOf("CUP", "3.00F", "MONEDA"),
        listOf("CUP", "5.00F", "MONEDA"),
        listOf("CUP", "1.00F", "BILLETE"),
        listOf("CUP", "3.00F", "BILLETE"),
        listOf("CUP", "5.00F", "BILLETE"),
        listOf("CUP", "10.00F", "BILLETE"),
        listOf("CUP", "20.00F", "BILLETE"),
        listOf("CUP", "50.00F", "BILLETE"),
        listOf("CUP", "100.00F", "BILLETE"),
        listOf("CUP", "200.00F", "BILLETE"),
        listOf("CUP", "500.00F", "BILLETE"),
        listOf("CUP", "1000.00F", "BILLETE"),
    ),
    //rev-fin
    private val _denominaciones_dop: List<List<String>> = listOf(
        listOf("DOP", "1.00F", "MONEDA"),
        listOf("DOP", "5.00F", "MONEDA"),
        listOf("DOP", "10.00F", "MONEDA"),
        listOf("DOP", "25.00F", "MONEDA"),
        listOf("DOP", "50.00F", "MONEDA"),
        listOf("DOP", "100.00F", "MONEDA"),
        listOf("DOP", "200.00F", "BILLETE"),
        listOf("DOP", "500.00F", "BILLETE"),
        listOf("DOP", "1000.00F", "BILLETE"),
        listOf("DOP", "2000.00F", "BILLETE")
    ),
    private val _denominaciones_eur: List<List<String>> = listOf(
        listOf("EUR", "0.01F", "MONEDA"),
        listOf("EUR", "0.02F", "MONEDA"),
        listOf("EUR", "0.05F", "MONEDA"),
        listOf("EUR", "0.10F", "MONEDA"),
        listOf("EUR", "0.20F", "MONEDA"),
        listOf("EUR", "0.50F", "MONEDA"),
        listOf("EUR", "1.00F", "MONEDA"),
        listOf("EUR", "2.00F", "MONEDA"),
        listOf("EUR", "5.00F", "BILLETE"),
        listOf("EUR", "10.00F", "BILLETE"),
        listOf("EUR", "20.00F", "BILLETE"),
        listOf("EUR", "50.00F", "BILLETE"),
        listOf("EUR", "100.00F", "BILLETE"),
        listOf("EUR", "200.00F", "BILLETE"),
        listOf("EUR", "500.00F", "BILLETE")
    ),
    private val _denominaciones_gtq: List<List<String>> = listOf(
        listOf("GTQ", "1.00F", "MONEDA"),
        listOf("GTQ", "5.00F", "MONEDA"),
        listOf("GTQ", "10.00F", "MONEDA"),
        listOf("GTQ", "25.00F", "MONEDA"),
        listOf("GTQ", "50.00F", "MONEDA"),
        listOf("GTQ", "1.00F", "BILLETE"),
        listOf("GTQ", "5.00F", "BILLETE"),
        listOf("GTQ", "10.00F", "BILLETE"),
        listOf("GTQ", "20.00F", "BILLETE"),
        listOf("GTQ", "50.00F", "BILLETE"),
        listOf("GTQ", "100.00F", "BILLETE")
    ),
    private val _denominaciones_gyd: List<List<String>> = listOf(
        listOf("GYD", "1.00F", "MONEDA"),
        listOf("GYD", "5.00F", "MONEDA"),
        listOf("GYD", "10.00F", "MONEDA"),
        listOf("GYD", "20.00F", "MONEDA"),
        listOf("GYD", "50.00F", "MONEDA"),
        listOf("GYD", "100.00F", "BILLETE"),
        listOf("GYD", "500.00F", "BILLETE"),
        listOf("GYD", "1000.00F", "BILLETE"),
        listOf("GYD", "5000.00F", "BILLETE")
    ),
    private val _denominaciones_hnl: List<List<String>> = listOf(
        listOf("HNL", "5.00F", "MONEDA"),
        listOf("HNL", "10.00F", "MONEDA"),
        listOf("HNL", "20.00F", "MONEDA"),
        listOf("HNL", "50.00F", "MONEDA"),
        listOf("HNL", "1.00F", "BILLETE"),
        listOf("HNL", "2.00F", "BILLETE"),
        listOf("HNL", "5.00F", "BILLETE"),
        listOf("HNL", "10.00F", "BILLETE"),
        listOf("HNL", "20.00F", "BILLETE"),
        listOf("HNL", "50.00F", "BILLETE"),
        listOf("HNL", "100.00F", "BILLETE"),
        listOf("HNL", "500.00F", "BILLETE")
    ),
    //rev-ini
    private val _denominaciones_mxn: List<List<String>> = listOf(
        listOf("MXN", "0.05F", "MONEDA"),
        listOf("MXN", "0.10F", "MONEDA"),
        listOf("MXN", "0.20F", "MONEDA"),
        listOf("MXN", "0.50F", "MONEDA"),
        listOf("MXN", "1.00F", "MONEDA"),
        listOf("MXN", "2.00F", "MONEDA"),
        listOf("MXN", "5.00F", "MONEDA"),
        listOf("MXN", "10.00F", "MONEDA"),
        listOf("MXN", "20.00F", "MONEDA"),
        listOf("MXN", "50.00F", "BILLETE"),
        listOf("MXN", "100.00F", "BILLETE"),
        listOf("MXN", "200.00F", "BILLETE"),
        listOf("MXN", "500.00F", "BILLETE"),
        listOf("MXN", "1000.00F", "BILLETE")
    ),
    //rev-fin
    private val _denominaciones_nio: List<List<String>> = listOf(
        listOf("NIO", "0.05F", "MONEDA"),
        listOf("NIO", "0.10F", "MONEDA"),
        listOf("NIO", "0.25F", "MONEDA"),
        listOf("NIO", "0.50F", "MONEDA"),
        listOf("NIO", "1.00F", "MONEDA"),
        listOf("NIO", "2.00F", "MONEDA"),
        listOf("NIO", "5.00F", "MONEDA"),
        listOf("NIO", "10.00F", "BILLETE"),
        listOf("NIO", "20.00F", "BILLETE"),
        listOf("NIO", "50.00F", "BILLETE"),
        listOf("NIO", "100.00F", "BILLETE"),
        listOf("NIO", "200.00F", "BILLETE")
    ),
    private val _denominaciones_pab: List<List<String>> = listOf(
        listOf("PAB", "0.01F", "MONEDA"),
        listOf("PAB", "0.05F", "MONEDA"),
        listOf("PAB", "0.10F", "MONEDA"),
        listOf("PAB", "0.25F", "MONEDA"),
        listOf("PAB", "0.50F", "MONEDA"),
        listOf("PAB", "1.00F", "MONEDA"),
        listOf("PAB", "2.00F", "MONEDA"),
        listOf("PAB", "5.00F", "MONEDA"),
        listOf("PAB", "10.00F", "BILLETE"),
        listOf("PAB", "20.00F", "BILLETE"),
        listOf("PAB", "50.00F", "BILLETE"),
        listOf("PAB", "100.00F", "BILLETE")
    ),
    private val _denominaciones_pen: List<List<String>> = listOf(
        listOf("PEN", "0.10F", "MONEDA"),
        listOf("PEN", "0.20F", "MONEDA"),
        listOf("PEN", "0.50F", "MONEDA"),
        listOf("PEN", "1.00F", "MONEDA"),
        listOf("PEN", "2.00F", "MONEDA"),
        listOf("PEN", "5.00F", "MONEDA"),
        listOf("PEN", "10.00F", "BILLETE"),
        listOf("PEN", "20.00F", "BILLETE"),
        listOf("PEN", "50.00F", "BILLETE"),
        listOf("PEN", "100.00F", "BILLETE"),
        listOf("PEN", "200.00F", "BILLETE")
    ),
    private val _denominaciones_pyg: List<List<String>> = listOf(
        listOf("PYG", "50.00F", "MONEDA"),
        listOf("PYG", "100.00F", "MONEDA"),
        listOf("PYG", "500.00F", "MONEDA"),
        listOf("PYG", "1000.00F", "MONEDA"),
        listOf("PYG", "2000.00F", "MONEDA"),
        listOf("PYG", "5000.00F", "MONEDA"),
        listOf("PYG", "10000.00F", "BILLETE"),
        listOf("PYG", "20000.00F", "BILLETE"),
        listOf("PYG", "50000.00F", "BILLETE"),
        listOf("PYG", "100000.00F", "BILLETE")
    ),
    private val _denominaciones_svc: List<List<String>> = listOf(
        listOf("SVC", "1.00F", "MONEDA"),
        listOf("SVC", "5.00F", "MONEDA"),
        listOf("SVC", "10.00F", "MONEDA"),
        listOf("SVC", "25.00F", "MONEDA"),
        listOf("SVC", "50.00F", "MONEDA"),
        listOf("SVC", "1.00F", "BILLETE"),
        listOf("SVC", "2.00F", "BILLETE"),
        listOf("SVC", "5.00F", "BILLETE"),
        listOf("SVC", "10.00F", "BILLETE"),
        listOf("SVC", "25.00F", "BILLETE"),
        listOf("SVC", "50.00F", "BILLETE"),
        listOf("SVC", "100.00F", "BILLETE")
    ),
    //rev-ini
    private val _denominaciones_usd: List<List<String>> = listOf(
        listOf("USD", "0.01F", "MONEDA"),
        listOf("USD", "0.05F", "MONEDA"),
        listOf("USD", "0.10F", "MONEDA"),
        listOf("USD", "0.25F", "MONEDA"),
        listOf("USD", "0.50F", "MONEDA"),
        listOf("USD", "1.00F", "BILLETE"),
        listOf("USD", "2.00F", "BILLETE"),
        listOf("USD", "5.00F", "BILLETE"),
        listOf("USD", "10.00F", "BILLETE"),
        listOf("USD", "20.00F", "BILLETE"),
        listOf("USD", "50.00F", "BILLETE"),
        listOf("USD", "100.00F", "BILLETE")
    ),
    //rev-fin
    private val _denominaciones_uyu: List<List<String>> = listOf(
        listOf("UYU", "1.00F", "MONEDA"),
        listOf("UYU", "2.00F", "MONEDA"),
        listOf("UYU", "5.00F", "MONEDA"),
        listOf("UYU", "10.00F", "MONEDA"),
        listOf("UYU", "20.00F", "MONEDA"),
        listOf("UYU", "50.00F", "MONEDA"),
        listOf("UYU", "100.00F", "MONEDA"),
        listOf("UYU", "200.00F", "BILLETE"),
        listOf("UYU", "500.00F", "BILLETE"),
        listOf("UYU", "1000.00F", "BILLETE"),
        listOf("UYU", "2000.00F", "BILLETE")
    ),
    private val _denominaciones_ved: List<List<String>> = listOf(
        listOf("VED", "1.00F", "MONEDA"),
        listOf("VED", "2.00F", "MONEDA"),
        listOf("VED", "5.00F", "MONEDA"),
        listOf("VED", "10.00F", "BILLETE"),
        listOf("VED", "20.00F", "BILLETE"),
        listOf("VED", "50.00F", "BILLETE"),
        listOf("VED", "100.00F", "BILLETE")
    ),
    private val _denominaciones_ves: List<List<String>> = listOf(
        listOf("VES", "1.00F", "MONEDA"),
        listOf("VES", "2.00F", "MONEDA"),
        listOf("VES", "5.00F", "MONEDA"),
        listOf("VES", "10.00F", "BILLETE"),
        listOf("VES", "20.00F", "BILLETE"),
        listOf("VES", "50.00F", "BILLETE"),
        listOf("VES", "100.00F", "BILLETE")
    ),
) {
    fun obtener_ars(): List<List<String>> {return _denominaciones_ars}
    fun obtener_bob(): List<List<String>> {return _denominaciones_bob}
    fun obtener_brl(): List<List<String>> {return _denominaciones_brl}
    fun obtener_clp(): List<List<String>> {return _denominaciones_clp}
    fun obtener_cop(): List<List<String>> {return _denominaciones_cop}
    fun obtener_crc(): List<List<String>> {return _denominaciones_crc}
    fun obtener_cup(): List<List<String>> {return _denominaciones_cup}
    fun obtener_dop(): List<List<String>> {return _denominaciones_dop}
    fun obtener_eur(): List<List<String>> {return _denominaciones_eur}
    fun obtener_gtq(): List<List<String>> {return _denominaciones_gtq}
    fun obtener_gyd(): List<List<String>> {return _denominaciones_gyd}
    fun obtener_hnl(): List<List<String>> {return _denominaciones_hnl}
    fun obtener_mxn(): List<List<String>> {return _denominaciones_mxn}
    fun obtener_nio(): List<List<String>> {return _denominaciones_nio}
    fun obtener_pab(): List<List<String>> {return _denominaciones_pab}
    fun obtener_pen(): List<List<String>> {return _denominaciones_pen}
    fun obtener_pyg(): List<List<String>> {return _denominaciones_pyg}
    fun obtener_svc(): List<List<String>> {return _denominaciones_svc}
    fun obtener_usd(): List<List<String>> {return _denominaciones_usd}
    fun obtener_uyu(): List<List<String>> {return _denominaciones_uyu}
    fun obtener_ved(): List<List<String>> {return _denominaciones_ved}
    fun obtener_ves(): List<List<String>> {return _denominaciones_ves}
}