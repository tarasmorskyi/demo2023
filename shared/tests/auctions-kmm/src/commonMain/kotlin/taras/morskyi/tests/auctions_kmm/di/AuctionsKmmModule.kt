package taras.morskyi.tests.auctions_kmm.di

import org.koin.dsl.module
import taras.morskyi.auctions_kmm.data.AuctionApiImpl
import taras.morskyi.auctions_kmm.data.AuctionsApi
import taras.morskyi.auctions_kmm.data.AuctionsRepository
import taras.morskyi.auctions_kmm.data.AuctionsRepositoryImpl
import taras.morskyi.auctions_kmm.usecases.FetchAuctionUseCase
import taras.morskyi.auctions_kmm.usecases.FilterAuctionsUseCase
import taras.morskyi.auctions_kmm.viewmodels.AuctionsViewModel
import taras.morskyi.tests.auctions_kmm.data.AuctionsApiMock
import taras.morskyi.tests.auctions_kmm.data.AuctionsApiTest

fun auctionsKmmTestModule(
    apiMock: AuctionsApiMock
) = module {

    single<AuctionsApi> {
        AuctionsApiTest(apiMock)
    }
}