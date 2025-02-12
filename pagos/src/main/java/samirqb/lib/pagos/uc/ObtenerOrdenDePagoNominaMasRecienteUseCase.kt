package samirqb.lib.pagos.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.pagos.entities.OrdenPagoNominaEntity
import samirqb.lib.pagos.repositories.OrdenPagoNominaRepositories

class ObtenerOrdenDePagoNominaMasRecienteUseCase(
    private val mRepositories: OrdenPagoNominaRepositories
) {
    operator fun invoke(): Flow<OrdenPagoNominaEntity?> {
        return mRepositories.leerMasReciente()
    }
}