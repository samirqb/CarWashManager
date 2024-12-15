package samirqb.lib.helpers

import java.text.NumberFormat
import java.util.Locale

class FormatearTexto {

    private val mMyRegex = MyRegex()

    fun decimalFormater(string:String):String{
        val match = mMyRegex.dosDecimalesOpcionalesRegex.find(string)
        return match?.value ?: "0.00"
    }

    fun puntuacionMilesYDosDecimalesFormater(input: String):String{

        val match = mMyRegex.dosDecimalesSiempreRegex.find(input)
        val number = match?.value?.toDoubleOrNull() ?: return input

        val formatter = NumberFormat.getNumberInstance(Locale.US).apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 2
        }

        return formatter.format(number)
    }
}