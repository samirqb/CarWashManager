package samirqb.lib.vehiculos

class MarcasDeVehiculosParams (
    private val _marcas: List<String> = listOf(
        "ACURA",
        "ALFA ROMEO",
        "ASTON MARTIN",
        "AUDI",
        "BENTLEY",
        "BMW",
        "BUGATTI",
        "BUICK",
        "CADILLAC",
        "CHEVROLET",
        "CHRYSLER",
        "CITROEN",
        "DODGE",
        "FERRARI",
        "FIAT",
        "FORD",
        "GENESIS",
        "GMC",
        "HONDA",
        "HYUNDAI",
        "INFINITI",
        "JAGUAR",
        "JEEP",
        "KIA",
        "LADA",
        "LAMBORGHINI",
        "LAND ROVER",
        "LEXUS",
        "LINCOLN",
        "MASERATI",
        "MAZDA",
        "MCLAREN",
        "MERCEDES-BENZ",
        "MINI",
        "MITSUBISHI",
        "NISSAN",
        "PAGANI",
        "PEUGEOT",
        "PORSCHE",
        "RAM",
        "RENAULT",
        "ROLLS-ROYCE",
        "SEAT",
        "SUBARU",
        "SUZUKI",
        "TESLA",
        "TOYOTA",
        "VOLKSWAGEN",
        "VOLVO"
    )
){
    fun obtener(): List<String>{
        return _marcas.sorted()
    }
}