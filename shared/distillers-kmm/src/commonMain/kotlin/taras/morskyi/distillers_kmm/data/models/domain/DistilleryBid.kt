package taras.morskyi.distillers_kmm.data.models.domain

import kotlinx.datetime.LocalDate
import taras.morskyi.distillers_kmm.data.models.viewdata.DistilleryBidViewData

data class DistilleryBid(
    val name: String,
    val slug: String,
    val date: LocalDate,
    val bidMax: Double,
    val bidMin: Double,
    val bidMean: Double,
    val tradingVolume: Double,
    val lotsCount: Int,
) {
    fun toViewData() = DistilleryBidViewData(
        name, date, bidMax, bidMin, bidMean, tradingVolume, lotsCount
    )
}