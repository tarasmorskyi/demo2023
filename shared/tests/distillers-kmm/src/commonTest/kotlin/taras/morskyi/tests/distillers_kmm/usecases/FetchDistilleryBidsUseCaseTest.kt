package taras.morskyi.tests.distillers_kmm.usecases

import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import org.koin.test.get
import taras.morskyi.distillers_kmm.data.models.domain.DistilleryBid
import taras.morskyi.distillers_kmm.data.models.viewdata.DistilleryBidViewData
import taras.morskyi.distillers_kmm.usecases.FetchDistilleryBidsUseCase
import taras.morskyi.tests.base_kmm.BaseTest
import taras.morskyi.tests.distillers_kmm.data.DistilleriesApiMock
import taras.morskyi.tests.distillers_kmm.data.DistilleryDatabaseMock
import taras.morskyi.tests.distillers_kmm.di.initDistillersDependencyInjection
import kotlin.test.Test
import kotlin.test.assertEquals

class FetchDistilleryBidsUseCaseTest: BaseTest() {

    @Test
    fun `test if distillery bids are fetched and mapped`() = runTest {

        val imput = listOf(
            DistilleryBid(
                slug = "test",
                name = "test",
                date = LocalDate.fromEpochDays(0),
                bidMax = 0.0,
                bidMin = 0.0,
                bidMean = 0.0,
                tradingVolume = 0.0,
                lotsCount = 1,
            )
        )

        val expected = listOf(
            DistilleryBidViewData(
                name = "test",
                date = LocalDate.fromEpochDays(0),
                bidMax = 0.0,
                bidMin = 0.0,
                bidMean = 0.0,
                tradingVolume = 0.0,
                lotsCount = 1,
            )
        )

        initDistillersDependencyInjection(
            apiMock = DistilleriesApiMock(
                getDistilleryData = {
                    imput
                }
            ),
            databaseMock = DistilleryDatabaseMock()
        )

        val actual = get<FetchDistilleryBidsUseCase>().invoke("test")

        assertEquals(expected, actual)
    }
}