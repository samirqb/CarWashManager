package samirqb.lib.helpers

class SumaValoresDeItemsDeUnaLista {

    fun sumar(lista:List<Int> ):Int {

        var total_suma = 0

        total_suma = lista.sum()

        return total_suma
    }

    fun sumar(lista:List<Float> ):Float {

        var total_suma = 0f

        total_suma = lista.sum()

        return total_suma
    }

}