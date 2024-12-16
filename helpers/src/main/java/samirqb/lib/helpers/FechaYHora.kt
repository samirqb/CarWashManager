package samirqb.lib.helpers

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

class FechaYHora {

    @RequiresApi(Build.VERSION_CODES.O)
    fun now():String{
        return LocalDateTime.now().toString()
    }
}