package taras.morskyi.feature_kmm.di

import org.koin.dsl.module
import taras.morskyi.feature_kmm.viewmodels.FeatureViewModel

val featureKmmModule = module {
    //Repository
    //Usecases

    single {
        {
            FeatureViewModel()
        }
    }
}