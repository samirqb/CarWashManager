package samirqb.carwashmanager.app.ui.components.custom.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.ui.components.base.layouts.sLazyColumn
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.base.inputs.sOutlinedTextField
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout2P
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.lcarwashmanager.app.ui.layoutcomponets.VLayout2P

@Composable
fun AgregarCantPorDenominacionWidget(
    lista_denominaciones: List<String>,
    //value: String,
    //onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
) {

    sLazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = 1.dp)
    ) {
        itemsIndexed(lista_denominaciones){ index, denominacion ->
            sSurface(
                modifier = Modifier.fillMaxWidth().size(75.dp)
            ) {
                HLayout2P(
                    content1 = {
                        sSurface(modifier = Modifier.weight(1.5f).fillMaxWidth().size(53.dp)) {
                            VLayout2P(
                                content1 = { xTextLabel(text = denominacion) },
                                content2 = { xTextBody(text = index.toString()) },
                            )
                        }
                    },

                    content2 = {
                        sSurface(
                            modifier = Modifier.weight(1f).fillMaxWidth().size(53.dp).padding(end = 9.dp),
                            //shape = RoundedCornerShape(0.dp),
                        ) {
                            var value by rememberSaveable{mutableStateOf("")}
                            sOutlinedTextField(
                                value = value,
                                onValueChange = {value = it},
                            )
                        }
                    },
                )
            }
        }
    }
}