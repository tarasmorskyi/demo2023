package taras.morskyi.tests.distillers_kmm.usecases

import app.cash.turbine.test
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.runTest
import org.koin.test.get
import taras.morskyi.distillers_kmm.data.models.domain.Distiller
import taras.morskyi.distillers_kmm.data.models.viewdata.DistilleryViewData
import taras.morskyi.distillers_kmm.usecases.GetDistilleriesUseCase
import taras.morskyi.tests.base_kmm.BaseTest
import taras.morskyi.tests.distillers_kmm.data.DistilleriesApiMock
import taras.morskyi.tests.distillers_kmm.data.DistilleryDatabaseMock
import taras.morskyi.tests.distillers_kmm.di.initDistillersDependencyInjection
import kotlin.test.Test
import kotlin.test.assertEquals

class GetDistilleriesUseCaseTest : BaseTest() {

    @Test
    fun `test if distilleries are fetched, stored and returned`() = runTest {

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

        val databaseInsert = MutableStateFlow<List<List<Distiller>>>(emptyList())

        initDistillersDependencyInjection(
            apiMock = DistilleriesApiMock(
                getDistillers = {
                    input
                }
            ),
            databaseMock = DistilleryDatabaseMock(
                getDistilleries = {
                    flowOf(input)
                },
                insertDistilleries = { distillers ->
                    databaseInsert.update { value ->
                        value + listOf(distillers)
                    }
                }
            )
        )

        databaseInsert.test {
            assertEquals(listOf(input), awaitItem())
        }

        get<GetDistilleriesUseCase>().invoke(Unit).test {
            assertEquals(expected, awaitItem())
            awaitComplete()
        }
    }
}