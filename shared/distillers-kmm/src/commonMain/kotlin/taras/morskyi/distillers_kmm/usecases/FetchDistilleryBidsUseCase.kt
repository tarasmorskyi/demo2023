package taras.morskyi.distillers_kmm.usecases

import taras.morskyi.base_kmm.common.UseCase
import taras.morskyi.distillers_kmm.data.DistillersRepository
import taras.morskyi.distillers_kmm.data.models.domain.DistilleryBid
import taras.morskyi.distillers_kmm.data.models.viewdata.DistilleryBidViewData

class FetchDistilleryBidsUseCase(
    private val repository: DistillersRepository
): UseCase<String, List<DistilleryBidViewData>> {
    override suspend fun invoke(slug: String): List<DistilleryBidViewData> {
        return repository.fetchDistillerData(slug).map { bid -> bid.toViewData() }
    }
}