package samirqb.carwashmanager.app.ui.templates.floatingactionbuttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.ui.components.base.inputs.sFloatingActionButton
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon

@Composable
fun tFloatingActionButton(
    onClick: () -> Unit,
    image_vector_id:Int,
    containerColor: Color = FloatingActionButtonDefaults.containerColor,
) {
    sFloatingActionButton(
        modifier = Modifier.size(66.4.dp),
        shape = RoundedCornerShape(45.dp),
        onClick = onClick,
        containerColor = containerColor,
        content = {
            sIcon(
                modifier = Modifier.fillMaxWidth().size(33.dp),
                image_vector_id = image_vector_id,
            )
        }
    )
}