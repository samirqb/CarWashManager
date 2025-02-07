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
fun DatosUsuarioWidget(
    txt_body_nombre: String,
    txt_body_identificacion: String,
    txt_body_telefono: String,
    modifier: Modifier = Modifier,
) {

    val color = MaterialTheme.colorScheme.surfaceContainer

    VLayout2P(
        verticalArrangement = Arrangement.spacedBy(7.dp),
        //NOMBRE Y APOELLIDOS
        content1 = {
            sSurface(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(69.dp),
                //.weight(1f)
                shape = RoundedCornerShape(13),
                color = color
            ) {
                VLayout2P(

                    content1 = {
                        HLayout2P(
                            horizontalArrangement = Arrangement.spacedBy(7.dp),
                            content1 = {
                                sIcon(image_vector_id = R.drawable.rounded_person_24)
                            },
                            content2 = {
                                xTextLabel(text = stringResource(id = R.string.txt_label_nombre_apellidos))
                            },
                        )
                    },

                    content2 = {
                        HLayout1P(
                            content1 = {
                                xTextBody(text = txt_body_nombre)
                            },
                        )
                    },
                )
            }
        },

        // IDENTIFICACION Y TELEFONO
        content2 = {

            HLayout2P(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(69.dp),
                horizontalArrangement = Arrangement.spacedBy(7.dp),

                /** fecha */
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
                                        sIcon(image_vector_id = R.drawable.rounded_id_card_24)
                                    },
                                    content2 = {
                                        xTextLabel(text = stringResource(id = R.string.txt_label_numero_de_identificacion))
                                    },
                                )
                            },

                            content2 = {
                                HLayout1P(
                                    content1 = {
                                        xTextBody(text = txt_body_identificacion)
                                    },
                                )
                            },
                        )
                    }
                },


                /** hora */
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
                                        sIcon(image_vector_id = R.drawable.rounded_perm_phone_msg_24)
                                    },
                                    content2 = {
                                        xTextLabel(text = stringResource(id = R.string.txt_label_telefono))
                                    },
                                )
                            },

                            content2 = {
                                HLayout1P(
                                    content1 = {
                                        xTextBody(text = txt_body_telefono)
                                    },
                                )
                            },
                        )
                    }
                },
            )
        },
    )
}