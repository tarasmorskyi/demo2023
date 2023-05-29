package taras.morskyi.auctions.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import taras.morskyi.auctions_kmm.viewmodels.AuctionsViewModel

val auctionsModule = module {
    viewModel {
        get<() -> AuctionsViewModel>()()
    }
}