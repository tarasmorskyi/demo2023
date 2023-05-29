package taras.morskyi.auctions_kmm.usecases

import taras.morskyi.auctions_kmm.data.AuctionsRepository
import taras.morskyi.auctions_kmm.data.models.domain.Auction
import taras.morskyi.base_kmm.common.UseCase

class FetchAuctionUseCase(private val auctionsRepository: AuctionsRepository) :
    UseCase<Unit, List<Auction>> {
    override suspend fun invoke(param: Unit): List<Auction> {
        return auctionsRepository.fetchAuctions()
    }

}