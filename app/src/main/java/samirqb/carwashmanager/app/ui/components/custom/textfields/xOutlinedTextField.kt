package samirqb.carwashmanager.app.ui.components.custom.textfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import samirqb.carwashmanager.app.ui.components.base.inputs.sOutlinedTextField
import samirqb.carwashmanager.app.ui.components.custom.textfields.config.SeparadorDeMiles

@Composable
fun xOutlinedTextField(
    value:String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    sOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Number, ),
        visualTransformation = visualTransformation,
        singleLine = true,
    )
}