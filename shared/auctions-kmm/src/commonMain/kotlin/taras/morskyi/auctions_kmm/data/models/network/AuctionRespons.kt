package taras.morskyi.auctions_kmm.data.models.network

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import taras.morskyi.auctions_kmm.data.models.domain.Auction
import taras.morskyi.base_kmm.extensions.orZero

@Serializable
data class AuctionRespons(
    @SerialName("dt") val dateTime: String?,
    @SerialName("winning_bid_max") val winningBidMax: Double?,
    @SerialName("winning_bid_min") val winningBidMin: Double?,
    @SerialName("winning_bid_mean") val winningBidMean: Double?,
    @SerialName("auction_trading_volume") val auctionTradingVolume: Double?,
    @SerialName("auction_lots_count") val auctionLotsCount: Int?,
    @SerialName("all_auctions_lots_count") val allAuctionsLotsCount: Int?,
    @SerialName("auction_name") val auctionName: String?,
    @SerialName("auction_slug") val auctionSlug: String?,
) {
    fun toDomain() = Auction(
        slug = auctionSlug.orEmpty(),
        name = auctionName.orEmpty(),
        date = kotlin.runCatching { dateTime?.toLocalDate() }.getOrNull() ?: Clock.System.now()
            .toLocalDateTime(
                TimeZone.currentSystemDefault()
            ).date,
        min = winningBidMin.orZero(),
        max = winningBidMax.orZero(),
        mean = winningBidMean.orZero(),
        totalValue = auctionTradingVolume.orZero(),
        lotsCount = auctionLotsCount.orZero(),
        allLotsCount = allAuctionsLotsCount.orZero()
    )
}