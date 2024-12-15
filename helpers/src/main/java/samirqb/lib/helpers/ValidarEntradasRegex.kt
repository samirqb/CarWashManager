package samirqb.lib.helpers

class ValidarEntradasRegex {

    private val mMyRegex = MyRegex()

    fun validarNumeros(string:String):Boolean{
        return mMyRegex.valoresNumericosValidos.matches(string)
    }



}