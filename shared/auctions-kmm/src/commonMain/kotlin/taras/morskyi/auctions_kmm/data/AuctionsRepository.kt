package taras.morskyi.auctions_kmm.data

import taras.morskyi.auctions_kmm.data.models.domain.Auction

interface AuctionsRepository {
    suspend fun fetchAuctions(): List<Auction>
}

class AuctionsRepositoryImpl(
    val api: AuctionsApi
): AuctionsRepository {
    override suspend fun fetchAuctions(): List<Auction> = api.getAuctions()
}