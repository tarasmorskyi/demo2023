package taras.morskyi.tests.distillers_kmm.di

import org.koin.dsl.module
import taras.morskyi.distillers_kmm.data.DistilleriesApi
import taras.morskyi.distillers_kmm.data.DistilleryDatabase
import taras.morskyi.tests.distillers_kmm.data.DistilleriesApiMock
import taras.morskyi.tests.distillers_kmm.data.DistilleriesApiTest
import taras.morskyi.tests.distillers_kmm.data.DistilleryDatabaseMock
import taras.morskyi.tests.distillers_kmm.data.DistilleryDatabaseTest

fun distillersKmmTestModule(
    databaseMock: DistilleryDatabaseMock,
    apiMock: DistilleriesApiMock
) = module {

    single<DistilleryDatabase> {
        DistilleryDatabaseTest(databaseMock)
    }

    single<DistilleriesApi> {
        DistilleriesApiTest(apiMock)
    }
}