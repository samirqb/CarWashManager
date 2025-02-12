package samirqb.carwashmanager.app.ui.templates.modalbottomsheets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.ui.components.base.layouts.sLazyColumn
import samirqb.carwashmanager.app.ui.components.base.containers.sModalBottomSheet
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextTitle
import samirqb.carwashmanager.app.ui.layoutcomponets.VLayout2P

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun tModalBottomSheet(
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
    list_content:List<String>,
    titulo_ModalBottomSheet: Int,

) {
    sModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
    ) {

        VLayout2P(

            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),

            content1 = {
                sSurface(
                    modifier = Modifier.padding(bottom = 30.dp),
                    color = Color.Transparent
                ) {
                    xTextTitle(
                        text = stringResource(
                            id = titulo_ModalBottomSheet
                        )
                    )
                }
            },

            content2 = {
                sLazyColumn(
                    modifier = Modifier.padding(11.dp),
                    verticalArrangement = Arrangement.spacedBy(space = 11.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                )
                {

                    itemsIndexed(list_content){ index, item ->

                        sSurface(
                            modifier = Modifier.fillMaxWidth().size(66.dp),
                            shape = RoundedCornerShape(15.dp),
                            color = MaterialTheme.colorScheme.scrim
                        ) {
                            item.forEach {
                                VLayout2P(
                                    content1 = { xTextTitle(text = item) },
                                    content2 = { xTextTitle(text = "${index}") },
                                )
                            }
                        }
                    }
                }
            },
        )
    }
}