package taras.morskyi.tests.distillers_kmm.usecases

import app.cash.turbine.test
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.koin.test.get
import taras.morskyi.distillers_kmm.data.models.domain.Distiller
import taras.morskyi.distillers_kmm.usecases.GetDistilleriesLoadingErrorUseCase
import taras.morskyi.distillers_kmm.usecases.GetDistilleriesLoadingUseCase
import taras.morskyi.tests.base_kmm.BaseTest
import taras.morskyi.tests.distillers_kmm.data.DistilleriesApiMock
import taras.morskyi.tests.distillers_kmm.data.DistilleryDatabaseMock
import taras.morskyi.tests.distillers_kmm.di.initDistillersDependencyInjection
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GetDistilleriesLoadingErrorUseCaseTest: BaseTest() {

    @Test
    fun `test if error from distillery loader is returned and loading status is reset`() = runTest {

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

        val expectedError = NullPointerException("test")

        val delayedInput = MutableStateFlow<List<Distiller>?>(null)

        initDistillersDependencyInjection(
            apiMock = DistilleriesApiMock(
                getDistillers = {
                    delayedInput.filterNotNull().first()
                    throw expectedError
                }
            ),
            databaseMock = DistilleryDatabaseMock()
        )

        get<GetDistilleriesLoadingUseCase>().invoke(Unit).test {
            assertTrue(awaitItem())
            delayedInput.value = input
            assertFalse(awaitItem())
        }

        get<GetDistilleriesLoadingErrorUseCase>().invoke(Unit).test {
            assertEquals(expectedError, awaitItem())
        }
    }
}