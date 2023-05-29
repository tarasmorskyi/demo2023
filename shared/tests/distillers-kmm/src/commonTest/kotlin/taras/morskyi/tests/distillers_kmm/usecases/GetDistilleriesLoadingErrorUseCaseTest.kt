package taras.morskyi.tests.distillers_kmm.usecases

import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import org.junit.Test
import org.koin.test.get
import taras.morskyi.distillers_kmm.data.models.domain.DistilleryBid
import taras.morskyi.distillers_kmm.data.models.viewdata.DistilleryBidViewData
import taras.morskyi.distillers_kmm.usecases.FetchDistilleryBidsUseCase
import taras.morskyi.tests.base_kmm.BaseTest
import taras.morskyi.tests.distillers_kmm.data.DistilleriesApiMock
import taras.morskyi.tests.distillers_kmm.data.DistilleryDatabaseMock
import taras.morskyi.tests.distillers_kmm.di.initDistillersDependencyInjection
import kotlin.test.assertEquals

class GetDistilleriesLoadingErrorUseCaseTest: BaseTest() {
}