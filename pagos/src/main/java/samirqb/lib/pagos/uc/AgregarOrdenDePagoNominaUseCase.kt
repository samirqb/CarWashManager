package samirqb.lib.pagos.uc

import samirqb.lib.pagos.entities.OrdenPagoNominaEntity
import samirqb.lib.pagos.repositories.OrdenPagoNominaRepositories

class AgregarOrdenDePagoNominaUseCase(
    private val mRepository: OrdenPagoNominaRepositories
) {
    suspend operator fun invoke(mEntity: OrdenPagoNominaEntity){
        mRepository.insertar(mEntity)
    }
}
