package samirqb.carwashmanager.app.ui.templates.topappbars.config

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun miCustomSetTopAppBarColors():TopAppBarColors {
    return TopAppBarColors(
        //containerColor = MaterialTheme.colorScheme.primaryContainer ,
        containerColor = MaterialTheme.colorScheme.onSecondary ,
        scrolledContainerColor = MaterialTheme.colorScheme.onSecondaryContainer,
        navigationIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        actionIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
    )
}