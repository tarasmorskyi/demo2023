package taras.morskyi.tests.distillers_kmm.usecases

import app.cash.turbine.test
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import org.koin.test.get
import taras.morskyi.distillers_kmm.data.models.domain.Distiller
import taras.morskyi.distillers_kmm.usecases.GetDistilleriesLoadingUseCase
import taras.morskyi.tests.base_kmm.BaseTest
import taras.morskyi.tests.distillers_kmm.data.DistilleriesApiMock
import taras.morskyi.tests.distillers_kmm.data.DistilleryDatabaseMock
import taras.morskyi.tests.distillers_kmm.di.initDistillersDependencyInjection
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GetDistilleriesLoadingUseCaseTest : BaseTest() {

    @Test
    fun `test if loading status is changed before and after loading distilleries`() = runTest {

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

        val delayedInput = MutableStateFlow<List<Distiller>?>(null)

        initDistillersDependencyInjection(
            apiMock = DistilleriesApiMock(
                getDistillers = {
                    delayedInput.filterNotNull().first()
                }
            ),
            databaseMock = DistilleryDatabaseMock()
        )

        get<GetDistilleriesLoadingUseCase>().invoke(Unit).test {
            assertTrue(awaitItem())
            delayedInput.value = input
            assertFalse(awaitItem())
        }
    }
}