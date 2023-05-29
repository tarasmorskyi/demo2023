package taras.morskyi.auctions_kmm.data

import io.ktor.http.HttpMethod
import taras.morskyi.auctions_kmm.data.models.domain.Auction
import taras.morskyi.auctions_kmm.data.models.network.AuctionRespons
import taras.morskyi.base_kmm.common.DemoHttpClient
import taras.morskyi.base_kmm.common.KtorRequestParams

interface AuctionsApi {
    suspend fun getAuctions(): List<Auction>
}

class AuctionApiImpl(
    val demoHttpClient: DemoHttpClient
): AuctionsApi {
    override suspend fun getAuctions(): List<Auction> = demoHttpClient.request<List<AuctionRespons>>(
        KtorRequestParams(
            url = "auctions_data/?format=json",
            httpMethod = HttpMethod.Get
        )
    ).map { response -> response.toDomain() }
}