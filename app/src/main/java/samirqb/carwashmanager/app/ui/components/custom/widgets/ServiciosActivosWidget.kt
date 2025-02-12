package samirqb.carwashmanager.app.ui.components.custom.widgets

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.layouts.sLazyVerticalGrid
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextHeadLine
import samirqb.carwashmanager.app.ui.layoutcomponets.VLayout2P

@Composable
fun ServiciosActivosWidget(
    lista_servicios_activos: List<String>
) {

    var index by rememberSaveable { mutableStateOf(0) }

    sSurface(
        color = Color.Transparent,
    ) {
        VLayout2P(
            content1 = {
                Spacer( modifier = Modifier.size(21.dp) )
                sSurface(
                    modifier = Modifier.fillMaxWidth().size(39.dp),
                    color = Color.Transparent
                ) {
                    xTextHeadLine( text = stringResource( R.string.txt_headline_servicios_activos))
                }
            },
            content2 = {
                sLazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.padding(3.dp),
                    contentPadding = PaddingValues(7.dp)
                ){
                    itemsIndexed(lista_servicios_activos){ index, servicio_Activo ->
                        VehiculoEnPatioWidget(
                            modifier = Modifier.pointerInput(Unit) {
                                detectTapGestures(
                                    onTap = {
                                        Log.i("X_TAG","CLICK EN SERVICIO ACTIVO ${index}")
                                    }
                                )
                            },
                            txt_matricula_vehiculo = index.toString(),
                            txt_valor_servicio = servicio_Activo
                        )
                    }
                }
            },
        )
    }
}