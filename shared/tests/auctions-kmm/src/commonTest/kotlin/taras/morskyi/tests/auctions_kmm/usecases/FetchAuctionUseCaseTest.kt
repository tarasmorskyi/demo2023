package taras.morskyi.tests.auctions_kmm.usecases

import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import org.koin.test.get
import taras.morskyi.auctions_kmm.data.models.domain.Auction
import taras.morskyi.auctions_kmm.usecases.FetchAuctionUseCase
import taras.morskyi.tests.auctions_kmm.data.AuctionsApiMock
import taras.morskyi.tests.auctions_kmm.di.initAuctionsDependencyInjection
import taras.morskyi.tests.base_kmm.BaseTest
import kotlin.test.Test
import kotlin.test.assertEquals

class FetchAuctionUseCaseTest : BaseTest() {

    @Test
    fun `test if auctions are fetched`() = runTest {

        val expected = listOf(
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
            )
        )

        initAuctionsDependencyInjection(
            apiMock = AuctionsApiMock(
                getAuction = {
                    expected
                }
            )
        )

        val actual = get<FetchAuctionUseCase>().invoke(Unit)

        assertEquals(expected, actual)
    }
}