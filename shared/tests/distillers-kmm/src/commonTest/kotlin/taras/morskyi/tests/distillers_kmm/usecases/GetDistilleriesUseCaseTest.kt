package taras.morskyi.tests.distillers_kmm.usecases

import app.cash.turbine.test
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import org.junit.Test
import org.koin.test.get
import taras.morskyi.distillers_kmm.data.models.domain.Distiller
import taras.morskyi.distillers_kmm.data.models.domain.DistilleryBid
import taras.morskyi.distillers_kmm.data.models.entities.DistilleryEntity
import taras.morskyi.distillers_kmm.data.models.viewdata.DistilleryBidViewData
import taras.morskyi.distillers_kmm.data.models.viewdata.DistilleryViewData
import taras.morskyi.distillers_kmm.usecases.FetchDistilleryBidsUseCase
import taras.morskyi.distillers_kmm.usecases.GetDistilleriesUseCase
import taras.morskyi.tests.base_kmm.BaseTest
import taras.morskyi.tests.distillers_kmm.data.DistilleriesApiMock
import taras.morskyi.tests.distillers_kmm.data.DistilleryDatabaseMock
import taras.morskyi.tests.distillers_kmm.di.initDistillersDependencyInjection
import kotlin.test.assertEquals

class GetDistilleriesUseCaseTest : BaseTest() {

    @Test
    fun `test if distillery bids are fetched and mapped`() = runTest {

        val input = listOf(
            Distiller(
                slug = "test",
                name = "test",
                country = "test",
                whiskies = 1,
                votes = 1,
                rating = 1.0
            )
        )

        val expected = listOf(
            DistilleryViewData(
                slug = "test",
                name = "test",
                country = "test",
                whiskies = 1,
                votes = 1,
                rating = 1.0
            )
        )

        initDistillersDependencyInjection(
            apiMock = DistilleriesApiMock(
                getDistillers = {
                    input
                }
            ),
            databaseMock = DistilleryDatabaseMock(
                getDistilleries = {
                    flowOf(input)
                }
            )
        )

        get<GetDistilleriesUseCase>().invoke(Unit).test {
            assertEquals(expected, awaitItem())
            awaitComplete()
        }
    }
}