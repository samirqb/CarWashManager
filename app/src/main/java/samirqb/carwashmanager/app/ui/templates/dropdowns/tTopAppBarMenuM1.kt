package samirqb.carwashmanager.app.ui.templates.dropdowns

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.inputs.sIconButton
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout2P
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody

@Composable
fun tTopAppBarMenuM1(
    onClick_dropdownmenuitem_admin_moneda: ()->Unit,
    onClick_dropdownmenuitem_admin_servicios: ()->Unit,
    onClick_dropdownmenuitem_admin_empleados: ()->Unit,
    onClick_dropdownmenuitem_admin_clientes: ()->Unit,
    onClick_dropdownmenuitem_admin_categorias: ()->Unit,
) {

    var display_menu by rememberSaveable { mutableStateOf(false) }

    sIconButton(
        onClick = {display_menu = !display_menu},
        content = {

            sIcon(image_vector_id = R.drawable.rounded_more_vert_24)

            DropdownMenu(
                expanded = display_menu,
                onDismissRequest = { display_menu = false },
                modifier = Modifier.size(width = 266.dp, height = Dp.Unspecified),
                offset = DpOffset(20.dp,20.dp),
            ){

                val SPACE_BY = 13.dp

                HorizontalDivider()

                // A D M I N I S T R A S   M O N E D A D
                DropdownMenuItem(
                    text = {
                        HLayout2P(
                            horizontalArrangement = Arrangement.spacedBy(space = SPACE_BY),
                            content1 = { sIcon(image_vector_id = R.drawable.rounded_attach_money_24) },
                            content2 = { xTextBody(stringResource(id = R.string.txt_body_menu_monedas)) },
                        )
                    },
                    onClick = {
                        display_menu = false
                        onClick_dropdownmenuitem_admin_moneda()
                    }
                )
                HorizontalDivider()

                // A D M I N I S T R A S   S E R V I C I O S
                DropdownMenuItem(
                    text = {
                        HLayout2P(
                            horizontalArrangement = Arrangement.spacedBy(space = SPACE_BY),
                            content1 = { sIcon(image_vector_id = R.drawable.rounded_local_car_wash_24) },
                            content2 = { xTextBody(stringResource(id = R.string.txt_body_menu_servicios)) },
                        )
                    },
                    onClick = {
                        display_menu = false
                        onClick_dropdownmenuitem_admin_servicios()
                    }
                )
                HorizontalDivider()

                // A D M I N I S T R A S   E M P L E A D O S
                DropdownMenuItem(
                    text = {
                        HLayout2P(
                            horizontalArrangement = Arrangement.spacedBy(space = SPACE_BY),
                            content1 = { sIcon(image_vector_id = R.drawable.rounded_server_person_24) },
                            content2 = { xTextBody(stringResource(id = R.string.txt_body_menu_empleados)) },
                        )
                    },
                    onClick = {
                        display_menu = false
                        onClick_dropdownmenuitem_admin_empleados()
                    }
                )
                HorizontalDivider()

                // A D M I N I S T R A S   C A T E G O R I A S   D E   V E H I C U L O S
                DropdownMenuItem(
                    text = {
                        HLayout2P(
                            horizontalArrangement = Arrangement.spacedBy(space = SPACE_BY),
                            content1 = { sIcon(image_vector_id = R.drawable.rounded_category_24) },
                            content2 = { xTextBody(stringResource(id = R.string.txt_body_menu_categorias_vehiculos)) },
                        )
                    },
                    onClick = {
                        display_menu = false
                        onClick_dropdownmenuitem_admin_categorias()
                    }
                )
                HorizontalDivider()

                // A D M I N I S T R A S   C L I E N T E S
                DropdownMenuItem(
                    text = {
                        HLayout2P(
                            horizontalArrangement = Arrangement.spacedBy(space = SPACE_BY),
                            content1 = { sIcon(image_vector_id = R.drawable.rounded_group_24) },
                            content2 = { xTextBody(stringResource(id = R.string.txt_body_menu_clientes)) },
                        )
                    },
                    onClick = {
                        display_menu = false
                        onClick_dropdownmenuitem_admin_clientes()
                    }
                )
                HorizontalDivider()
            }
        }
    )
}
