package taras.morskyi.distillers_kmm.usecases

import kotlinx.coroutines.flow.StateFlow
import taras.morskyi.base_kmm.common.SynchronousUseCase
import taras.morskyi.distillers_kmm.data.DistillersRepository

class GetDistilleriesLoadingUseCase(private val distillersRepository: DistillersRepository) :
    SynchronousUseCase<Unit, StateFlow<Boolean>> {
    override fun invoke(param: Unit): StateFlow<Boolean> {
        return distillersRepository.distilleriesLoading
    }

}