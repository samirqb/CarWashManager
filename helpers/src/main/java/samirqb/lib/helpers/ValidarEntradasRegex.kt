package samirqb.lib.helpers

class ValidarEntradasRegex {

    private val mMyRegex = MyRegex()

    fun validarNumerosDecimales(string:String):Boolean{
        return mMyRegex.valoresNumericosDecimalesValidos.matches(string)
    }

    fun validarNumerosEnteros(string:String):Boolean{
        return mMyRegex.valoresNumericosEnterosValidos.matches(string)
    }

    fun validarAlfanumericos(string:String):Boolean{
        return mMyRegex.valoresAlfaNumericosValidos.matches(string)
    }

}