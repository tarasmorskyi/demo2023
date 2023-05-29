package taras.morskyi.distillers_kmm.data.models.viewdata

import kotlinx.datetime.LocalDate
import taras.morskyi.base_kmm.data.state.BaseViewState
import taras.morskyi.base_kmm.data.state.Mailbox

data class DistilleryBidsViewState(
    val distilleryBids: List<DistilleryBidViewData>,
    override val loading: Boolean,
    override val error: Mailbox<Throwable>
) : BaseViewState(loading, error)

data class DistilleryBidViewData(
    val name: String,
    val date: LocalDate,
    val bidMax: Double,
    val bidMin: Double,
    val bidMean: Double,
    val tradingVolume: Double,
    val lotsCount: Int,
)