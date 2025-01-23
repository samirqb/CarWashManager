package samirqb.carwashmanager.app.ui.components.custom.old

import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import samirqb.carwashmanager.app.R

class WidgetEjemplo {

    @Composable
    fun iconoDelWidget( icon_id: Int = R.drawable.rounded_server_person_24 ) {
        Surface {
            Icon(
                imageVector = ImageVector.vectorResource(id = icon_id),
                contentDescription = null
            )
        }
    }

    @Composable
    fun textoWidget(texto: String =  "Hola Mundo"){
        Text(text = texto)
    }
}