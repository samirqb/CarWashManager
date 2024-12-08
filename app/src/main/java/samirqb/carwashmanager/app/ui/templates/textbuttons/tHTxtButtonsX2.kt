package samirqb.carwashmanager.app.ui.templates.textbuttons

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.base.inputs.sTextButton
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout2P
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel

@Composable
fun tHTxtButtonsX2(
    txt_btn_1: Int,
    on_click_boton_1: ()->Unit,
    txt_btn_2: Int,
    on_click_boton_2: ()->Unit,
    modifier: Modifier,
) {
    sSurface(
        modifier = modifier,
        color =  MaterialTheme.colorScheme.secondaryContainer,
    ) {
        HLayout2P(
            content1 = {
                sTextButton(
                    onClick = on_click_boton_1,
                    content = {
                        xTextLabel(text = stringResource(id = txt_btn_1))
                    }
                )
            },
            content2 = {
                sTextButton(
                    onClick = on_click_boton_2,
                    content = {
                        xTextLabel(text = stringResource(id = txt_btn_2))
                    }
                )
            },
        )
    }
}