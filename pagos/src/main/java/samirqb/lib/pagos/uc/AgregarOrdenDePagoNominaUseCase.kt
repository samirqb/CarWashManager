package samirqb.lib.pagos.uc

import samirqb.lib.pagos.entities.OrdenPagoNominaEntity
import samirqb.lib.pagos.repositories.OrdenPagoNominaRepository

class AgregarOrdenDePagoNominaUseCase(
    private val mRepository: OrdenPagoNominaRepository
) {
    suspend operator fun invoke(mEntity: OrdenPagoNominaEntity){
        mRepository.insertar(mEntity)
    }
}
