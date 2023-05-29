package taras.morskyi.distillers.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import taras.morskyi.base_kmm.di.DiConstants
import taras.morskyi.distillers_kmm.viewmodels.DistilleriesViewModel
import taras.morskyi.distillers_kmm.viewmodels.DistilleryBidsViewModel

val distillersModule = module {
    viewModel {
        get<() -> DistilleriesViewModel>(DiConstants.distilleriesViewModel)()
    }

    viewModel {
        get<() -> DistilleryBidsViewModel>(DiConstants.distilleryBidsViewModel)()
    }
}