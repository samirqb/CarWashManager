package samirqb.lib.caja.entidades

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "tab_unidades_monetarias",
    indices = [Index(value = ["codigo_iso_4217_pk", "nombre_y_origen"])],
    primaryKeys = ["codigo_iso_4217_pk"]
)
data class UnidadMonetariaEntity(
    val codigo_iso_4217_pk: String,
    val nombre_y_origen: String,
    // SQLite funcion: datetime('now') = YYYY-MM-DD HH:MM:SS.SSS
    // Koltin funcion: LocalDateTime.now() = 2017-01-01T22:27:06.006276200 <-> import java.time.LocalDateTime
    val fecha_hora_creacion: String
)