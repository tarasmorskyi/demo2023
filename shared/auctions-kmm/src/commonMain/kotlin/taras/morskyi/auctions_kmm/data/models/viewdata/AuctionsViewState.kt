package taras.morskyi.auctions_kmm.data.models.viewdata

import kotlinx.datetime.LocalDate
import taras.morskyi.base_kmm.data.state.BaseViewState
import taras.morskyi.base_kmm.data.state.Mailbox

data class AuctionsViewState(
    val auctions: List<AuctionNameViewData>,
    val filteredAuctions: List<AuctionBidsViewData>,
    override val loading: Boolean,
    override val error: Mailbox<Throwable>
): BaseViewState(loading, error)


data class AuctionNameViewData(
    val slug: String,
    val name: String,)

data class AuctionBidsViewData(
    val name: String,
    val date: LocalDate,
    val max: Double,
    val min: Double,
    val mean: Double,
    val totalValue: Double,
    val lotsCount: Int,
    val allLotsCount: Int,
)