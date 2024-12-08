package samirqb.carwashmanager.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import samirqb.carwashmanager.app.nav.MyAppNavHost
import samirqb.carwashmanager.app.ui.components.custom.widgets.SeleccionarVehiculoCategoriaWidget
import samirqb.carwashmanager.app.ui.dialogs.AgregarMonedaDialog
import samirqb.carwashmanager.app.ui.dialogs.AperturaCajaCantidadesPorDenominacionDialog
import samirqb.carwashmanager.app.ui.screens.AdministrarMonedaScreen
import samirqb.carwashmanager.app.ui.theme.CarWashManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarWashManagerTheme {
                MyAppNavHost()


                //AgregarMonedaDialog()
                /*
                SeleccionarVehiculoCategoriaWidget(
                    lista_categorias = listOf("categoria_1","categoria_2","categoria_3")
                )
                */
            }
        }
    }
}
