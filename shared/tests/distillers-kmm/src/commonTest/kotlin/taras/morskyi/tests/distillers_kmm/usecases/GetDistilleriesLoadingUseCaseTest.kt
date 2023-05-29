package taras.morskyi.tests.distillers_kmm.usecases

import app.cash.turbine.test
import io.github.aakira.napier.Napier
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import org.junit.Test
import org.koin.test.get
import taras.morskyi.distillers_kmm.data.models.domain.DistilleryBid
import taras.morskyi.distillers_kmm.usecases.GetDistilleriesLoadingUseCase
import taras.morskyi.tests.base_kmm.BaseTest
import taras.morskyi.tests.distillers_kmm.data.DistilleriesApiMock
import taras.morskyi.tests.distillers_kmm.data.DistilleryDatabaseMock
import taras.morskyi.tests.distillers_kmm.di.initDistillersDependencyInjection
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GetDistilleriesLoadingUseCaseTest : BaseTest() {

    @Test
    fun `test if distillery bids are fetched and mapped`() = runTest {

        val input = listOf(
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

        var await = true

        initDistillersDependencyInjection(
            apiMock = DistilleriesApiMock(
                getDistilleryData = {
                    delay(5000)
                    input
                }
            ),
            databaseMock = DistilleryDatabaseMock()
        )

        get<GetDistilleriesLoadingUseCase>().invoke(Unit).test {
            assertTrue(awaitItem())
            assertFalse(awaitItem())
        }
    }
}