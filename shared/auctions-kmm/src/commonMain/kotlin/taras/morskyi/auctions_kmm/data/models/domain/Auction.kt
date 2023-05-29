package taras.morskyi.auctions_kmm.data.models.domain

import kotlinx.datetime.LocalDate
import taras.morskyi.auctions_kmm.data.models.viewdata.AuctionBidsViewData
import taras.morskyi.auctions_kmm.data.models.viewdata.AuctionNameViewData

data class Auction(
    val slug: String,
    val name: String,
    val date: LocalDate,
    val max: Double,
    val min: Double,
    val mean: Double,
    val totalValue: Double,
    val lotsCount: Int,
    val allLotsCount: Int,
) {
    fun toNameViewData() = AuctionNameViewData(
        slug = slug,
        name = name
    )

    fun toBidsViewData() = AuctionBidsViewData(
        name = name,
        date = date,
        max = max,
        min = min,
        mean = mean,
        totalValue = totalValue,
        lotsCount = lotsCount,
        allLotsCount = allLotsCount
    )
}