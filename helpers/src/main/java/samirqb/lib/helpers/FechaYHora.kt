package samirqb.lib.helpers

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FechaYHora {

    @RequiresApi(Build.VERSION_CODES.O)
    private var localDateTime: LocalDateTime = LocalDateTime.now()

    @RequiresApi(Build.VERSION_CODES.O)
    fun now(){
        localDateTime = LocalDateTime.now()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDateTime():String{
        return localDateTime.format(
            DateTimeFormatter.ISO_LOCAL_DATE_TIME
        ).toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDate():String{
        return localDateTime.format(
            DateTimeFormatter.ISO_LOCAL_DATE
        ).toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTime():String{
        return localDateTime.format(
            DateTimeFormatter.ISO_LOCAL_TIME
        ).toString()
    }
}