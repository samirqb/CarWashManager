package samirqb.carwashmanager.app.ui.components.base.containers

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MenuItemColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun sDropdownMenuItem(
    text: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier,
    leadingIcon: @Composable() (() -> Unit)?,
    trailingIcon: @Composable() (() -> Unit)?,
    enabled: Boolean,
    colors: MenuItemColors,
    contentPadding: PaddingValues,
    interactionSource: MutableInteractionSource?
) {
    DropdownMenuItem(
        text = text,
        onClick = onClick,
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        enabled = enabled,
        colors = colors,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
    )
}