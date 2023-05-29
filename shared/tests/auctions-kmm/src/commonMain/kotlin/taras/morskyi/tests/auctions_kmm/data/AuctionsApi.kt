package taras.morskyi.tests.auctions_kmm.data

import taras.morskyi.auctions_kmm.data.AuctionsApi
import taras.morskyi.auctions_kmm.data.models.domain.Auction

class AuctionsApiMock(
    val getAuction: () -> List<Auction> = { emptyList() }
)

class AuctionsApiTest(private val mock: AuctionsApiMock) : AuctionsApi {
    override suspend fun getAuctions(): List<Auction> = mock.getAuction()
}