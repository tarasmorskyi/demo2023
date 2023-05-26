package taras.morskyi.feature.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import taras.morskyi.feature_kmm.viewmodels.FeatureViewModel

val featureModule = module {
    viewModel {
        get<() -> FeatureViewModel>()()
    }
}