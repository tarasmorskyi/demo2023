package taras.morskyi.tests.auctions_kmm.di

import org.koin.core.context.startKoin
import taras.morskyi.auctions_kmm.di.auctionsKmmModule
import taras.morskyi.base_kmm.di.baseKmmModule
import taras.morskyi.tests.auctions_kmm.data.AuctionsApiMock

fun initAuctionsDependencyInjection(
    apiMock: AuctionsApiMock
) = startKoin {
    modules(
        baseKmmModule,
        auctionsKmmModule,
        auctionsKmmTestModule(apiMock)
    )
}