package samirqb.lib.pagos.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.pagos.entities.OrdenPagoNominaEntity
import samirqb.lib.pagos.repositories.OrdenPagoNominaRepository

class ObtenerOrdenDePagoNominaMasRecienteUseCase(
    private val mRepositories: OrdenPagoNominaRepository
) {
    operator fun invoke(): Flow<OrdenPagoNominaEntity?> {
        return mRepositories.leerMasReciente()
    }
}