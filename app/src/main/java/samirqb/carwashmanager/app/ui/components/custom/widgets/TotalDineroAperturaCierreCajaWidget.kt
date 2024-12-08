package samirqb.carwashmanager.app.ui.components.custom.widgets

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextDisplay
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.lcarwashmanager.app.ui.layoutcomponets.VLayout2P

@Composable
fun TotalDineroAperturaCierreCajaWidget(
    txt_body_suma_total_denominaciones: String,
    modifier: Modifier,
) {
    sSurface(
        modifier = modifier,
        color =  MaterialTheme.colorScheme.secondaryContainer
    ) {
        VLayout2P(

            content1 = {
                sSurface {
                    xTextLabel(text = stringResource(id = R.string.txt_label_total_apertura))
                }
            },
            content2 = {
                sSurface {
                    xTextDisplay(text = txt_body_suma_total_denominaciones )
                }
            },
        )
    }
}