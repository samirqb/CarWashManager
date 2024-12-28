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
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout2P
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.lcarwashmanager.app.ui.layoutcomponets.VLayout2P

@Composable
fun FechaYHoraWidget(
    txt_body_fecha: String,
    txt_body_hora: String,
    modifier: Modifier = Modifier,
) {
    HLayout2P(
        modifier = Modifier.fillMaxWidth().size(69.dp),
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        /** fecha */
        content1 = {
            sSurface(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                shape = RoundedCornerShape(13),
                color = MaterialTheme.colorScheme.surfaceContainer,
            ) {
                VLayout2P(

                    content1 =  {
                        xTextLabel(text = stringResource(id = R.string.txt_label_fecha))
                    },

                    content2 = {
                        HLayout2P(
                            content1 = {
                                sIcon(image_vector_id = R.drawable.rounded_calendar_month_24)
                            },
                            content2 = {
                                xTextBody(text = txt_body_fecha)
                            },
                        )
                    },
                )
            }

        },

        /** hora */
        content2 = {
            sSurface(
                modifier = Modifier.weight(1f).fillMaxSize(),
                shape = RoundedCornerShape(13),
                color = MaterialTheme.colorScheme.onSecondary
            ) {
                VLayout2P(

                    content1 =  {
                        xTextLabel(text = stringResource(id = R.string.txt_label_hora))
                    },
                    content2 = {
                        HLayout2P(
                            content1 = {
                                sIcon(image_vector_id = R.drawable.rounded_nest_clock_farsight_analog_24)
                            },
                            content2 = {
                                xTextBody(text = txt_body_hora)
                            },
                        )
                    },
                )
            }

        },
    )
}