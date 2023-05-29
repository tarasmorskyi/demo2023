package taras.morskyi.distillers_kmm.di

import org.koin.dsl.module
import taras.morskyi.base_kmm.di.DiConstants
import taras.morskyi.base_kmm.realm.RealmDatabaseConfig
import taras.morskyi.base_kmm.realm.createRealm
import taras.morskyi.distillers_kmm.data.DistilleriesApi
import taras.morskyi.distillers_kmm.data.DistilleriesApiImpl
import taras.morskyi.distillers_kmm.data.DistillersRepository
import taras.morskyi.distillers_kmm.data.DistillersRepositoryImpl
import taras.morskyi.distillers_kmm.data.DistilleryDatabase
import taras.morskyi.distillers_kmm.data.DistilleryRealm
import taras.morskyi.distillers_kmm.data.models.entities.DistilleryEntity
import taras.morskyi.distillers_kmm.usecases.FetchDistilleryBidsUseCase
import taras.morskyi.distillers_kmm.usecases.GetDistilleriesLoadingErrorUseCase
import taras.morskyi.distillers_kmm.usecases.GetDistilleriesLoadingUseCase
import taras.morskyi.distillers_kmm.usecases.GetDistilleriesUseCase
import taras.morskyi.distillers_kmm.viewmodels.DistilleriesViewModel
import taras.morskyi.distillers_kmm.viewmodels.DistilleryBidsViewModel

val distillersKmmModule = module {

    single<DistillersRepository>(createdAtStart = true) {
        DistillersRepositoryImpl(get(), get())
    }

    single(DiConstants.distilleriesRealm) {
        createRealm(
            RealmDatabaseConfig(
                fileName = "distillery.realm",
                schemaVersion = 1,
                models = setOf(DistilleryEntity::class)
            )
        )
    }

    single<DistilleryDatabase> {
        DistilleryRealm(get(DiConstants.distilleriesRealm))
    }

    single<DistilleriesApi> {
        DistilleriesApiImpl(get())
    }

    factory {
        FetchDistilleryBidsUseCase(get())
    }

    factory {
        GetDistilleriesUseCase(get())
    }

    factory {
        GetDistilleriesLoadingUseCase(get())
    }

    factory {
        GetDistilleriesLoadingErrorUseCase(get())
    }

    single(DiConstants.distilleriesViewModel) {
        {
            DistilleriesViewModel(get(), get(), get())
        }
    }

    single(DiConstants.distilleryBidsViewModel) {
        {
            DistilleryBidsViewModel(get())
        }
    }
}