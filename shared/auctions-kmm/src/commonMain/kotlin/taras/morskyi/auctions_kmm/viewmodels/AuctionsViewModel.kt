package taras.morskyi.auctions_kmm.viewmodels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import taras.morskyi.auctions_kmm.data.models.domain.Auction
import taras.morskyi.auctions_kmm.data.models.viewdata.AuctionNameViewData
import taras.morskyi.auctions_kmm.data.models.viewdata.AuctionsViewState
import taras.morskyi.auctions_kmm.usecases.FetchAuctionUseCase
import taras.morskyi.auctions_kmm.usecases.FilterAuctionsUseCase
import taras.morskyi.base_kmm.common.BaseViewModel
import taras.morskyi.base_kmm.common.KotlinLiveData
import taras.morskyi.base_kmm.extensions.combineStates

class AuctionsViewModel(
    private val fetchAuctionUseCase: FetchAuctionUseCase,
    private val filterAuctionsUseCase: FilterAuctionsUseCase
) : BaseViewModel() {

    private val auctions = MutableStateFlow(emptyList<Auction>())

    private val auctionNames =
        auctions.map { auctions -> auctions.map { auction -> auction.toNameViewData() } }
            .stateIn(kmmViewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val filter: MutableStateFlow<String?> = MutableStateFlow<String?>(null)

    private val filteredAuctions = combine(auctions, filter.filterNotNull()) { auctions, slug ->
        filterAuctionsUseCase(FilterAuctionsUseCase.Params(auctions, slug))
    }.onEach {
        isLoading.value = false
    }.stateIn(kmmViewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    init {
        launchWithProgress {
            auctions.value = fetchAuctionUseCase(Unit)
        }
    }

    override val viewState: KotlinLiveData<AuctionsViewState> = combineStates(
        isLoading,
        errors,
        auctionNames,
        filteredAuctions,
    ) { isLoading,
        errors,
        auctions,
        filteredAuctions ->
        AuctionsViewState(
            auctions,
            filteredAuctions,
            isLoading,
            errors
        )
    }.asKotlinLiveData()

    fun getAuctions(slug: String) {
        isLoading.value = true
        filter.value = slug
    }
}