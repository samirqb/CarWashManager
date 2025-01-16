package samirqb.lib.ofertas.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tab_productos",
    indices = [Index(value = ["id_producto_pk","descripcion"])],
    //primaryKeys = ["id_producto_pk"],
)
data class ProductoEntity(
    @PrimaryKey(autoGenerate = true)
    val id_producto_pk:String,
    val descripcion: String,
    val fecha_hora_creacion: String,
)