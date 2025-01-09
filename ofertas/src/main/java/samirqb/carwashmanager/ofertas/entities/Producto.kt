package samirqb.carwashmanager.ofertas.entities

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "tab_productos",
    indices = [Index(value = ["id_producto_pk","descripcion"])],
    primaryKeys = ["id_producto_pk"],
)
data class Producto(
    val id_producto_pk:String,
    val descripcion: String,
    val fecha_hora_creacion: String,
)