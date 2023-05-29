package taras.morskyi.distillers_kmm.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import taras.morskyi.base_kmm.common.SynchronousUseCase
import taras.morskyi.distillers_kmm.data.DistillersRepository
import taras.morskyi.distillers_kmm.data.models.viewdata.DistilleryViewData

class GetDistilleriesUseCase(
    private val repository: DistillersRepository
) : SynchronousUseCase<Unit, Flow<List<DistilleryViewData>>> {
    override fun invoke(param: Unit): Flow<List<DistilleryViewData>> {
        return repository.getDistillers()
            .map { distilleries -> distilleries.map { distiller -> distiller.toViewData() } }
    }
}