package taras.morskyi.auctions_kmm.di

import org.koin.dsl.module
import taras.morskyi.auctions_kmm.data.AuctionApiImpl
import taras.morskyi.auctions_kmm.data.AuctionsApi
import taras.morskyi.auctions_kmm.data.AuctionsRepository
import taras.morskyi.auctions_kmm.data.AuctionsRepositoryImpl
import taras.morskyi.auctions_kmm.usecases.FetchAuctionUseCase
import taras.morskyi.auctions_kmm.usecases.FilterAuctionsUseCase
import taras.morskyi.auctions_kmm.viewmodels.AuctionsViewModel

val auctionsKmmModule = module {

    single<AuctionsApi> {
        AuctionApiImpl(get())
    }

    single<AuctionsRepository> {
        AuctionsRepositoryImpl(get())
    }

    factory {
        FetchAuctionUseCase(get())
    }

    factory { FilterAuctionsUseCase() }

    single {
        {
            AuctionsViewModel(get(), get())
        }
    }
}