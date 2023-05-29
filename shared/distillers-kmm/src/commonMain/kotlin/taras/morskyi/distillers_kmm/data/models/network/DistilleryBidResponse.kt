package taras.morskyi.distillers_kmm.data.models.network

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import taras.morskyi.base_kmm.extensions.orZero
import taras.morskyi.distillers_kmm.data.models.domain.DistilleryBid

@Serializable
data class DistilleryBidResponse(
    val name: String? = null,
    val slug: String? = null,
    @SerialName("dt") val dateTime: String? = null,
    @SerialName("winning_bid_max") val winningBidMax: Double? = null,
    @SerialName("winning_bid_min") val winningBidMin: Double? = null,
    @SerialName("winning_bid_mean") val winningBidMean: Double? = null,
    @SerialName("trading_volume") val tradingVolume: Double? = null,
    @SerialName("lots_count") val lotsCount: Int? = null,
) {
    fun toDomain() = DistilleryBid(
        name = name.orEmpty(),
        slug = slug.orEmpty(),
        date = kotlin.runCatching { dateTime?.toLocalDate() }.getOrNull() ?: Clock.System.now()
            .toLocalDateTime(
                TimeZone.currentSystemDefault()
            ).date,
        bidMax = winningBidMax.orZero(),
        bidMin = winningBidMin.orZero(),
        bidMean = winningBidMean.orZero(),
        tradingVolume = tradingVolume.orZero(),
        lotsCount = lotsCount.orZero(),
    )
}