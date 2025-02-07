package samirqb.carwashmanager.app

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import samirqb.carwashmanager.app.nav.MyAppNavHost
import samirqb.carwashmanager.app.ui.theme.CarWashManagerTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarWashManagerTheme {
                MyAppNavHost()
            }
        }
    }
}
