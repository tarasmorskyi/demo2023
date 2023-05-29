package taras.morskyi.tests.distillers_kmm.di

import org.koin.core.context.startKoin
import taras.morskyi.base_kmm.di.baseKmmModule
import taras.morskyi.distillers_kmm.di.distillersKmmModule
import taras.morskyi.tests.distillers_kmm.data.DistilleriesApiMock
import taras.morskyi.tests.distillers_kmm.data.DistilleryDatabaseMock

fun initDistillersDependencyInjection(
    databaseMock: DistilleryDatabaseMock,
    apiMock: DistilleriesApiMock
) = startKoin {
    modules(
        baseKmmModule,
        distillersKmmModule,
        distillersKmmTestModule(databaseMock, apiMock)
    )
}