package samirqb.carwashmanager.app.ui.components.custom.old.viewcontents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout2P
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout3P
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextTitle
import samirqb.lcarwashmanager.app.ui.layoutcomponets.VLayout2P
import samirqb.lib.caja.entidades.MonedaEntity

@Composable
fun CantidadPorDenominacionViewContent(
    image_vector_id:Int,
    denominacion_fk:String,
    codigo_iso_4217_fk:String,
    tipo_fk:String,
    //modifier: Modifier = Modifier,
    //verticalArrangement: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(1.dp),
) {
    sSurface(
        shape = RoundedCornerShape(13.dp)
    ) {
        HLayout2P(
            content1 = {
                sIcon(
                    image_vector_id = image_vector_id
                )
            },
            content2 = {
                VLayout2P(
                    content1 = {
                        xTextTitle(
                            text = "$${denominacion_fk} ${codigo_iso_4217_fk}"
                        )
                    },
                    content2 = { xTextLabel(text = "${tipo_fk}") },
                )
            },
        )
    }
}