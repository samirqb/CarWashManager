package samirqb.carwashmanager.app.ui.components.custom.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout1P
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout2P
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.lcarwashmanager.app.ui.layoutcomponets.VLayout2P

@Composable
fun DatosVehiculoWidget(
    txt_body_matricula:String,
    txt_body_clasificacion:String,
) {

    val color = MaterialTheme.colorScheme.surfaceContainer

    HLayout2P(
        modifier = Modifier
            .fillMaxWidth()
            .size(69.dp),
        horizontalArrangement = Arrangement.spacedBy(7.dp),

        content1 = {
            sSurface(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                shape = RoundedCornerShape(13),
                color = color,
            ) {
                VLayout2P(

                    content1 = {
                        HLayout2P(
                            horizontalArrangement = Arrangement.spacedBy(7.dp),
                            content1 = {
                                sIcon(image_vector_id = R.drawable.rounded_pin_24)
                            },
                            content2 = {
                                xTextLabel(text = stringResource(id = R.string.txt_label_matricula_vehiculo))
                            },
                        )
                    },

                    content2 = {
                        HLayout1P(
                            content1 = {
                                xTextBody(text = txt_body_matricula)
                            },
                        )
                    },
                )
            }
        },

        content2 = {
            sSurface(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                shape = RoundedCornerShape(13),
                color = color
            ) {
                VLayout2P(

                    content1 = {
                        HLayout2P(
                            horizontalArrangement = Arrangement.spacedBy(7.dp),
                            content1 = {
                                sIcon(image_vector_id = R.drawable.rounded_car_tag_24)
                            },
                            content2 = {
                                xTextLabel(text = stringResource(id = R.string.txt_label_clasificacion))
                            },
                        )
                    },

                    content2 = {
                        HLayout1P(
                            content1 = {
                                xTextBody(text = txt_body_clasificacion)
                            },
                        )
                    },
                )
            }

        },
    )
}