package taras.morskyi.tests.auctions_kmm.usecases

import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import org.koin.test.get
import taras.morskyi.auctions_kmm.data.models.domain.Auction
import taras.morskyi.auctions_kmm.data.models.viewdata.AuctionBidsViewData
import taras.morskyi.auctions_kmm.usecases.FilterAuctionsUseCase
import taras.morskyi.tests.auctions_kmm.data.AuctionsApiMock
import taras.morskyi.tests.auctions_kmm.di.initAuctionsDependencyInjection
import taras.morskyi.tests.base_kmm.BaseTest
import kotlin.test.Test
import kotlin.test.assertEquals

class FilterAuctionsUseCaseTest : BaseTest() {

    @Test
    fun `test if auctions are filtered by slug and mapped`() = runTest {

        val input = listOf(
            Auction(
                slug = "test",
                name = "test",
                date = LocalDate.fromEpochDays(0),
                max = 0.0,
                min = 0.0,
                mean = 0.0,
                totalValue = 0.0,
                lotsCount = 1,
                allLotsCount = 1
            ),
            Auction(
                slug = "test",
                name = "test1",
                date = LocalDate.fromEpochDays(0),
                max = 0.0,
                min = 0.0,
                mean = 0.0,
                totalValue = 0.0,
                lotsCount = 1,
                allLotsCount = 1
            ),
            Auction(
                slug = "test",
                name = "test2",
                date = LocalDate.fromEpochDays(0),
                max = 0.0,
                min = 0.0,
                mean = 0.0,
                totalValue = 0.0,
                lotsCount = 1,
                allLotsCount = 1
            ),
            Auction(
                slug = "wrong",
                name = "test",
                date = LocalDate.fromEpochDays(0),
                max = 0.0,
                min = 0.0,
                mean = 0.0,
                totalValue = 0.0,
                lotsCount = 1,
                allLotsCount = 1
            )
        )


        val expected = listOf(
            AuctionBidsViewData(
                name = "test",
                date = LocalDate.fromEpochDays(0),
                max = 0.0,
                min = 0.0,
                mean = 0.0,
                totalValue = 0.0,
                lotsCount = 1,
                allLotsCount = 1
            ),
            AuctionBidsViewData(
                name = "test1",
                date = LocalDate.fromEpochDays(0),
                max = 0.0,
                min = 0.0,
                mean = 0.0,
                totalValue = 0.0,
                lotsCount = 1,
                allLotsCount = 1
            ),
            AuctionBidsViewData(
                name = "test2",
                date = LocalDate.fromEpochDays(0),
                max = 0.0,
                min = 0.0,
                mean = 0.0,
                totalValue = 0.0,
                lotsCount = 1,
                allLotsCount = 1
            ),
        )

        initAuctionsDependencyInjection(
            apiMock = AuctionsApiMock()
        )

        val actual =
            get<FilterAuctionsUseCase>().invoke(FilterAuctionsUseCase.Params(input, "test"))

        assertEquals(expected, actual)
    }

}