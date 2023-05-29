package taras.morskyi.auctions_kmm.usecases

import taras.morskyi.auctions_kmm.data.models.domain.Auction
import taras.morskyi.auctions_kmm.data.models.viewdata.AuctionBidsViewData
import taras.morskyi.base_kmm.common.UseCase

class FilterAuctionsUseCase: UseCase<FilterAuctionsUseCase.Params, List<AuctionBidsViewData>> {
    data class Params(
        val auctions: List<Auction>,
        val slug: String
    )

    override suspend fun invoke(param: Params): List<AuctionBidsViewData> {
        return param.auctions.filter { auction -> auction.slug == param.slug }.map { it.toBidsViewData() }
    }
}